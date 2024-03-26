package com.bennysamuel.dayscaluclator

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.dayscaluclator.databinding.FragmentOutputBinding
import com.bennysamuel.dayscaluclator.output.OutputViewModel


class OutputFragment : Fragment() {


    private lateinit var binding: FragmentOutputBinding
    private lateinit var viewModel: OutputViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentOutputBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(OutputViewModel::class.java)

        viewModel.days.observe(viewLifecycleOwner , Observer {age ->
            binding.textView.text = viewModel.calculateDays()
        })

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_back_to_select_date_fragment)
        }

        return binding.root
    }







}