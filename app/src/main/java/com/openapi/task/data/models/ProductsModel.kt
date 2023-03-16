package com.openapi.task.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ProductsModel : Serializable{
    @SerializedName("products")
    @Expose
    private var products: List<Product>? = null

    @SerializedName("total")
    @Expose
    private var total: Int? = null

    @SerializedName("skip")
    @Expose
    private var skip: Int? = null

    @SerializedName("limit")
    @Expose
    private var limit: Int? = null

    fun getProducts(): List<Product?>? {
        return products
    }

    fun setProducts(products: List<Product>?) {
        this.products = products
    }

    fun getTotal(): Int? {
        return total
    }

    fun setTotal(total: Int?) {
        this.total = total
    }

    fun getSkip(): Int? {
        return skip
    }

    fun setSkip(skip: Int?) {
        this.skip = skip
    }

    fun getLimit(): Int? {
        return limit
    }

    fun setLimit(limit: Int?) {
        this.limit = limit
    }


    class Product  : Serializable{
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("description")
        @Expose
        var description: String? = null

        @SerializedName("price")
        @Expose
        var price: Int? = null

        @SerializedName("discountPercentage")
        @Expose
        var discountPercentage: Double? = null

        @SerializedName("rating")
        @Expose
        var rating: Double? = null

        @SerializedName("stock")
        @Expose
        var stock: Int? = null

        @SerializedName("brand")
        @Expose
        var brand: String? = null

        @SerializedName("category")
        @Expose
        var category: String? = null

        @SerializedName("thumbnail")
        @Expose
        var thumbnail: String? = null

        @SerializedName("images")
        @Expose
        var images: List<String>? = null
    }
}