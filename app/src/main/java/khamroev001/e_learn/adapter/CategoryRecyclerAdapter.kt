package khamroev001.e_learn

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import khamroev001.e_learn.databinding.HomeCategoryItemBinding

import khamroev001.e_learn.utils.Category


class CategoryRecyclerAdapter(val onCategoryChanged: OnCategoryChanged) : RecyclerView.Adapter<CategoryRecyclerAdapter.MyViewHolder>() {

    var selectedPos = 0
    var old = 0

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val innerCard: CardView = itemView.findViewById(R.id.home_category_item_innerCard)
        val categoryName: TextView = itemView.findViewById(R.id.home_category_item_category_name)
        val primaryColor = categoryName.currentTextColor
        val whiteColor = innerCard.cardBackgroundColor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            HomeCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun getItemCount(): Int {
        return Category.values().size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == 0) {
            holder.categoryName.text = "All"
            if (selectedPos == 0) {
                holder.categoryName.setTextColor(holder.whiteColor)
                holder.innerCard.setCardBackgroundColor(holder.primaryColor)
            }else{
                holder.categoryName.setTextColor(holder.primaryColor)
                holder.innerCard.setCardBackgroundColor(holder.whiteColor)
            }
        } else {
            val category = Category.values()[position-1]
            holder.categoryName.text = category.namE
            if (selectedPos == position) {
                holder.categoryName.setTextColor(holder.whiteColor)
                holder.innerCard.setCardBackgroundColor(holder.primaryColor)
            }else{
                holder.categoryName.setTextColor(holder.primaryColor)
                holder.innerCard.setCardBackgroundColor(holder.whiteColor)
            }
        }
        holder.itemView.setOnClickListener {
            if (position != selectedPos){
                old = selectedPos
                selectedPos = position
                notifyItemChanged(selectedPos)
                notifyItemChanged(old)
                if (position == 0){
                    onCategoryChanged.onCategoryChanged(null)
                }else{
                    onCategoryChanged.onCategoryChanged(Category.values()[position-1])
                }
            }


        }
    }
    interface OnCategoryChanged{
        fun onCategoryChanged(category: Category?)
    }
}