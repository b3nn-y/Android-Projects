package com.bennysamuel.quizonchennai.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.quizonchennai.R
import com.bennysamuel.quizonchennai.ViewModels.GameViewModel
import com.bennysamuel.quizonchennai.databinding.FragmentHomeScreenBinding


class HomeScreenFragment : Fragment() {
    private lateinit var binding:FragmentHomeScreenBinding
    private lateinit var viewModel: GameViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener{
            viewModel.clear()
            findNavController().navigate(R.id.action_from_home_screen_to_quest_1)
        }
    }


}