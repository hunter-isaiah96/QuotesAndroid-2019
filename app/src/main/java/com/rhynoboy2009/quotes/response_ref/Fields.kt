package com.rhynoboy2009.quotes.response_ref

data class Fields(
    val after: After,
    val fetch: Fetch,
    val fetchLinks: FetchLinks,
    val graphQuery: GraphQuery,
    val lang: Lang,
    val orderings: Orderings,
    val page: Page,
    val pageSize: PageSize,
    val q: Q,
    val ref: RefX,
    val referer: Referer
)