package com.openapi.task.data.remote

class Resource<out T>(val status: ApiStatus, val data: T?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = ApiStatus.SUCCESS, data = data)

        fun <T> noInterNet(): Resource<T> =
            Resource(status = ApiStatus.NOINTERNET, data =  null)

        fun <T> failed(data: T?): Resource<T> =
            Resource(status = ApiStatus.FAILED, data = data)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = ApiStatus.LOADING, data = data)
    }

}