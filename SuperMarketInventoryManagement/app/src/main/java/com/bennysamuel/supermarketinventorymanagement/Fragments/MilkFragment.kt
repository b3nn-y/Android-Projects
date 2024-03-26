package com.bennysamuel.supermarketinventorymanagement.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.supermarketinventorymanagement.R
import com.bennysamuel.supermarketinventorymanagement.databinding.FragmentMilkBinding
import com.bennysamuel.supermarketinventorymanagement.viewmodels.StocksViewModel

class MilkFragment : Fragment() {
    private lateinit var binding: FragmentMilkBinding
    private lateinit var viewModel: StocksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMilkBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(StocksViewModel::class.java)
        savedInstanceState?.let {
            val chValue = it.getString("milk")
            if (chValue != null) {
                viewModel.setMilkValue(chValue.toInt())
            }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener{
            findNavController().navigate(R.id.action_milkFragment_to_itemsFragment)
        }
        viewModel.milkStock.observe(viewLifecycleOwner, Observer { value ->
            binding.milkAmount.text = value.toString()

        })
        binding.incStock.setOnClickListener {
            viewModel.incMilk()
//            println("Inc")
        }
        binding.decStock.setOnClickListener {
            viewModel.decMilk()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("milk",viewModel.milkStock.toString())
        println("OnSaveInstance is called")
    }

    override fun onStart() {
        super.onStart()
        println("OnStart is called")
    }

    override fun onStop() {
        super.onStop()
        println("OnStop is called")
    }

    override fun onResume() {
        super.onResume()
        println("OnResume is called")
    }

    override fun onPause() {
        super.onPause()
        println("OnPause is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("OnDestroy is called")
    }
}