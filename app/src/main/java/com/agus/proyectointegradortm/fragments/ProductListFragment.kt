package com.agus.proyectointegradortm.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.activities.CartActivity
import com.agus.proyectointegradortm.adapters.ProductsAdapter
import com.agus.proyectointegradortm.databinding.FragmentProductListBinding
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.utils.Communicator
import com.agus.proyectointegradortm.viewModels.ProductViewModel

class ProductListFragment() :
    Fragment(), ProductsAdapter.ProductListOnClickListener {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductsAdapter
    private var productList = emptyList<Product>()
    private lateinit var productViewModel: ProductViewModel
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProductListBinding.inflate(inflater, container, false)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val recyclerView = binding.rvProducts
        adapter = ProductsAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        communicator = activity as Communicator

        val title = arguments?.getString("title")

        if (getTypeBasedOnTitle(title) == 10) {
            // Productos destacados basados en el historial
            productList = MyApplication.history
        } else {
            productList = productViewModel.getProductsByType(getTypeBasedOnTitle(title))
        }

        adapter.updateProductList(productList)

        // Floating action button
        val floatingActionButton = binding.fabShoppingCart
        floatingActionButton.setOnClickListener {
            val intent = Intent(context, CartActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        val selectedProduct = productList[position]
        communicator.passDataCom(selectedProduct)
    }

    private fun getTypeBasedOnTitle(title: String?): Int {
        val type = when (title) {
            "Zapatillas" -> 5
            "Remeras" -> 4
            "Pantalones" -> 3
            "Buzos" -> 1
            "Camperas" -> 2
            "Destacados" -> 10
            else -> 6
        }
        return type
    }

    fun onQueryChange(query: String) {
        val productListFiltered =
            productList.filter { product -> product.title.lowercase().contains(query.lowercase()) }
        adapter.updateProductList(productListFiltered)
    }

}