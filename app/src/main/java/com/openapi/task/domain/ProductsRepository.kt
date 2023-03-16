package com.openapi.task.domain

import com.google.gson.JsonObject
import com.openapi.task.data.remote.Resource
import okhttp3.ResponseBody

interface ProductsRepository {
    suspend fun getProducts(): Resource<ResponseBody>
}