package com.bennysamuel.weatherify.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.weatherify.R
import com.bennysamuel.weatherify.database.WeatherDatabase
import com.bennysamuel.weatherify.databinding.FragmentHomeBinding
import com.bennysamuel.weatherify.databinding.FragmentSearchBinding
import com.bennysamuel.weatherify.suggestionsRecyclerView.SuggestionsAdapter
import com.bennysamuel.weatherify.suggestionsRecyclerView.SuggestionsRecyclerData
import com.bennysamuel.weatherify.suggestionsRetrofit.Feature
import com.bennysamuel.weatherify.viewmodels.WeatherViewModel
import com.bennysamuel.weatherify.viewmodels.WeatherViewModelFactory
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: WeatherViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var suggestionsList: ArrayList<SuggestionsRecyclerData>
    private lateinit var suggestionsAdapter: SuggestionsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = WeatherDatabase.getInstance(application).weatherDetailsDAO
        val viewModelFactory = WeatherViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(WeatherViewModel::class.java)
        return binding.root
    } 
    //https://api.open-meteo.com/v1/forecast?latitude=13.0827&longitude=80.2707&current=temperature_2m,relative_humidity_2m,is_day,precipitation,rain,showers,snowfall,cloud_cover&daily=sunrise,sunset,uv_index_max&timezone=auto
    //https://api.tomorrow.io/v4/weather/realtime?location=mogappair&units=metric&apikey=QjV3LCYhNrfjIw3flf7g8kgUu9qWmXI2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerview3
        recyclerView.layoutManager = LinearLayoutManager(context)
        binding.searchBar.requestFocus()
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                println("submitted")
                binding.searchBar.clearFocus()
                lifecycleScope.launch {
                    genRecyclerView(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println("Text is changing")
                lifecycleScope.launch {
                    genRecyclerView(newText)
                }
                return false
            }



        })
    }
    suspend fun genRecyclerView(query: String?){
        println(query)
        if (query != null && query != "" && query.length>=3){
            try {
                suggestionsList = viewModel.getSuggestions(query)
                println(suggestionsList)
                if (::suggestionsAdapter.isInitialized) {
                    suggestionsAdapter.updateData(suggestionsList)
                } else {
                    suggestionsList = suggestionsList
                    suggestionsAdapter = SuggestionsAdapter(suggestionsList)
                    recyclerView.adapter = suggestionsAdapter
                }

            }
            catch (e:Exception){
                println(e.message)
            }
        }
        else{
            genRecyclerView("zzzzzzzzzzz")
        }

        suggestionsAdapter.onItemClick = {
            println(it)
            viewModel.detailedFragemnt = it
            findNavController().navigate(R.id.action_searchFragment_to_detailsFragment)

        }


    }
}