package com.bennysamuel.formsroomdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.formsroomdb.R
import com.bennysamuel.formsroomdb.database.FormDatabase
import com.bennysamuel.formsroomdb.databinding.FragmentFormBinding
import com.bennysamuel.formsroomdb.viewmodels.FormViewModel
import com.bennysamuel.formsroomdb.viewmodels.FormViewModelFactory

class FormFragment : Fragment() {
    private lateinit var binding: FragmentFormBinding
    private lateinit var viewModel: FormViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormBinding.inflate(inflater,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = FormDatabase.getInstance(application).formDatabaseDAO
        val viewModelFactory = FormViewModelFactory(dataSource,application, lifecycleOwner = viewLifecycleOwner)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory)[FormViewModel::class.java]
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            viewModel.insertData(binding.editText5.text.toString(),binding.editText6.text.toString(),binding.editText7.text.toString(),binding.editText8.text.toString())
            viewModel.getDetails()
            findNavController().navigate(R.id.action_formFragment_to_resultFragment)
        }
    }

}