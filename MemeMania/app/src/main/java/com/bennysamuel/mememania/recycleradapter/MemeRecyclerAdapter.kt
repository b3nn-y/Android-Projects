package com.bennysamuel.mememania.recycleradapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.mememania.Meme
import com.bennysamuel.mememania.Memes
import com.bennysamuel.mememania.R
import com.bumptech.glide.Glide

class MemeRecyclerAdapter(private var memes: ArrayList<Memes>): RecyclerView.Adapter<MemeRecyclerAdapter.MemeViewHolder>() {

    var onItemClick: ((Memes) -> Unit)? = null
    class MemeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val memeImage: ImageView = itemView.findViewById(R.id.memeImage)
        val memeText: TextView = itemView.findViewById(R.id.memeText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return MemeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memes.size
    }
    override fun getItemViewType(position: Int): Int  = position

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val meme = memes[position]
        Glide.with(holder.itemView.context)
            .load(meme.url)
//            .error(R.drawable.error)
//            .placeholder(R.drawable.load)
            .into(holder.memeImage)

        holder.memeText.text = meme.title

        holder.memeImage.setOnClickListener {
            onItemClick?.invoke(meme)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: ArrayList<Memes>){
        memes = newList
        notifyDataSetChanged()
    }

}