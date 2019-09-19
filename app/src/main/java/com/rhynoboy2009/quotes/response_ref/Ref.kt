package com.rhynoboy2009.quotes.response_ref

data class Ref(
    val id: String,
    val isMasterRef: Boolean,
    val label: String,
    val ref: String
)