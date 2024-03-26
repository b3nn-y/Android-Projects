package com.bennysamuel.supermarketinventorymanagement.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bennysamuel.supermarketinventorymanagement.R
import com.bennysamuel.supermarketinventorymanagement.databinding.FragmentItemsBinding

class ItemsFragment : Fragment() {
    private lateinit var binding: FragmentItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chips.setOnClickListener{
            findNavController().navigate(R.id.action_itemsFragment_to_chipsFragment)
        }

        binding.cheese.setOnClickListener{
            findNavController().navigate(R.id.action_itemsFragment_to_cheeseFragment)
        }

        binding.soda.setOnClickListener{
            findNavController().navigate(R.id.action_itemsFragment_to_sodaFragment)
        }

        binding.milk.setOnClickListener{
            findNavController().navigate(R.id.action_itemsFragment_to_milkFragment)
        }

        binding.noodles.setOnClickListener{
            findNavController().navigate(R.id.action_itemsFragment_to_ramenFragment)
        }
        binding.choco.setOnClickListener{
            findNavController().navigate(R.id.action_itemsFragment_to_chocolateFragment)

        }
        binding.back.setOnClickListener{
            findNavController().navigate(R.id.action_itemsFragment_to_homeScreenFragment)
        }

    }


}