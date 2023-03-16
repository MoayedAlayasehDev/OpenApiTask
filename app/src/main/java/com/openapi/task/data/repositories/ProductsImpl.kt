package com.openapi.task.data.repositories
import com.openapi.task.base.BaseReposetory
import com.openapi.task.data.managers.RequestManger
import com.openapi.task.data.remote.EndPoints
import com.openapi.task.data.remote.Resource
import com.openapi.task.domain.ProductsRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class ProductsImpl: ProductsRepository, BaseReposetory {

    @Inject
    constructor(manger: RequestManger){
        this.manger=manger
    }
    override suspend fun getProducts(): Resource<ResponseBody> {
        val callResponse=manger.GenricGETRequest(EndPoints.ENDPONT_PRODUCTS)
        return callResponse
    }

}