package khamroev001.e_learn.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.HomeMentorItemBinding
import khamroev001.e_learn.utils.AnimHelper
import khamroev001.e_learn.utils.Mentor


class MentorsRecyclerAdapter(
    var mentorsList: ArrayList<khamroev001.e_learn.utils.Mentor>,
    private val animHelper: AnimHelper,
    private val context: Context,
    val onPressed: OnPressed
) : RecyclerView.Adapter<MentorsRecyclerAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.home_mentor_item_image)
        val namE = itemView.findViewById<TextView>(R.id.home_mentor_item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            HomeMentorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun getItemCount(): Int {
        return mentorsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mentor = mentorsList[position]
        holder.image.load(mentor.img){
            placeholder(R.drawable.img)
            error(R.drawable.no_internet)
            transformations(CircleCropTransformation())
        }
        holder.namE.text = mentor.firstName
        holder.itemView.setOnClickListener {
            animHelper.animate(
                context,
                holder.itemView,
                R.anim.button_press_anim,
                object : AnimHelper.EndAction {
                    override fun endAction() {
                        onPressed.onPressed(mentor)
                    }

                })
        }
    }
    interface OnPressed{
        fun onPressed(mentor: Mentor)
    }

}