package khamroev001.e_learn.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import khamroev001.e_learn.R
import khamroev001.e_learn.databinding.ReviewItemBinding
import khamroev001.e_learn.utils.Review

class ReviewRecyclerAdapter(val reviews:ArrayList<Review>): RecyclerView.Adapter<ReviewRecyclerAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.review_image)
        val namE = itemView.findViewById<TextView>(R.id.review_name)
        val score = itemView.findViewById<TextView>(R.id.review_score)
        val texT = itemView.findViewById<TextView>(R.id.review_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = reviews[position]
        holder.image.load(review.user.image)
        holder.namE.text = review.user.firstName + " " + review.user.lastName
        holder.score.text = review.score.toString()
        holder.texT.text = review.text
    }
}