package com.bennysamuel.whatsmynextcountry.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.whatsmynextcountry.R
import com.bennysamuel.whatsmynextcountry.database.CountryDatabase
import com.bennysamuel.whatsmynextcountry.databinding.FragmentWishlistBinding
import com.bennysamuel.whatsmynextcountry.recyclerview.CountryAdapter
import com.bennysamuel.whatsmynextcountry.recyclerview.CountryRecycleViewData
import com.bennysamuel.whatsmynextcountry.viewmodel.ExploreViewModel
import com.bennysamuel.whatsmynextcountry.viewmodel.ExploreViewModelFactory

class WishlistFragment : Fragment() {
    private lateinit var binding: FragmentWishlistBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var countryList: ArrayList<CountryRecycleViewData>
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var viewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishlistBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = CountryDatabase.getInstance(application).countryDatabaseDAO
        val viewModelFactory = ExploreViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(ExploreViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isCurrentFragmentWishlist = true

        binding.back3.setOnClickListener {
            findNavController().navigate(R.id.action_wishlistFragment_to_exploreFragment)
        }
        if(viewModel.areThereAnySavedCountries()){
            println("There are some countries")
            viewModel.clearSavedHashMap()
            viewModel.getDataFromSavedCountries()
            genRecycleView()
        }
        else{
            println("No countries")
            binding.tempText2.text = "Not yet liked any countries"
        }


    }

    fun genRecycleView(){
        recyclerView = binding.recycleView2
        recyclerView.layoutManager = LinearLayoutManager(context)
        countryList = ArrayList()
        for (i in viewModel.savedImageHashMap){
            lateinit var temp: CountryRecycleViewData
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
            findNavController().navigate(R.id.action_wishlistFragment_to_countriesDetailsFragment)
        }

        countryAdapter.onItemSaveClick = {country, saveState ->
            viewModel.changeSaveState(country.countryName, saveState)
            viewModel.clearSavedHashMap()
            viewModel.getDataFromSavedCountries()
            genRecycleView()

        }
    }

}