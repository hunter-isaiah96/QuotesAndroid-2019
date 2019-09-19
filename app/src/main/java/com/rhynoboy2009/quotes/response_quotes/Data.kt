package com.rhynoboy2009.quotes.response_quotes

data class Data(
    val author: List<Author>,
    val author_image: AuthorImage,
    val quote: List<QuoteResponseX>
)