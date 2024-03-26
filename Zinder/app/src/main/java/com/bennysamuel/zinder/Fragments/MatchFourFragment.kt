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
import com.bennysamuel.zinder.databinding.FragmentMatchFourBinding

class MatchFourFragment : Fragment() {
    private lateinit var binding: FragmentMatchFourBinding
    private lateinit var viewModel: MatchesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchFourBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MatchesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.matches.observe(viewLifecycleOwner, Observer{ value ->
            binding.matchValue.text = value.toString()


        })

        binding.back4.setOnClickListener {
            findNavController().navigate(R.id.action_matchFourFragment_to_homeScreenFragment)
        }

        binding.no4.setOnClickListener {
            findNavController().navigate(R.id.action_matchFourFragment_to_matchFiveFragment)
        }

        binding.yes4.setOnClickListener {
            viewModel.incMatch()
            findNavController().navigate(R.id.action_matchFourFragment_to_matchFiveFragment)
        }
    }
}