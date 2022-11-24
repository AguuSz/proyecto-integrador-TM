package com.agus.proyectointegradortm.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.adapters.CartAdapter
import com.agus.proyectointegradortm.databinding.ActivityCartBinding
import com.agus.proyectointegradortm.models.Product

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartProductList: List<Product>
    private lateinit var adapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = "Carrito de compras"

        // RecyclerView handling
        val recyclerView = binding.rvCartProducts
        // TODO: Este provider de las zapatillas esta puesto de modo temporal solamente para tener items
        cartProductList = emptyList<Product>()
//        cartProductList = ShoeProvider.shoeList.toMutableList()
        adapter = CartAdapter(cartProductList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Ir a pagar boton
        var cartTotal = 0
        cartProductList.forEach { product ->
            cartTotal += product.price
        }
        binding.fabPay.text = "Pagar ahora: $" + cartTotal.toString()
        binding.fabPay.setOnClickListener {
            Toast.makeText(this, "La funcion de ir a pagar se implementara pronto!", Toast.LENGTH_SHORT).show()
        }

    }
}