package com.bennysamuel.headsortails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.headsortails.ViewModels.TossResultViewModel
import com.bennysamuel.headsortails.databinding.FragmentTossResultBinding


class TossResultFragment : Fragment() {

    private lateinit var binding: FragmentTossResultBinding
    private lateinit var viewModel:TossResultViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentTossResultBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(TossResultViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.tossValue == "head"){
            val resourceId = resources.getIdentifier("head", "drawable", "com.bennysamuel.headsortails")
            binding.imageView.setImageResource(resourceId)

        }
        else{
            val resourceId = resources.getIdentifier("tail", "drawable", "com.bennysamuel.headsortails")
            binding.imageView.setImageResource(resourceId)
        }

        binding.button2.setOnClickListener{
            findNavController().navigate(R.id.fron_result_fragment_to_toss_fragment)
        }
    }


}