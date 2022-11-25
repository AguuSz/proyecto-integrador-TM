package com.agus.proyectointegradortm.repositories

import androidx.lifecycle.LiveData
import com.agus.proyectointegradortm.daos.ProductDAO
import com.agus.proyectointegradortm.models.Product

class ProductRepository(private val productDAO: ProductDAO) {
    val getAll: LiveData<List<Product>> = productDAO.getAll()

    suspend fun getProductsByType(type: Int): List<Product> {
        return productDAO.getProductsByType(type)
    }

    suspend fun getProductById(id: Int): Product {
        return productDAO.getProductById(id)
    }

    suspend fun addProduct(product: Product) {
        productDAO.addProduct(product)
    }

    suspend fun addProduct(products: List<Product>) {
        productDAO.addProduct(products)
    }

    suspend fun updateProduct(product: Product) {
        productDAO.updateProduct(product)
    }

    suspend fun delete(product: Product) {
        productDAO.delete(product)
    }
}