package com.ntgclarity.authenticator.api

import com.ntgclarity.authenticator.api.New

data class NewsJson(
    val news: List<New>,
    val page: Int,
    val status: String
)