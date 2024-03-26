package com.bennysamuel.carsrecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.carsrecyclerview.databinding.FragmentRecycleBinding

class RecycleFragment : Fragment() {
    private lateinit var binding: FragmentRecycleBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var carList: ArrayList<Car>
    private lateinit var carAdapter: CarAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecycleBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.carList
        carList = ArrayList()

        carList.add(Car(R.drawable.ferrari,"#EFEFEFEF"))
        carList.add(Car(R.drawable.porche,"#CDF1A2"))
        carList.add(Car(R.drawable.rolls,"#EFEFEFEF"))
        carList.add(Car(R.drawable.lamborghini,"#CDF1A2"))
        carList.add(Car(R.drawable.porche,"#EFEFEFEF"))
        carList.add(Car(R.drawable.ferrari,"#EFEFEFEF"))
        carList.add(Car(R.drawable.porche,"#CDF1A2"))
        carList.add(Car(R.drawable.rolls,"#EFEFEFEF"))
        carList.add(Car(R.drawable.lamborghini,"#CDF1A2"))
        carList.add(Car(R.drawable.porche,"#EFEFEFEF"))
        carList.add(Car(R. drawable.ferrari,"#EFEFEFEF"))
        carList.add(Car(R.drawable.porche,"#CDF1A2"))
        carList.add(Car(R.drawable.rolls,"#EFEFEFEF"))
        carList.add(Car(R.drawable.lamborghini,"#CDF1A2"))
        carList.add(Car(R.drawable.porche,"#EFEFEFEF"))
        carList.add(Car(R.drawable.ferrari,"#EFEFEFEF"))
        carList.add(Car(R.drawable.porche,"#CDF1A2"))
        carList.add(Car(R.drawable.rolls,"#EFEFEFEF"))
        carList.add(Car(R.drawable.lamborghini,"#CDF1A2"))
        carList.add(Car(R.drawable.porche,"#EFEFEFEF"))

        carAdapter = CarAdapter(carList)
        recyclerView.adapter = carAdapter

    }


}