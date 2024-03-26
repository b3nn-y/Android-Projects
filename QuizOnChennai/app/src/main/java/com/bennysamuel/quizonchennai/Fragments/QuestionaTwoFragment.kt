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
import com.bennysamuel.quizonchennai.databinding.FragmentQuestionaTwoBinding

class QuestionaTwoFragment : Fragment() {
    private lateinit var binding:FragmentQuestionaTwoBinding
    private lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentQuestionaTwoBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next2.setOnClickListener {
            findNavController().navigate(R.id.action_from_quest2_to_quest3)
        }
        binding.back2.setOnClickListener {
            findNavController().navigate(R.id.action_from_quest2_to_quest1)
        }

        if(viewModel.questionTwoChoice != 0){
            binding.radioGroup2.check(viewModel.questionTwoChoice)
        }

        binding.radioGroup2.setOnCheckedChangeListener{group, checkedID ->
            when(checkedID){
                R.id.radioButton4 ->{
                    viewModel.questionTwo = false
                    viewModel.questionTwoChoice = checkedID
                }

                R.id.radioButton5 ->{
                    viewModel.questionTwo = false
                    viewModel.questionTwoChoice = checkedID

                }

                R.id.radioButton6 ->{
                    viewModel.questionTwo = true
                    viewModel.questionTwoChoice = checkedID

                }
            }

        }
    }
}