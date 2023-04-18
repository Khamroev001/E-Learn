package khamroev001.e_learn.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import khamroev001.e_learn.R
import khamroev001.e_learn.model.Btn_Courses

class Btn_CoursesAdapter(var context: Context, private val itemList: MutableList<Btn_Courses>) : RecyclerView.Adapter<Btn_CoursesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return(ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_btn_courses, parent,false)))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        if (item.isPressed){
            holder.name.setBackgroundResource(R.drawable.bg_item_btn_pressed)
            holder.name.setTextColor(Color.parseColor("#466DFA"))
        }else {
            holder.name.setBackgroundResource(R.drawable.bg_item_btn)
            holder.name.setTextColor(Color.parseColor("#FFFFFF"))
        }
        holder.name.text=item.name

        holder.name.setOnClickListener {

            if (position==0){
              if (item.isPressed){
                   item.isPressed=false
                  if (item.isPressed){
                      item.isPressed=false
                      holder.name.setBackgroundResource(R.drawable.bg_item_btn_pressed)
                      holder.name.setTextColor(Color.parseColor("#466DFA"))
                  }else {
                      item.isPressed=true
                      holder.name.setBackgroundResource(R.drawable.bg_item_btn)
                      holder.name.setTextColor(Color.parseColor("#FFFFFF"))
                  }
                  notifyDataSetChanged()
                  return@setOnClickListener
              }
                if (!item.isPressed){
                    for(i in itemList){
                        i.isPressed=false
                    }
                    item.isPressed=true
                    if (item.isPressed){
                        item.isPressed=false
                        holder.name.setBackgroundResource(R.drawable.bg_item_btn_pressed)
                        holder.name.setTextColor(Color.parseColor("#466DFA"))
                    }else {
                        item.isPressed=true
                        holder.name.setBackgroundResource(R.drawable.bg_item_btn)
                        holder.name.setTextColor(Color.parseColor("#FFFFFF"))
                    }
                    notifyDataSetChanged()
                    return@setOnClickListener
                }
            }


                if (item.isPressed){
                    for(i in itemList){
                    i.isPressed=false
                }
                    item.isPressed=false
                    itemList[0].isPressed=true


                    if (item.isPressed){
                        item.isPressed=false
                        holder.name.setBackgroundResource(R.drawable.bg_item_btn_pressed)
                        holder.name.setTextColor(Color.parseColor("#466DFA"))
                    }else {
                        item.isPressed=true
                        holder.name.setBackgroundResource(R.drawable.bg_item_btn)
                        holder.name.setTextColor(Color.parseColor("#FFFFFF"))
                    }

                    notifyDataSetChanged()
                }else{
                    for(i in itemList){
                        i.isPressed=false
                    }
                    itemList[0].isPressed=false
                    item.isPressed=true

                    if (item.isPressed){
                        item.isPressed=false
                        holder.name.setBackgroundResource(R.drawable.bg_item_btn_pressed)
                        holder.name.setTextColor(Color.parseColor("#466DFA"))
                    }else {
                        item.isPressed=true
                        holder.name.setBackgroundResource(R.drawable.bg_item_btn)
                        holder.name.setTextColor(Color.parseColor("#FFFFFF"))
                    }

                    notifyDataSetChanged()
                }

            }

            if (item.isPressed){
                item.isPressed=false
                holder.name.setBackgroundResource(R.drawable.bg_item_btn_pressed)
                holder.name.setTextColor(Color.parseColor("#466DFA"))
            }else {
                item.isPressed=true
                holder.name.setBackgroundResource(R.drawable.bg_item_btn)
                holder.name.setTextColor(Color.parseColor("#FFFFFF"))
            }

        }


    }
