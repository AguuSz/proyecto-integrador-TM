package com.agus.proyectointegradortm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.databinding.ProductElementBinding
import com.agus.proyectointegradortm.models.Product
import com.bumptech.glide.Glide

class ProductsAdapter(
    private var productList: List<Product>,
    private val listener: ProductListOnClickListener
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_element, viewGroup, false)
        context = viewGroup.context
        return ViewHolder(v)
    }

    // Cuando puebla cada elemento
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.productTitle.text = productList[i].title

        val priceText = "$ " + productList[i].price.toString()
        viewHolder.productPrice.text = priceText
        Glide.with(context).load(productList[i].imageURL).into(viewHolder.productImage)

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ProductElementBinding.bind(itemView)

        var productTitle: TextView
        var productPrice: TextView
        var productImage: ImageView

        init {
            productTitle = binding.tvProductTitle
            productPrice = binding.tvProductPrice
            productImage = binding.ivProductImage
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }

    interface ProductListOnClickListener {
        fun onItemClick(position: Int)
    }

}