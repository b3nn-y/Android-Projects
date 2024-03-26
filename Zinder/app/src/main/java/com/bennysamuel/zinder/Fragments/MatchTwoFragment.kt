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
import com.bennysamuel.zinder.databinding.FragmentMatchTwoBinding

class MatchTwoFragment : Fragment() {
    private lateinit var binding: FragmentMatchTwoBinding
    private lateinit var viewModel: MatchesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchTwoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MatchesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.matches.observe(viewLifecycleOwner, Observer{ value ->
            binding.matchValue.text = value.toString()


        })

        binding.back2.setOnClickListener {
            findNavController().navigate(R.id.action_matchTwoFragment_to_homeScreenFragment)
        }

        binding.no2.setOnClickListener {
            findNavController().navigate(R.id.action_matchTwoFragment_to_matchThreeFragment)
        }

        binding.yes2.setOnClickListener {
            viewModel.incMatch()
            findNavController().navigate(R.id.action_matchTwoFragment_to_matchThreeFragment)
        }
    }
}