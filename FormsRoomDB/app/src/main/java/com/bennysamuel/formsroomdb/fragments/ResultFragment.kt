package com.bennysamuel.formsroomdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.formsroomdb.R
import com.bennysamuel.formsroomdb.database.FormDatabase
import com.bennysamuel.formsroomdb.databinding.FragmentResultBinding
import com.bennysamuel.formsroomdb.viewmodels.FormViewModel
import com.bennysamuel.formsroomdb.viewmodels.FormViewModelFactory

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: FormViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = FormDatabase.getInstance(application).formDatabaseDAO
        val viewModelFactory = FormViewModelFactory(dataSource,application,viewLifecycleOwner)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(FormViewModel::class.java)
//        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("Hi onview")
        binding.button2.setOnClickListener {
            viewModel.clearData()
            findNavController().navigate(R.id.action_resultFragment_to_formFragment)
        }
        binding.resultText.text = viewModel.finalOut.value


    }
}