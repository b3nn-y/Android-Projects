package com.bennysamuel.whatsmynextcountry.fragments

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.navigation.fragment.findNavController
import com.bennysamuel.whatsmynextcountry.R
import com.bennysamuel.whatsmynextcountry.countryImageRetrofit.ImagesApiService
import com.bennysamuel.whatsmynextcountry.countryImageRetrofit.ImagesRetrofitInstance
import com.bennysamuel.whatsmynextcountry.countryImageRetrofit.PixabayResponse
import com.bennysamuel.whatsmynextcountry.databinding.FragmentExploreBinding
import com.bennysamuel.whatsmynextcountry.countryInfoRetrofit.Countries
import com.bennysamuel.whatsmynextcountry.countryInfoRetrofit.CountryApiService
import com.bennysamuel.whatsmynextcountry.countryInfoRetrofit.CountryRetroFitInstance
import com.bennysamuel.whatsmynextcountry.viewmodel.ExploreViewModel
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.whatsmynextcountry.database.CountryDatabase
import com.bennysamuel.whatsmynextcountry.recyclerview.CountryAdapter
import com.bennysamuel.whatsmynextcountry.recyclerview.CountryRecycleViewData
import com.bennysamuel.whatsmynextcountry.viewmodel.ExploreViewModelFactory

class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding
    private lateinit var viewModel: ExploreViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var countryList: ArrayList<CountryRecycleViewData>
    private lateinit var countryAdapter: CountryAdapter
    private var count = 0
    private var totReq = 0
    private var compReq = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = CountryDatabase.getInstance(application).countryDatabaseDAO
        val viewModelFactory = ExploreViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(ExploreViewModel::class.java)

        if(viewModel.isTheListFull()){
            binding.tempText.text = ""
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isCurrentFragmentWishlist = false

        binding.back.setOnClickListener{
            findNavController().navigate(R.id.action_exploreFragment_to_homeFragment)
        }
        binding.wishlist.setOnClickListener{
            findNavController().navigate(R.id.action_exploreFragment_to_wishlistFragment)
        }
        binding.search.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment_to_searchFragment)
        }


        if(isInternetAvailable()){
            try {
                if(!viewModel.isTheListFull()){
                    val retroFitInstance = CountryRetroFitInstance.getRetrofitInstance().create(
                        CountryApiService::class.java)


                    val responseLiveData: LiveData<Response<Countries>> = liveData {
                        val response = retroFitInstance.getCountriesData()
                        emit(response)
                    }
                    responseLiveData.observe(viewLifecycleOwner, Observer {
//            println(it.body()!!::class.java)
                        val countriesList = it.body()?.listIterator()
                        var countryCount = 0

                        if (countriesList!= null){
                            while (countriesList.hasNext()){
                                var tempCountry = countriesList.next()

                                viewModel.addCountryName(tempCountry.name.common)

                                viewModel.addCountryFlag(tempCountry.flags.png)
                                viewModel.addCountryLocation(tempCountry.maps.googleMaps)
                                viewModel.addCountryRegion(tempCountry.region)
                                try{
                                    viewModel.addCountryCapital(tempCountry.capital[0])
                                }
                                catch(e:Exception){
                                    viewModel.addCountryCapital("No Capital City")
                                }

                                try{
                                    viewModel.addCountryCurrency(((tempCountry.currencies.values).toList()[0]).toString().subSequence(18,(((tempCountry.currencies.values).toList()[0]).toString().length)-1).toString())
                                }
                                catch(e:Exception){
                                    viewModel.addCountryCurrency("Unknown Currency")
                                }
                                countryCount++
                            }
                            genImagesWithTime(viewModel.getCountryNames())

                            println(countryCount)
                            viewModel.printData()
                            binding.tempText.text = ""



                            println(viewModel.imageHashMap.size)
                        }
                    })
//            viewModel.printData()


                }
                else{
                    genRecycleView()
                    binding.tempText.text = ""
                }
            }
            catch (e:Exception){
                println(e.stackTrace)
                binding.tempText.text = "Error on loading"
            }

        }
        else{
            binding.tempText.text = "Error"
        }


    }
    fun getImageLinks(cityName: String, apiNum: Int){
        val imagesRetroFitInstance = ImagesRetrofitInstance.getRetrofitInstance().create(ImagesApiService::class.java)
        var queryParameter = cityName.split(" ").joinToString("+")
        val imagesResponseLiveData: LiveData<Response<PixabayResponse>> = liveData {
            if (apiNum ==1){
                val response = imagesRetroFitInstance.getImages2(queryParameter)
                emit(response)
            }
            if (apiNum==2){
                val response = imagesRetroFitInstance.getImages3(queryParameter)
                emit(response)
            }
            else{
                val response = imagesRetroFitInstance.getImages4(queryParameter)
                emit(response)
            }
        }

        imagesResponseLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val imagesList = response.body()?.hits
                if (!imagesList.isNullOrEmpty()) {
                    var tempSt: MutableList<String> = mutableListOf()
                    for (i in imagesList){
                        tempSt.add(i.largeImageURL)
                    }
//                    tempSt.add(imagesList[0].userImageURL)
                    viewModel.imageHashMap[cityName] = tempSt
                    println("success")
                    count++
                    println(count)
                } else {
                     println("No images found")
                    viewModel.imageHashMap[cityName] = mutableListOf("No Images Found")
                }
            } else {
                println("Error: ${response.message()}")
            }
            compReq++
            if (compReq == totReq) {
                genRecycleView()
                unlockOrientation()
            }

        })
    }

    fun genImagesWithTime(list: MutableList<String>){
        println(list.size)
        lockOrientationToPortrait()
        for (i in 0..100){
            totReq++
            getImageLinks(list[i],1)
        }
        for (i in 101..200){
            totReq++
            getImageLinks(list[i],2)
        }
        for (i in 201..249){
            totReq++
            getImageLinks(list[i],3)
        }
    }

    fun genRecycleView(){
        recyclerView = binding.recycleView
        recyclerView.layoutManager = LinearLayoutManager(context)
        countryList = ArrayList()
        for (i in viewModel.imageHashMap){
            lateinit var temp:CountryRecycleViewData
            if (i.value[0]!="No Images Found"){
                temp = CountryRecycleViewData(i.key,i.value[0],viewModel.isTheCountrySaved(i.key))
                viewModel.updateImages(i.key,i.value)
//                countryList.add(temp)
            }
            else{
                temp = CountryRecycleViewData(i.key,"https://upload.wikimedia.org/wikipedia/commons/d/d1/Image_not_available.png",viewModel.isTheCountrySaved(i.key))
                viewModel.updateImages(i.key,i.value)
//                countryList.add(temp)
            }

            countryList.add(temp)
        }
        countryAdapter = CountryAdapter(countryList)
        println(countryList)
        recyclerView.adapter = countryAdapter

        countryAdapter.onItemClick = {
            viewModel.detailedFragementData = it
            findNavController().navigate(R.id.action_exploreFragment_to_countriesDetailsFragment)
        }

        countryAdapter.onItemSaveClick = {country, saveState ->
            viewModel.insertData(country.countryName,saveState)
            viewModel.changeSaveState(country.countryName, saveState)

        }
    }

    @SuppressLint("ServiceCast")
    private fun isInternetAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun lockOrientationToPortrait() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun unlockOrientation() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }

}