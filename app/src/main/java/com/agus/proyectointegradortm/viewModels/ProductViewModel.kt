package com.agus.proyectointegradortm.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.agus.proyectointegradortm.db.UserDB
import com.agus.proyectointegradortm.models.Product
import com.agus.proyectointegradortm.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductViewModel(application: Application): AndroidViewModel(application) {
    val getAllProducts: LiveData<List<Product>>
    private val repository: ProductRepository

    init {
        val productDAO = UserDB.getDatabase(application).productDAO()
        repository = ProductRepository(productDAO)
        getAllProducts = repository.getAll
    }

    fun getProductsByType(type: Int): List<Product> {
        var productList = emptyList<Product>()
        runBlocking (Dispatchers.IO) {
            productList = repository.getProductsByType(type)
        }
        return productList
    }

    fun getProductById(id: Int): Product {
        var product: Product
        runBlocking (Dispatchers.IO) {
            product = repository.getProductById(id)
        }
        return product
    }

    fun addProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }

    fun addProduct(products: List<Product>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(products)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduct(product)
        }
    }

    fun delete(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(product)
        }
    }
}