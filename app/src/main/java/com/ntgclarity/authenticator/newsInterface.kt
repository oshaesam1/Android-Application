package com.ntgclarity.authenticator

import com.ntgclarity.authenticator.api.NewsJson
import retrofit2.http.GET

interface newsInterface {
    @GET("/v1/latest-news?language=en&apiKey=eXTk8eKNMv_GmiufuRQLn_8VBF4UdAB8SJR_3Wm7xlrB7Ow_")
    suspend fun getallNews():   NewsJson //return news
}