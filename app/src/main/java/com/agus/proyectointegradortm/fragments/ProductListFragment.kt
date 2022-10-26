package com.agus.proyectointegradortm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.adapters.ProductsAdapter
import com.agus.proyectointegradortm.databinding.FragmentProductListBinding
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.providers.ShoeProvider
import com.agus.proyectointegradortm.utils.Communicator
import com.google.android.material.snackbar.Snackbar

class ProductListFragment : Fragment(), ProductsAdapter.ProductListOnClickListener {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductsAdapter
    private lateinit var productList: MutableList<Product>
    private lateinit var communicator: Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProductListBinding.inflate(inflater, container, false)

        productList = ShoeProvider.shoeList.toMutableList()
//        var productList = when (title) {
//            "Zapatillas" -> ShoeProvider.shoeList.toMutableList()
//            "Remeras" -> ShirtProvider.shirtList.toMutableList()
//
//            else -> {
//                ShoeProvider.shoeList.toMutableList()
//            }
//        }

        val recyclerView = binding.rvProducts
        adapter = ProductsAdapter(productList, this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        communicator = activity as Communicator

        // Floating action button
        val floatingActionButton = binding.fabShoppingCart
        floatingActionButton.setOnClickListener {
            Snackbar.make(binding.root, "El carrito se implementara pronto!", Snackbar.LENGTH_SHORT).show()
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
}