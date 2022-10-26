package com.agus.proyectointegradortm.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.databinding.ActivityItemsBinding
import com.agus.proyectointegradortm.fragments.ProductDetailFragment
import com.agus.proyectointegradortm.fragments.ProductListFragment
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.utils.Communicator

class ItemsActivity : AppCompatActivity(), Communicator {
    private lateinit var binding: ActivityItemsBinding
    private var isDetailShown: Boolean = false
    private lateinit var productListFragment: ProductListFragment

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

        productListFragment = ProductListFragment()
        supportFragmentManager.beginTransaction().replace(binding.fragmentItems.id, productListFragment).commit()
    }

    override fun passDataCom(product: Product) {
        val bundle = Bundle()
        bundle.putParcelable("selectedProduct", product)

        val productDetailFragment = ProductDetailFragment()
        productDetailFragment.arguments = bundle
        replaceFragment(productDetailFragment)
        isDetailShown = true
    }

    override fun onBackPressed() {
        if (isDetailShown) {
            val productDetailFragment = ProductListFragment()
            replaceFragment(productDetailFragment)
            isDetailShown = false
        } else {
            super.onBackPressed()
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentItems.id, fragment)
        transaction.commit()
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
                productListFragment.onQueryChange(newText.toString())
                return false
            }
        })
        return true
    }
}