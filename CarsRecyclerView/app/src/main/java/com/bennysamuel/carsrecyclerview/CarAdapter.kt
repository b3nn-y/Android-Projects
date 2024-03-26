package com.bennysamuel.carsrecyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(private val carList: ArrayList<Car>): RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    class CarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.carImg)
        val layout: LinearLayout = itemView.findViewById(R.id.carCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = carList[position]
        holder.imageView.setImageResource(car.carImage)
        holder.layout.setBackgroundColor(Color.parseColor(car.Background))
    }
}