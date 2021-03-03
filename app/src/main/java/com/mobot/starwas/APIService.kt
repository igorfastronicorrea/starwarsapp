package com.mobot.starwas

import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("people")
    fun list() : Call<People>
}