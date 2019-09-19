package com.rhynoboy2009.quotes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.rhynoboy2009.quotes.response_quotes.QuoteResponse
import com.rhynoboy2009.quotes.response_quotes.QuotesApi
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.viewpager.widget.ViewPager


class MainActivity : AppCompatActivity() {

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://quotify.cdn.prismic.io/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        quotes_pager.adapter = QuoteAdapter(applicationContext, getListOfQuotes(retrofit))
        quotes_pager.pageMargin = 10
        quotes_pager.offscreenPageLimit = 10
        quotes_pager.setPageMarginDrawable(android.R.color.black)
        quotes_pager.setPageTransformer(true, ParallaxTransformer())

    }

    private fun getListOfQuotes(retrofit: Retrofit) : List<QuoteFinal> {
        val quotes = ArrayList<QuoteFinal>()

        disposables.add(
            getAllQuotes(retrofit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ( { result ->
                    for (quote in result.results)
                        quotes.add(QuoteFinal(quote.data.author_image.url, quote.data.quote[0].text, quote.data.author[0].text))
                    quotes_pager.adapter?.notifyDataSetChanged()
                }, { throwable -> Log.d("method", throwable.localizedMessage!!)})
        )

        return quotes
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun getAllQuotes(rf: Retrofit): Observable<QuoteResponse> {
        val quoteAPI = rf.create(QuotesApi::class.java)
        return quoteAPI.getRef()
            .flatMap { response -> quoteAPI.getQuotes(response.refs[0].ref) }
    }

    class QuoteAdapter(private val context: Context, private val list: List<QuoteFinal>): PagerAdapter() {

        override fun isViewFromObject(view: View, o: Any): Boolean {
            return o==view
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            val quoteData = list[position]

            val layout = LayoutInflater.from(context).inflate(R.layout.quote_item, container, false)
            val authorImage = layout.findViewById<ImageView>(R.id.author_image)
            val quote = layout.findViewById<TextView>(R.id.quote)
            val author = layout.findViewById<TextView>(R.id.author)

            Glide.with(context).load(quoteData.authorImage).transition(DrawableTransitionOptions.withCrossFade()).centerCrop().into(authorImage)
            quote.text = quoteData.quote
            author.text = quoteData.author

            container.addView(layout)

            return layout
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }

    }

    data class QuoteFinal(val authorImage: String, val quote: String, val author: String)

}

