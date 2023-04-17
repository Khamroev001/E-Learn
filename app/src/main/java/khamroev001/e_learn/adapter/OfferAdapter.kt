package khamroev001.e_learn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import khamroev001.e_learn.R
import khamroev001.e_learn.model.Discount_offers

class OfferAdapter(var context: Context, private val itemList: MutableList<Discount_offers>) : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var description: TextView = itemView.findViewById(R.id.describtion)
        var discount_off: TextView = itemView.findViewById(R.id.discount_off)
        var discount: TextView = itemView.findViewById(R.id.discount_size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_offers, parent,false)))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]


        holder.description.text=item.describtion
        holder.discount.text="${item.discount} %"
        holder.discount_off.text="${item.discount} % off"
        holder.title.text=item.title
    }
}