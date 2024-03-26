package com.bennysamuel.whatsmynextcountry.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.whatsmynextcountry.R
import com.bumptech.glide.Glide

class CountryAdapter(private val countryList: ArrayList<CountryRecycleViewData>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    var onItemClick: ((CountryRecycleViewData) -> Unit)? = null
    var onItemSaveClick: ((CountryRecycleViewData, saveState:String) -> Unit)? = null
    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val imageView: ImageView = itemView.findViewById(R.id.countryImage)
        val textView: TextView = itemView.findViewById(R.id.countryName)
        val heartImageView: ImageView = itemView.findViewById(R.id.heart)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countryList[position]
        holder.textView.text = country.countryName
        Glide.with(holder.itemView.context)
            .load(country.countryImageLink)
            .placeholder(R.drawable.load4)
            .error(R.drawable.error2)
            .into(holder.imageView)
        if(country.saved == "false"){
            holder.heartImageView.setImageResource(R.drawable.orangeheart)
        }
        else{
            holder.heartImageView.setImageResource(R.drawable.redheart)
        }
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(country)
        }
        holder.heartImageView.setOnClickListener{
            country.saved = if (country.saved == "false") "true" else "false"
            onItemSaveClick?.invoke(country,country.saved)

            // Update the heart image based on the new saved status
            if (country.saved == "false") {
                holder.heartImageView.setImageResource(R.drawable.orangeheart)
            } else {
                holder.heartImageView.setImageResource(R.drawable.redheart)
            }

            notifyItemChanged(position)
        }

    }
}