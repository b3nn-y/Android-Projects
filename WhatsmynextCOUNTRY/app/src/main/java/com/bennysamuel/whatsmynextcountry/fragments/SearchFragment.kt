package com.bennysamuel.whatsmynextcountry.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.query
import com.bennysamuel.whatsmynextcountry.R
import com.bennysamuel.whatsmynextcountry.database.CountryDatabase
import com.bennysamuel.whatsmynextcountry.databinding.FragmentSearchBinding
import com.bennysamuel.whatsmynextcountry.databinding.FragmentWishlistBinding
import com.bennysamuel.whatsmynextcountry.recyclerview.CountryAdapter
import com.bennysamuel.whatsmynextcountry.recyclerview.CountryRecycleViewData
import com.bennysamuel.whatsmynextcountry.viewmodel.ExploreViewModel
import com.bennysamuel.whatsmynextcountry.viewmodel.ExploreViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var countryList: ArrayList<CountryRecycleViewData>
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var viewModel: ExploreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = CountryDatabase.getInstance(application).countryDatabaseDAO
        val viewModelFactory = ExploreViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(ExploreViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("Inside the Search Fragment")

        binding.back4.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_exploreFragment)
        }
        binding.searchBar.setOnClickListener {
            binding.searchBar.requestFocus()
        }
        if (isInternetAvailable()){
            binding.searchBar.requestFocus()
            binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    println("submitted")
                    binding.searchBar.clearFocus()
                    genRecycleView(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    println("Text is changing")
                    genRecycleView(newText)
                    return false
                }


            })
        }
        else{
            binding.tempText3.text = "Internet not available"
        }
        binding.searchBar.requestFocus()

    }

    @SuppressLint("ServiceCast")
    private fun isInternetAvailable(): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun genRecycleView(searchedText: String?){
        println(searchedText)
       if (searchedText != null && searchedText != ""){
          try {
              recyclerView = binding.recycleView3
              recyclerView.layoutManager = LinearLayoutManager(context)
              countryList = ArrayList()
              for (i in viewModel.imageHashMap){
                  var temp: CountryRecycleViewData? = null
                  println(i.key.lowercase())
                  println(i.key.lowercase().contains(searchedText.lowercase()))
                  if (i.value[0]!="No Images Found" && i.key.lowercase().contains(searchedText.lowercase())){
                      temp = CountryRecycleViewData(i.key,i.value[0],viewModel.isTheCountrySaved(i.key))
                      viewModel.updateImages(i.key,i.value)
//                countryList.add(temp)
                  }
                  else{
                      if (i.key.lowercase().contains(searchedText.lowercase())){
                          temp = CountryRecycleViewData(i.key,"https://upload.wikimedia.org/wikipedia/commons/d/d1/Image_not_available.png",viewModel.isTheCountrySaved(i.key))
                          viewModel.updateImages(i.key,i.value)
                      }

//                countryList.add(temp)
                  }

                  if(temp!= null){
                      countryList.add(temp)
                  }
              }
              if (countryList.size>0){
                  binding.tempText3.text = ""
                  var tempCounList = countryList
                  countryList  = filterAndSortCountries(searchedText,countryList)
                  for(i in tempCounList){
                      if (!countryList.contains(i)){
                          countryList.add(i)
                      }
                  }
              }
              else{
                  if(searchedText != "ZZZZZZZ"){
                      binding.tempText3.text = "No countries Found"
                  }
                  else{
                      binding.tempText3.text = ""
                  }
              }


              countryAdapter = CountryAdapter(countryList)
              println(countryList)
              recyclerView.adapter = countryAdapter

              countryAdapter.onItemClick = {
                  viewModel.detailedFragementData = it
                  findNavController().navigate(R.id.action_searchFragment_to_countriesDetailsFragment)
              }

              countryAdapter.onItemSaveClick = {country, saveState ->
                  viewModel.insertData(country.countryName,saveState)
                  viewModel.changeSaveState(country.countryName, saveState)

              }

          }
          catch (e:Exception){
              println(e.printStackTrace())
          }
       }
        else{
            genRecycleView("ZZZZZZZ")
           binding.tempText3.text = ""
       }
    }


    fun filterAndSortCountries(startingLetters: String, countryList: ArrayList<CountryRecycleViewData>): ArrayList<CountryRecycleViewData> {
        return ArrayList(countryList.filter {
            it.countryName.startsWith(startingLetters, ignoreCase = true)
        }.sortedBy {
            it.countryName
        })
    }


}