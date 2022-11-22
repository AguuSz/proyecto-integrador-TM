package com.agus.proyectointegradortm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agus.proyectointegradortm.R
import com.agus.proyectointegradortm.models.Category
import com.agus.proyectointegradortm.models.Product

class TypesAdapter (private var categoriesList: List<String>): RecyclerView.Adapter<TypesAdapter.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.type_element, viewGroup, false)
        return ViewHolder(v)
    }

    // Cuando puebla cada elemento
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = this.categoriesList[i]

        viewHolder.itemView.setOnClickListener {
            onItemClick?.invoke(this.categoriesList[i])
        }
    }

    override fun getItemCount(): Int {
        return this.categoriesList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.tvProductTitle)
        }
    }

}