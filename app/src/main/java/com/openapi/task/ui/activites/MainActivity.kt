package com.openapi.task.ui.activites

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.openapi.task.R
import com.openapi.task.data.models.ProductsModel
import com.openapi.task.data.remote.ApiStatus
import com.openapi.task.data.remote.SharedPrefConstants
import com.openapi.task.data.repositories.PrefRepository
import com.openapi.task.ui.adapters.ProductsRecyclerViewAdapter
import com.openapi.task.viewmodels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val userViewModel: ProductsViewModel by viewModels()
    var productsArray: List<ProductsModel.Product?>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerviewViewProductsData()
        fetchProducthData()
        swiper.setOnRefreshListener {
            fetchProducthData()
        }
    }

    fun fetchProducthData() {
        userViewModel.fetchProductData().observe(this, Observer {
            when (it.status) {
                ApiStatus.LOADING -> {
                    swiper.isRefreshing = true
                }
                ApiStatus.SUCCESS -> {
                    try {
                        val gson = Gson()
                        val productData = gson.fromJson(
                            it.data?.string(),
                            ProductsModel::class.java
                        )
                        var prefRepository = PrefRepository(this)
                        prefRepository.putSharedPreferencesObject(
                            SharedPrefConstants.PRODUCT_OBJECT,
                            productData
                        )
                        setRecyclerviewViewProductsData()
                        swiper.isRefreshing = false
                    } catch (e: Exception) {
                        e.toString()
                    }
                }
                ApiStatus.FAILED -> {
                    swiper.isRefreshing = false
                    Toast.makeText(this, getString(R.string.error_api_failed), Toast.LENGTH_LONG)
                        .show()
                }
                ApiStatus.NOINTERNET -> {
                    swiper.isRefreshing = false
                    Toast.makeText(this, getString(com.openapi.task.R.string.error_no_internet), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    fun setRecyclerviewViewProductsData() {
        var prefRepository = PrefRepository(this)
        val cachedData = prefRepository.getSharedPreferencesObject(
            SharedPrefConstants.PRODUCT_OBJECT,
            ProductsModel::class.java
        )
        if (cachedData != null) {
            cachedData as ProductsModel
            productsArray = ArrayList()
            productsArray = cachedData.getProducts()
            productRecyclerView.layoutManager = LinearLayoutManager(this)
            productRecyclerView.adapter = ProductsRecyclerViewAdapter(this, productsArray,object : ProductsRecyclerViewAdapter.ShareClickListener{
                override fun onShareClick(dataObject: ProductsModel.Product?) {
                    onShare(dataObject)
                }
            })
        }

    }
    fun onShare(dataObject: ProductsModel.Product?)
    {
        var sharedText = dataObject?.title +" \n"+dataObject?.description +" \n"
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, sharedText);
        startActivity(Intent.createChooser(shareIntent,""))
    }

}