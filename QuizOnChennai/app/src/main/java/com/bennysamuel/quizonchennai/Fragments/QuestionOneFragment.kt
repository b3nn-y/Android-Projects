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
import com.bennysamuel.quizonchennai.databinding.FragmentQuestionOneBinding


class QuestionOneFragment : Fragment() {
   private lateinit var binding: FragmentQuestionOneBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentQuestionOneBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next1.setOnClickListener {
            findNavController().navigate(R.id.action_from_quest1_to_quest2)
        }
        binding.back1.setOnClickListener {
            findNavController().navigate(R.id.action_from_quest1_to_home_screen)
        }
        if(viewModel.questionOneChoice != 0){
            binding.radioGroup1.check(viewModel.questionOneChoice)
        }

        binding.radioGroup1.setOnCheckedChangeListener{group, checkedID ->
            when(checkedID){
                R.id.radioButton1 ->{
                    viewModel.questionOne = true
                    viewModel.questionOneChoice = checkedID
                }

                R.id.radioButton2 ->{
                    viewModel.questionOne = false
                    viewModel.questionOneChoice = checkedID

                }

                R.id.radioButton3 ->{
                    viewModel.questionOne = false
                    viewModel.questionOneChoice = checkedID

                }
            }

        }
    }
}