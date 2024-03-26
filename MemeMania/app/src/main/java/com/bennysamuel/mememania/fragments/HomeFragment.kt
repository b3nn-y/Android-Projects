package com.bennysamuel.mememania.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bennysamuel.mememania.R
import com.bennysamuel.mememania.databinding.FragmentHomeBinding
import com.bennysamuel.mememania.viewmodels.MemeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MemeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MemeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.memes = arrayListOf()
//        lifecycleScope.launch {
//            viewModel.getMemes("")
//        }
        binding.devmemes.setOnClickListener {
            viewModel.memeType = "dev"

            findNavController().navigate(R.id.action_homeFragment_to_memeFragment)
        }
        binding.tamilmemes.setOnClickListener {
            viewModel.memeType = "tamil"
            findNavController().navigate(R.id.action_homeFragment_to_memeFragment)
        }
        binding.memes.setOnClickListener {
            viewModel.memeType = "meme"
            findNavController().navigate(R.id.action_homeFragment_to_memeFragment)
        }
        binding.wholesome.setOnClickListener {
            viewModel.memeType = "wholesome"
            findNavController().navigate(R.id.action_homeFragment_to_memeFragment)
        }
        binding.adult.setOnClickListener {
            viewModel.memeType = "adult"
            findNavController().navigate(R.id.action_homeFragment_to_memeFragment)
        }
        binding.dank.setOnClickListener {
            viewModel.memeType = "dank"
            findNavController().navigate(R.id.action_homeFragment_to_memeFragment)
        }
    }
}
