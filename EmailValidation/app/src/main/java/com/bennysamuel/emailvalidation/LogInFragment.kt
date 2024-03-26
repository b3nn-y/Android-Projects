package com.bennysamuel.emailvalidation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bennysamuel.emailvalidation.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {
    private lateinit var binding:FragmentLogInBinding
    private lateinit var viewModel: LogInViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(LogInViewModel::class.java)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.logInButton.setOnClickListener {
            if (binding.username.text.length>5){
                viewModel.username = binding.username.text.toString()
            }
        }



    }

}