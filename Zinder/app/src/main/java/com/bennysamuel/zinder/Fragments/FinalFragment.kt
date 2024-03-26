package com.bennysamuel.zinder.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.zinder.R
import com.bennysamuel.zinder.ViewModel.MatchesViewModel
import com.bennysamuel.zinder.databinding.FragmentFinalBinding

class FinalFragment : Fragment() {
    private lateinit var binding: FragmentFinalBinding
    private lateinit var viewModel: MatchesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFinalBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MatchesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeButton.setOnClickListener {
            findNavController().navigate(R.id.action_finalFragment_to_homeScreenFragment)
        }
        viewModel.matches.observe(viewLifecycleOwner,Observer{value ->
            binding.textView2.text = value.toString()
        })

    }
}