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
import com.bennysamuel.supermarketinventorymanagement.databinding.FragmentChipsBinding
import com.bennysamuel.supermarketinventorymanagement.viewmodels.StocksViewModel


class ChipsFragment : Fragment() {

    private lateinit var binding: FragmentChipsBinding
    private lateinit var viewModel: StocksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChipsBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(StocksViewModel::class.java)
        savedInstanceState?.let {
            val chValue = it.getString("chips")
            if (chValue != null) {
                viewModel.setChipsValue(chValue.toInt())
            }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_chipsFragment_to_itemsFragment)
        }
        viewModel.chipsStock.observe(viewLifecycleOwner, Observer { value ->
            binding.chipsAmount.text = value.toString()

        })
        binding.incStock.setOnClickListener {
            viewModel.incChips()
            println("Inc")
        }
        binding.decStock.setOnClickListener {
            viewModel.decChips()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("chips",viewModel.cheeseStock.toString())
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