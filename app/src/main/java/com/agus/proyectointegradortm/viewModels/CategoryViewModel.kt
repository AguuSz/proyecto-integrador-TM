package com.agus.proyectointegradortm.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.agus.proyectointegradortm.db.CategoryClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class CategoryViewModel(application: Application): AndroidViewModel(application) {
    fun getCategoriesList(): List<String> {
        var categoriesList = emptyList<String>()
        runBlocking(Dispatchers.IO) {
            val categories = CategoryClient.service.getCategories()
            val body = categories.execute().body()
            if (body != null) {
                categoriesList = body.categories
            }
        }
        return categoriesList
    }
}