package com.agus.proyectointegradortm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.adapters.ProductsAdapter
import com.agus.proyectointegradortm.databinding.ActivityHistoryBinding
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.providers.ShoeProvider

class HistoryActivity : AppCompatActivity(), ProductsAdapter.ProductListOnClickListener {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyProductList: MutableList<Product>
    private lateinit var adapter: ProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = "Historial"

        // RecyclerView handling
        val recyclerView = binding.rvHistoryProducts
        // TODO: Este provider de las zapatillas esta puesto de modo temporal solamente para tener items
        historyProductList = ShoeProvider.shoeList.toMutableList()
        adapter = ProductsAdapter(historyProductList, this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun onItemClick(position: Int){
    }

}