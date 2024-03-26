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
import com.bennysamuel.quizonchennai.databinding.FragmentQuestionThreeBinding

class QuestionThreeFragment : Fragment() {

    private lateinit var binding: FragmentQuestionThreeBinding
    private lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentQuestionThreeBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next3.setOnClickListener {
            findNavController().navigate(R.id.action_questionThreeFragment_to_scoreFragment)
        }
        binding.back3.setOnClickListener {
            findNavController().navigate(R.id.action_from_quest3_to_quest2)
        }

        if(viewModel.questionThreeChoice != 0){
            binding.radioGroup3.check(viewModel.questionThreeChoice)
        }

        binding.radioGroup3.setOnCheckedChangeListener{group, checkedID ->
            when(checkedID){
                R.id.radioButton7 ->{
                    viewModel.questionThree = false
                    viewModel.questionThreeChoice = checkedID
                }

                R.id.radioButton8 ->{
                    viewModel.questionThree = false
                    viewModel.questionThreeChoice = checkedID

                }

                R.id.radioButton9 ->{
                    viewModel.questionThree = true
                    viewModel.questionThreeChoice = checkedID

                }
            }

        }
    }
}