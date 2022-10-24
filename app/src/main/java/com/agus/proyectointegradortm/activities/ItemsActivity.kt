package com.agus.proyectointegradortm.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.adapters.ProductsAdapter
import com.agus.proyectointegradortm.databinding.ActivityItemsBinding
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.providers.ShirtProvider
import com.agus.proyectointegradortm.providers.ShoeProvider
import com.google.android.material.snackbar.Snackbar

class ItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemsBinding
    private lateinit var productList: MutableList<Product>
    private lateinit var adapter: ProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Seteamos el titulo de la activity con el tipo de producto que fue clickeado
        val title = intent.getStringExtra("type")
        if (title != null) {
            val actionBar = supportActionBar
            actionBar?.title = title
        }

        val recyclerView = binding.rvProducts

        // TODO: Cambiar esto por cada uno de los diferentes providers dependiendo del tipo de producto
        productList = when (title) {
            "Zapatillas" -> ShoeProvider.shoeList.toMutableList()
            "Remeras" -> ShirtProvider.shirtList.toMutableList()

            else -> {ShoeProvider.shoeList.toMutableList()}
        }
        adapter = ProductsAdapter(productList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
        }

        // Floating action button
        val floatingActionButton = binding.fabShoppingCart
        floatingActionButton.setOnClickListener {
            Snackbar.make(binding.root, "El carrito se implementara pronto!", Snackbar.LENGTH_SHORT).show()
        }

    }

    // Funcion encargada de manejar el icono de busqueda
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            // Cuando clickeo enter
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            // Cuando el texto va cambiando
            override fun onQueryTextChange(newText: String?): Boolean {
                val productListFiltered = productList.filter { product -> product.title.contains(newText.toString()) }
                adapter.updateProductList(productListFiltered)
                return false
            }
        })

        return true
    }
}