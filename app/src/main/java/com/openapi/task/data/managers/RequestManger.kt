package com.openapi.task.data.managers
import com.openapi.task.application.AppApplication
import com.openapi.task.data.remote.ApiServices
import com.openapi.task.data.remote.Resource
import com.openapi.task.utilities.Utils
import okhttp3.ResponseBody
import javax.inject.Inject

class RequestManger {
    var apiServices: ApiServices

    @Inject
    constructor(apiServices: ApiServices) {
        this.apiServices = apiServices
    }

    suspend fun GenricGETRequest(
        apiName: String,
    ): Resource<ResponseBody> {
        return if (Utils.isInternetConnected(AppApplication.getInstance())) {
            return try {
                Resource.loading("")
                val responseBody = apiServices.getRequest(apiName)
                if (responseBody.isSuccessful) {
                    Resource.success(responseBody.body()!!)
                } else {
                    Resource.failed(responseBody.body()!!)
                }
            } catch (e: Exception) {
                Resource.failed(null)
            }
        } else {
            Resource.noInterNet()
        }
    }

}