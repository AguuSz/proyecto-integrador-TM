package com.agus.proyectointegradortm.adapters

import android.content.Context
import android.media.Image
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

class CartAdapter (private var productList: List<Product>): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    var onItemClick: ((Product) -> Unit)? = null
    private lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cart_element, viewGroup, false)
        context = viewGroup.context
        return ViewHolder(v)
    }

    // Cuando puebla cada elemento
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.productTitle.text = productList[i].title

        val priceText = "$ " + productList[i].price.toString()
        viewHolder.productPrice.text = priceText
        Glide.with(context).load(productList[i].imageURL).into(viewHolder.productImage)
        viewHolder.itemView.setOnClickListener {
            onItemClick?.invoke(productList[i])
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(productList: List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ProductElementBinding.bind(itemView)

        var productTitle: TextView
        var productPrice: TextView
        var productImage: ImageView

        init {
            productTitle = binding.tvProductTitle
            productPrice = binding.tvProductPrice
            productImage = binding.ivProductImage
        }
    }

}