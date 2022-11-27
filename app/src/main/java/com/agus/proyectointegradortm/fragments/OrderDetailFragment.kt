package com.agus.proyectointegradortm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.adapters.ProductsAdapter
import com.agus.proyectointegradortm.databinding.FragmentProductListBinding
import com.agus.proyectointegradortm.models.Order
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.viewModels.OrderViewModel
import com.agus.proyectointegradortm.viewModels.ProductViewModel
import java.util.regex.Pattern

class OrderDetailFragment : Fragment(), ProductsAdapter.ProductListOnClickListener {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private var selectedOrder: Order? = null
    private lateinit var adapter: ProductsAdapter
    private var productList = emptyList<Product>()
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        selectedOrder = arguments?.getParcelable<Order>("selectedOrder")

        val recyclerView = binding.rvProducts
        adapter = ProductsAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val string = selectedOrder?.products
        if (string != null) {
            val productsIDs: List<String> = Pattern.compile(",").split(string).toList()
            var product: Product
            var productList: MutableList<Product> = ArrayList()
            productsIDs.forEach { productId ->
                product = productViewModel.getProductById(productId.toInt())
                productList.add(product)
            }
            adapter.updateProductList(productList)
        }

        return binding.root
    }

    override fun onItemClick(position: Int) {
    }
}