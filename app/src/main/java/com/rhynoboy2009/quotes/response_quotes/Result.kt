package com.rhynoboy2009.quotes.response_quotes

data class Result(
    val `data`: Data,
    val alternate_languages: List<Any>,
    val first_publication_date: String,
    val href: String,
    val id: String,
    val lang: String,
    val last_publication_date: String,
    val linked_documents: List<Any>,
    val slugs: List<String>,
    val tags: List<Any>,
    val type: String,
    val uid: Any
)