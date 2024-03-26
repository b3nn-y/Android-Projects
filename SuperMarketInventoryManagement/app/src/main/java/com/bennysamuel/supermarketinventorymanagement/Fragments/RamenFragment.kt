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
import com.bennysamuel.supermarketinventorymanagement.databinding.FragmentRamenBinding
import com.bennysamuel.supermarketinventorymanagement.viewmodels.StocksViewModel


class RamenFragment : Fragment() {
    private lateinit var binding: FragmentRamenBinding
    private lateinit var viewModel: StocksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRamenBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(StocksViewModel::class.java)
        savedInstanceState?.let {
            val chValue = it.getString("ramen")
            if (chValue != null) {
                viewModel.setRamenValue(chValue.toInt())
            }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener{
            findNavController().navigate(R.id.action_ramenFragment_to_itemsFragment)
        }
        viewModel.ramenStock.observe(viewLifecycleOwner, Observer { value ->
            binding.noodlesAmount.text = value.toString()

        })
        binding.incStock.setOnClickListener {
            viewModel.incRamen()
//            println("Inc")
        }
        binding.decStock.setOnClickListener {
            viewModel.decRamen()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("ramen",viewModel.milkStock.toString())
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