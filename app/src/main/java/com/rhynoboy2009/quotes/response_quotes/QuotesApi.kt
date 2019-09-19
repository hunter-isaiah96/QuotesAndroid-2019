package com.rhynoboy2009.quotes.response_quotes

import com.rhynoboy2009.quotes.response_ref.RefResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("api/v2")
    fun getRef() : Observable<RefResponse>

    @GET("api/v2/documents/search")
    fun getQuotes(@Query("ref") ref: String = "") : Observable<QuoteResponse>
}