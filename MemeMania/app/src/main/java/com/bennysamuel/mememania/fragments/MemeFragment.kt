package com.bennysamuel.mememania.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.mememania.R
import com.bennysamuel.mememania.databinding.FragmentMemeBinding
import com.bennysamuel.mememania.recycleradapter.MemeRecyclerAdapter
import com.bennysamuel.mememania.viewmodels.MemeViewModel
import kotlinx.coroutines.launch


class MemeFragment : Fragment() {
    private lateinit var binding: FragmentMemeBinding
    private lateinit var viewModel: MemeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var memeRecyclerAdapter: MemeRecyclerAdapter
    private var isButtonVisible = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MemeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        lifecycleScope.launch {
            if (viewModel.memes.size<10){
                getMemes()
            }
            else{
                genRecyclerView()
            }
        }

        binding.heart.setOnClickListener {
            findNavController().navigate(R.id.action_memeFragment_to_fullMemeFragment)
        }

        binding.loadMore.setOnClickListener{
            lifecycleScope.launch {
                binding.loadMore?.visibility = View.GONE
                viewModel.getMemes(viewModel.memeType)
                memeRecyclerAdapter.updateList(viewModel.memes)
            }
        }

    }

    fun genRecyclerView(){
        memeRecyclerAdapter = MemeRecyclerAdapter(viewModel.memes)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = memeRecyclerAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1 && !isButtonVisible) {
                    if (totalItemCount > 10){
                        binding.loadMore?.visibility = View.VISIBLE
                        isButtonVisible = true
                    }
                } else if (lastVisibleItemPosition < totalItemCount - 1 && isButtonVisible) {
                    binding.loadMore?.visibility = View.GONE
                    isButtonVisible = false
                }
            }
        })
        memeRecyclerAdapter.onItemClick = {
            viewModel.currentMeme = it
            findNavController().navigate(R.id.action_memeFragment_to_fullMemeFragment)
        }
    }


    suspend fun getMemes(){
        viewModel.getMemes(viewModel.memeType)
        genRecyclerView()
    }


}