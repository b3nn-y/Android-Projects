package com.bennysamuel.weatherify.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.weatherify.R
import com.bennysamuel.weatherify.database.WeatherDatabase
import com.bennysamuel.weatherify.databinding.FragmentHomeBinding
import com.bennysamuel.weatherify.homeRecyclerView.HomeRecyclerViewAdapter
import com.bennysamuel.weatherify.homeRecyclerView.HomeRecyclerViewData
import com.bennysamuel.weatherify.retrofitForSearching.WeatherData
import com.bennysamuel.weatherify.suggestionsRecyclerView.SuggestionsAdapter
import com.bennysamuel.weatherify.suggestionsRecyclerView.SuggestionsRecyclerData
import com.bennysamuel.weatherify.suggestionsRetrofit.Feature
import com.bennysamuel.weatherify.viewmodels.WeatherViewModel
import com.bennysamuel.weatherify.viewmodels.WeatherViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: WeatherViewModel
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeRecyclerList: ArrayList<HomeRecyclerViewData>
    private lateinit var homeRecyclerViewAdapter: HomeRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = WeatherDatabase.getInstance(application).weatherDetailsDAO
        val viewModelFactory = WeatherViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(WeatherViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)
//        lifecycleScope.launch {
//            viewModel.getWeatherSearchData("Mogappair")
//        }
//
        binding.locationImage.setOnClickListener{
            getLocation()
        }
        fetchLocation()

        binding.searchBar.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        println("hello")

    }

    fun getLocation() {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION

        if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
            if (true) {
                // Show a rationale dialog explaining why the permission is needed
                AlertDialog.Builder(requireContext())
                    .setTitle("Location Permission Needed")
                    .setMessage("We need your location to show the weather in your location.")
                    .setPositiveButton("OK") { _, _ ->
                        requestLocationPermission()
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        // Handle denial or show a message
                        showToast("Location permission denied.")
                    }
                    .create()
                    .show()
            } else {
                requestLocationPermission()
            }
        } else {
            showToast("Location permission already granted.")
            // Permission already granted, get location
            fetchLocation()
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun fetchLocation() {
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    // Got last known location. In some situations, this can be null.
                    location?.let {
                        val latitude = it.latitude
                        val longitude = it.longitude
                        // Use latitude and longitude as needed
//                        showToast("Latitude: $latitude, Longitude: $longitude")
                        lifecycleScope.launch {
                            viewModel.findLocation(latitude, longitude)
                        }
                    }
                }
                .addOnFailureListener { e ->
                    // Handle failure
                    showToast("Error fetching location: ${e.message}")
                }

        }
        else{
            showToast("Location permission not granted")
        }

        lifecycleScope.launch{
            genRecyclerView(viewModel.searchDatabase())
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                // Check if the request was granted
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, get location
                    fetchLocation()
                } else {
                    // Permission denied, handle accordingly
                    showToast("Location permission denied.")
                }
            }
            // Add more cases if you have multiple permission requests
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }

    fun genRecyclerView(homeList:ArrayList<HomeRecyclerViewData>){
        homeRecyclerList = homeList
        homeRecyclerViewAdapter = HomeRecyclerViewAdapter(homeRecyclerList)
        recyclerView.adapter = homeRecyclerViewAdapter

        homeRecyclerViewAdapter.onItemClick = {
            println(it)
            var searchTerm2 = if (it.searchTerm.lowercase().contains("(current)")){
                it.searchTerm.subSequence(0,it.searchTerm.length-9 ).toString()
            } else {
                it.searchTerm
            }
            var temp = SuggestionsRecyclerData("" ,searchTerm2 )
            viewModel.detailedFragemnt = temp
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)

        }

    }

}