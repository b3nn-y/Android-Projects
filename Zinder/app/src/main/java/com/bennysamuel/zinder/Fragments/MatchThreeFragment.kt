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
import com.bennysamuel.zinder.databinding.FragmentMatchThreeBinding

class MatchThreeFragment : Fragment() {
    private lateinit var binding: FragmentMatchThreeBinding
    private lateinit var viewModel: MatchesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchThreeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MatchesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.matches.observe(viewLifecycleOwner, Observer{ value ->
            binding.matchValue.text = value.toString()


        })

        binding.back3.setOnClickListener {
            findNavController().navigate(R.id.action_matchThreeFragment_to_homeScreenFragment)
        }

        binding.no3.setOnClickListener {
            findNavController().navigate(R.id.action_matchThreeFragment_to_matchFourFragment)
        }

        binding.yes3.setOnClickListener {
            viewModel.incMatch()
            findNavController().navigate(R.id.action_matchThreeFragment_to_matchFourFragment)
        }
    }
}