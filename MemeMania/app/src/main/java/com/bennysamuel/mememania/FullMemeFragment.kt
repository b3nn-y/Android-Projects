package com.bennysamuel.mememania

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.bennysamuel.mememania.databinding.FragmentFullMemeBinding
import com.bennysamuel.mememania.databinding.FragmentMemeBinding
import com.bennysamuel.mememania.viewmodels.MemeViewModel

class FullMemeFragment : Fragment() {
    private lateinit var binding: FragmentFullMemeBinding
    private lateinit var viewModel: MemeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullMemeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MemeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView = view.findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            FullMemeScreen(meme = viewModel.currentMeme)
        }

    }


}