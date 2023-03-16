package com.openapi.task.data.remote

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiServices {
    @GET
    suspend fun getRequest(
        @Url apiName: String,
    ): Response<ResponseBody>

}