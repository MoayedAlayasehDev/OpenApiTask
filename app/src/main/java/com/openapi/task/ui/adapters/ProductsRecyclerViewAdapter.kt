package com.openapi.task.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openapi.task.R
import com.openapi.task.data.models.ProductsModel
import kotlinx.android.synthetic.main.adapter_products.view.*

public class ProductsRecyclerViewAdapter(
    val mcontext: Context,
    val productsArray: List<ProductsModel.Product?>?,
    val onShareClick : ShareClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class ProductsRecyclerViewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mcontext)
            .inflate(R.layout.adapter_products, parent, false)
        return ProductsRecyclerViewAdapter(view)
    }
    override fun getItemCount(): Int {
        if (productsArray.isNullOrEmpty())
        {
            return 0
        }
        return productsArray.size
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            holder.itemView.productTitle.text = productsArray?.get(position)?.title.toString()
            holder.itemView.productDetails.text = productsArray?.get(position)?.description.toString()
            holder.itemView.productCategory.text = productsArray?.get(position)?.category.toString()
            holder.itemView.shareBtn.setOnClickListener {
                onShareClick.onShareClick(productsArray?.get(position))
            }
        } catch (e: Exception) {
        }
    }
    interface ShareClickListener
    {
        fun onShareClick(get: ProductsModel.Product?)
    }
}



