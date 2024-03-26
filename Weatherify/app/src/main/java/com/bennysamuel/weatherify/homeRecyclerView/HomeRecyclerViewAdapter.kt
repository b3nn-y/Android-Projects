package com.bennysamuel.weatherify.homeRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.weatherify.R
import com.bennysamuel.weatherify.suggestionsRecyclerView.SuggestionsRecyclerData

class HomeRecyclerViewAdapter(private var homeDataList: ArrayList<HomeRecyclerViewData>): RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>() {
    var onItemClick: ((HomeRecyclerViewData) -> Unit)? = null
    class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val imageView: ImageView = itemView.findViewById(R.id.homeImage)
        val temperature: TextView = itemView.findViewById(R.id.homeTemp)
        val humidity: TextView = itemView.findViewById(R.id.homeHumidity)
        val location: TextView = itemView.findViewById(R.id.homeLocation)
        val card: ConstraintLayout = itemView.findViewById(R.id.weatherCard)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_card, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return homeDataList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        var data = homeDataList[position]
        holder.imageView.setImageResource(data.image)
        holder.temperature.text = data.temp.toString() + " deg"
        holder.humidity.text = "Humidity: " + data.humidity
        holder.location.text = data.searchTerm
        holder.card.setOnClickListener {
            onItemClick?.invoke(data)
        }
    }
}