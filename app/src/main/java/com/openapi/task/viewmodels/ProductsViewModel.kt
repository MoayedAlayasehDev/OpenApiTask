package com.openapi.task.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.openapi.task.data.remote.Resource
import com.openapi.task.data.repositories.ProductsImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel : ViewModel {
    var productsImpl: ProductsImpl
    var userResponse: MutableLiveData<Resource<ResponseBody>>

    @Inject
    constructor(productsImpl: ProductsImpl) {
        this.productsImpl = productsImpl
        userResponse = MutableLiveData(Resource.loading(null))
    }

    fun fetchProductData(
    ): LiveData<Resource<ResponseBody>> {
        userResponse = MutableLiveData(Resource.loading(null))
        viewModelScope.launch {
            userResponse.postValue(
                productsImpl.getProducts()
            )
        }
        return userResponse
    }

}