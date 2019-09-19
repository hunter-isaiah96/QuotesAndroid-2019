package com.rhynoboy2009.quotes

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager

class ParallaxTransformer: ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val authorImage = page.findViewById<ImageView>(R.id.author_image)
        val quote = page.findViewById<TextView>(R.id.quote)
        val author = page.findViewById<TextView>(R.id.author)

//        when {
//            position < -1 -> page.alpha = 1f
//            position <= 1 -> {
//                authorImage.translationX = -position * (pageWidth / 2)
//                quote.translationX = position * (pageWidth / 2)
//                author.translationX = position * (pageWidth / 1.5f)
//            }
//            else -> page.alpha = 1f
//        }

        if (position <= 1) {
            authorImage.translationX = -position * (pageWidth /2)
            quote.translationX = position * (pageWidth / 2)
            author.translationX = position * (pageWidth / 1.5f)
        }


//        when {
//            position < -1 -> page.alpha = 1f
//            position <=
//        }

//        if(position < -1)
//            page.alpha = 1f
//        else if(position <= 1) {
//            authorImage.translationX = -position * (pageWidth /2)
//            quote.translationX = position * (pageWidth / 2)
//            authorImage.translationX = position * (pageWidth / 2)
//        } else {
//            page.alpha = 1
//        }
    }

}