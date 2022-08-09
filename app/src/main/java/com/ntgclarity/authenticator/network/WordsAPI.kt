package com.ntgclarity.authenticator.network

import com.ntgclarity.authenticator.words.Word
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// https://b614dcce-f350-49fb-b6de-71923bb92f84.mock.pstmn.io/v2/everything

interface WordsAPI {
    @GET("v2/everything")
    fun words(): Call<List<Word>>
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://b614dcce-f350-49fb-b6de-71923bb92f84.mock.pstmn.io")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(WordsAPI::class.java)