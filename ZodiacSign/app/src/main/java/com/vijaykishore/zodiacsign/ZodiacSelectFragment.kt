package com.vijaykishore.zodiacsign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vijaykishore.zodiacsign.databinding.FragmentZodiacSelectBinding

class ZodiacSelectFragment : Fragment() {
    private lateinit var binding: FragmentZodiacSelectBinding
    private lateinit var viewModel: ZodiacViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentZodiacSelectBinding.inflate(inflater,container,false)
       return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding..setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_dollarFragment)
        }
        binding.ruppeButton.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_rupeesFragment)
        }
    }

}