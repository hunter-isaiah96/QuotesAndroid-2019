package com.rhynoboy2009.quotes.response_ref

data class RefResponse(
    val bookmarks: Bookmarks,
    val experiments: Experiments,
    val forms: Forms,
    val languages: List<Language>,
    val license: String,
    val oauth_initiate: String,
    val oauth_token: String,
    val refs: List<Ref>,
    val tags: List<Any>,
    val types: Types,
    val version: String
)