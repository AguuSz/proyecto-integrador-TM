package com.agus.proyectointegradortm.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.adapters.OrderAdapter
import com.agus.proyectointegradortm.databinding.ActivityOrderBinding
import com.agus.proyectointegradortm.models.Order
import com.agus.proyectointegradortm.viewModels.OrderViewModel

class OrderActivity : AppCompatActivity(), OrderAdapter.OrderListOnClickListener {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var adapter: OrderAdapter
    private var orderList = emptyList<Order>()
    private lateinit var orderViewModel: OrderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = "Historial de pedidos"

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        val recyclerView = binding.rvOrderProducts
        adapter = OrderAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        orderViewModel.getAllOrders.observe(this, Observer {orders ->
            orderList = orders
            adapter.updateOrderList(orders)
            Log.d("testeo", orderList.size.toString())
        })

    }

    override fun onItemClick(position: Int){
        Log.d("testeo", "asdasd")
    }

}