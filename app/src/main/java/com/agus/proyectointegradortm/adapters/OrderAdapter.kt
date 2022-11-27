package com.agus.proyectointegradortm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.databinding.OrderElementBinding
import com.agus.proyectointegradortm.models.Order
import com.bumptech.glide.Glide

class OrderAdapter(
    private val listener: OrderListOnClickListener
    ): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    private var orderList = emptyList<Order>()
    private lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.order_element, viewGroup, false)
        context = viewGroup.context
        return ViewHolder(v)
    }

    // Cuando puebla cada elemento
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.orderID.text = "Orden ID: " + orderList[i].id.toString()
        viewHolder.orderAddress.text = orderList[i].address
        viewHolder.orderPrice.text = "$ " + orderList[i].price.toString()
        viewHolder.orderDate.text = orderList[i].date
        Glide.with(context).load("https://icon-library.com/images/order-icon/order-icon-18.jpg").into(viewHolder.orderImage)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    fun updateOrderList(orderList: List<Order>) {
        this.orderList = orderList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = OrderElementBinding.bind(itemView)

        var orderID: TextView
        var orderImage: ImageView
        var orderAddress: TextView
        var orderPrice: TextView
        var orderDate: TextView

        init {
            orderID = binding.tvOrderID
            orderAddress = binding.tvOrderAddress
            orderPrice = binding.tvOrderPrice
            orderDate = binding.tvOrderDate
            orderImage = binding.ivOrderImage

            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }

    interface OrderListOnClickListener {
        fun onItemClick(position: Int)
    }
}