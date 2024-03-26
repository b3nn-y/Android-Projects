package com.bennysamuel.dayscaluclator

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.dayscaluclator.databinding.FragmentSelectDateBinding
import com.bennysamuel.dayscaluclator.output.OutputViewModel


class SelectDateFragment : Fragment() {

    private lateinit var binding: FragmentSelectDateBinding
    private lateinit var viewModel: OutputViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSelectDateBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(OutputViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener {
            val year = binding.datePicker.year
            println(year)
            viewModel.setDays(binding.datePicker)
            findNavController().navigate(R.id.action_to_output_fragment)
        }
    }


}