package com.bennysamuel.headsortails

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.headsortails.ViewModels.TossResultViewModel
import com.bennysamuel.headsortails.databinding.FragmentTossBinding
import com.bumptech.glide.Glide



class TossFragment : Fragment() {


    private lateinit var binding:FragmentTossBinding
    private lateinit var viewModel: TossResultViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTossBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity()).get(TossResultViewModel::class.java)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button1.setOnClickListener {
            viewModel.toss()
            findNavController().navigate(com.bennysamuel.headsortails.R.id.from_toss_fragment_to_result_fragment)
        }
    }


}