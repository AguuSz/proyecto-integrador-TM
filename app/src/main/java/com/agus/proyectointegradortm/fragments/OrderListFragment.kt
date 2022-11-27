package com.agus.proyectointegradortm.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.adapters.OrderAdapter
import com.agus.proyectointegradortm.databinding.FragmentOrderListBinding
import com.agus.proyectointegradortm.models.Order
import com.agus.proyectointegradortm.utils.Communicator
import com.agus.proyectointegradortm.viewModels.OrderViewModel

class OrderListFragment() : Fragment(), OrderAdapter.OrderListOnClickListener {

    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: OrderAdapter
    private var orderList = emptyList<Order>()
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        val recyclerView = binding.rvOrderList
        adapter = OrderAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        communicator = activity as Communicator

        orderViewModel.getAllOrders.observe(viewLifecycleOwner, Observer {orders ->
            orderList = orders
            adapter.updateOrderList(orders)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        val selectedOrder = orderList[position]
        communicator.passDataCom(selectedOrder)
    }
}