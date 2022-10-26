package com.agus.proyectointegradortm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agus.proyectointegradortm.databinding.FragmentProductDetailBinding
import com.agus.proyectointegradortm.models.Product

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private var selectedProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        selectedProduct = arguments?.getParcelable<Product>("selectedProduct")

        binding.tvProductTitle.text = selectedProduct?.title
        binding.tvProductDescription.text = selectedProduct?.description
        binding.tvProductPrice.text = selectedProduct?.price.toString()

        return binding.root
    }
}