package com.agus.proyectointegradortm.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agus.proyectointegradortm.R

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    val titles = arrayOf("Air Force 1", "Jordan 1 High", "Jordan 1 Low", "SB Dunks")
    val prices = arrayOf("120", "175", "120", "100")
    val images = intArrayOf(R.drawable.no_img_avaliable, R.drawable.no_img_avaliable, R.drawable.no_img_avaliable, R.drawable.no_img_avaliable)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_element, viewGroup, false)
        return ViewHolder(v)
    }

    // Cuando puebla cada elemento
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemPrice.text = "$ " + prices[i]
        viewHolder.itemImage.setImageResource(images[i])

    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrice: TextView

        init {
            itemImage = itemView.findViewById(R.id.ivIcon)
            itemTitle = itemView.findViewById(R.id.tvProductTitle)
            itemPrice = itemView.findViewById(R.id.tvProductPrice)
        }
    }

}