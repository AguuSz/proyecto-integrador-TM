package com.agus.proyectointegradortm.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.agus.proyectointegradortm.db.APIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class CategoryViewModel(application: Application): AndroidViewModel(application) {
    fun getCategoriesList(): List<String> {
        var categoriesList = emptyList<String>()
        runBlocking(Dispatchers.IO) {
            val categories = APIClient.service.getCategories()
            val body = categories.execute().body()
            if (body != null) {
                categoriesList = body.categories
            }
        }
        return categoriesList
    }
}