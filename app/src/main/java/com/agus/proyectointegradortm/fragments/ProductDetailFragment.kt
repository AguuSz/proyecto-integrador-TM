package com.agus.proyectointegradortm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.agus.proyectointegradortm.MyApplication
import com.agus.proyectointegradortm.databinding.FragmentProductDetailBinding
import com.agus.proyectointegradortm.models.Order
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.viewModels.OrderViewModel
import com.bumptech.glide.Glide

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private var selectedProduct: Product? = null
    private lateinit var orderViewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        selectedProduct = arguments?.getParcelable<Product>("selectedProduct")

        binding.tvProductTitle.text = selectedProduct?.title
        binding.tvProductDescription.text = selectedProduct?.description
        val price = "$ " + selectedProduct?.price.toString()
        binding.tvProductPrice.text = price
        if (container != null) {
            Glide.with(container.context).load(selectedProduct?.imageURL)
                .into(binding.ivProductImage)
        }

        binding.btnAddToCart.setOnClickListener {
            selectedProduct?.let { product -> MyApplication.cart.add(product) }
            Toast.makeText(context, "Añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}