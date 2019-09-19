package com.rhynoboy2009.quotes.response_quotes

data class QuoteResponse(
    val license: String,
    val next_page: Any,
    val page: Int,
    val prev_page: Any,
    val results: List<Result>,
    val results_per_page: Int,
    val results_size: Int,
    val total_pages: Int,
    val total_results_size: Int,
    val version: String
)