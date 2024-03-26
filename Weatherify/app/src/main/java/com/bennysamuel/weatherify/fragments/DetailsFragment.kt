package com.bennysamuel.weatherify.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bennysamuel.weatherify.R
import com.bennysamuel.weatherify.database.WeatherDatabase
import com.bennysamuel.weatherify.databinding.FragmentDetailsBinding
import com.bennysamuel.weatherify.databinding.FragmentHomeBinding
import com.bennysamuel.weatherify.viewmodels.WeatherViewModel
import com.bennysamuel.weatherify.viewmodels.WeatherViewModelFactory
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: WeatherViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = WeatherDatabase.getInstance(application).weatherDetailsDAO
        val viewModelFactory = WeatherViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(WeatherViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            getData()
        }
        binding.back.setOnClickListener{
            findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }
        binding.saveButton.setOnClickListener{
            saveButtonDatabase()
        }
        saveButton()



    }

    @SuppressLint("SetTextI18n")
    suspend fun getData(){
        val searchTerm = viewModel.detailedFragemnt.searchTerm
        binding.placeName.text = searchTerm
        if(viewModel.currentSearchedWeather != searchTerm){
            try {
                var searchData = viewModel.getWeatherSearchData(searchTerm)
                if (searchData != null) {
                    binding.locationName.text = searchData.location.name
                }
                var weather = viewModel.getWeather(searchData?.location!!.lat, searchData?.location!!.lon)
                viewModel.currentSearchedWeather = viewModel.detailedFragemnt.searchTerm
                viewModel.currLocation = searchData.location.name
                if (weather != null) {
                    viewModel.weather = weather
                }
                binding.humidity.text = "Humidity: " + weather?.current?.relative_humidity_2m.toString()
                println(weather?.current?.time)
                binding.temperature.text = weather?.current!!.temperature_2m.toString() + " deg"
                binding.cloudCover.text = "Cloud Cover: " + weather?.current!!.cloud_cover
                binding.forecast.text = "HEllo Forcast"
                binding.uvIndex.text = "UV index: "+ weather?.daily!!.uv_index_max
                var day = weather.current.is_day
                if(day != 0){
                    binding.day.text = "DAYTIME"
                    viewModel.dayStaus = "DAYTIME"
                    binding.weatherImage.setImageResource(R.drawable.sun)
                }
                else{
                    binding.day.text = "NIGHTTIME"
                    viewModel.dayStaus = "NIGHTIME"
                    binding.weatherImage.setImageResource(R.drawable.moon
                    )
                }

                var temperature = weather.current.temperature_2m
                binding.forecast.text =  when {
                    temperature >= 30 -> "It's hot today! Stay hydrated."
                    temperature in 20.0..29.9 -> "The weather is warm and pleasant."
                    temperature in 10.0..19.9 -> "It's a cool day. Grab a light jacket."
                    temperature < 10 -> "Brrr! It's cold. Bundle up!"
                    else -> "Weather information not available."
                }
            }
            catch (e:Exception){
                println(e.message)
            }
        }
        else{
            println(viewModel.dayStaus)
            binding.locationName.text = viewModel.currLocation
            binding.temperature.text = viewModel.weather.current!!.temperature_2m.toString() + " deg"
            binding.humidity.text = "Humidity: " + viewModel.weather.current?.relative_humidity_2m.toString()
            binding.cloudCover.text = "Cloud Cover: " + viewModel.weather?.current!!.cloud_cover
            binding.uvIndex.text = "UV index: "+ viewModel.weather?.daily_units!!.uv_index_max
            println(viewModel.dayStaus)
            if(viewModel.dayStaus == "DAYTIME"){
                binding.day.text = "DAYTIME"
                binding.weatherImage.setImageResource(R.drawable.sun)
            }
            else{
                binding.day.text = "NIGHTTIME"
                binding.weatherImage.setImageResource(R.drawable.sun)
            }

            var temperature = viewModel.weather.current.temperature_2m
            binding.forecast.text =  when {
                temperature >= 30 -> "It's hot today! Stay hydrated."
                temperature in 20.0..29.9 -> "The weather is warm and pleasant."
                temperature in 10.0..19.9 -> "It's a cool day. Grab a light jacket."
                temperature < 10 -> "Brrr! It's cold. Bundle up!"
                else -> "Weather information not available."
            }

        }

    }
    fun saveButton(){
        if (viewModel.isPlacePresent(viewModel.detailedFragemnt.searchTerm)){
            binding.saveButton.setBackgroundResource(R.color.green)
            binding.saveButton.text = "SAVED"
        }
        else{
            binding.saveButton.setBackgroundResource(R.color.white)
            binding.saveButton.text = "SAVE THIS"
        }
    }

    fun saveButtonDatabase(){
        if (viewModel.isPlacePresent(viewModel.detailedFragemnt.searchTerm)){
            viewModel.delParticularPlace(viewModel.detailedFragemnt.searchTerm)
        }
        else{
            viewModel.insert(viewModel.detailedFragemnt.searchTerm, viewModel.weather.latitude, viewModel.weather.longitude)
        }
        saveButton()
    }

}