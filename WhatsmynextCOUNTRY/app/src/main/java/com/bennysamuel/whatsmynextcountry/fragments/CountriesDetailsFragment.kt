package com.bennysamuel.whatsmynextcountry.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bennysamuel.whatsmynextcountry.R
import com.bennysamuel.whatsmynextcountry.database.CountryDatabase
import com.bennysamuel.whatsmynextcountry.databinding.FragmentCountriesDetailsBinding
import com.bennysamuel.whatsmynextcountry.viewmodel.ExploreViewModel
import com.bennysamuel.whatsmynextcountry.viewmodel.ExploreViewModelFactory
import com.bumptech.glide.Glide
import kotlin.random.Random


class CountriesDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCountriesDetailsBinding
    private lateinit var viewModel: ExploreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountriesDetailsBinding.inflate(inflater,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = CountryDatabase.getInstance(application).countryDatabaseDAO
        val viewModelFactory = ExploreViewModelFactory(dataSource,application)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(ExploreViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        println(viewModel.detailedFragementData)
        var countryData = viewModel.detailedFragementData
        binding.countryNameInDetail.text = countryData.countryName
        binding.countryNameInDetailHeading.text = countryData.countryName

        if(countryData.countryName.length>11){
            binding.countryNameInDetail.setTextSize(25F)
        }

        if(countryData.countryName.length>28){
            binding.countryNameInDetail.setTextSize(20F)
            binding.countryNameInDetailHeading.setTextSize(15F)
        }



        if(!viewModel.isCurrentFragmentWishlist){
            val countryIndex = viewModel.getCountryIndex(countryData.countryName)

            println(countryIndex)


            binding.countryCapital.text = "Capital: " + viewModel.getCountryCapital()[countryIndex]
            binding.countryCurrency.text = "Currency: "+ viewModel.getCountryCurrency()[countryIndex]
            binding.countryRegion.text = "Region: " + viewModel.getCountryRegion()[countryIndex]
            binding.countryLocation.text = "Location: " + viewModel.getCountryLocation()[countryIndex]

            Glide.with(this)
                .load(viewModel.getFlagLink()[countryIndex])
                .placeholder(R.drawable.load4)
                .error(R.drawable.error2)
                .into(binding.countryFlagImage)

            binding.back.setOnClickListener{
                findNavController().navigate(R.id.action_countriesDetailsFragment_to_exploreFragment)
            }

            binding.countryImageInDetail.setOnClickListener{
                var imageList = viewModel.imageHashMap[countryData.countryName]
                var randIndex = Random.nextInt(imageList!!.size)
                Glide.with(this)
                    .load(imageList[randIndex])
                    .thumbnail(0.8F)
                    .placeholder(R.drawable.load4)
                    .error(R.drawable.error2)
                    .into(binding.countryImageInDetail)
            }

        }

        else{

            binding.countryCapital.text = "Capital: " + viewModel.dataBase.getCounCapital(countryData.countryName)
            binding.countryCurrency.text = "Currency: "+ viewModel.dataBase.getCounCurr(countryData.countryName)
            binding.countryRegion.text = "Region: " + viewModel.dataBase.getCounRegion(countryData.countryName)
            binding.countryLocation.text = "Location: " + viewModel.dataBase.getCounLoc(countryData.countryName)

            Glide.with(this)
                .load(viewModel.dataBase.getCounFlag(countryData.countryName))
                .placeholder(R.drawable.load4)
                .error(R.drawable.error2)
                .into(binding.countryFlagImage)

            binding.back.setOnClickListener{
                findNavController().navigate(R.id.action_countriesDetailsFragment_to_wishlistFragment)
            }


        }




        if (countryData.saved == "false"){
            binding.addWishList.tag = R.drawable.orangeheart
            binding.addWishList.setImageResource(R.drawable.orangeheart)
        }
        else{
            binding.addWishList.tag = R.drawable.redheart
            binding.addWishList.setImageResource(R.drawable.redheart)
        }

        Glide.with(this)
            .load(countryData.countryImageLink)
            .placeholder(R.drawable.load4)
            .error(R.drawable.error2)
            .into(binding.countryImageInDetail)





        binding.addWishList.setOnClickListener {
            if (binding.addWishList.tag == R.drawable.orangeheart){
                binding.addWishList.tag = R.drawable.redheart
                binding.addWishList.setImageResource(R.drawable.redheart)
                viewModel.insertData(countryData.countryName, "true")
                viewModel.changeSaveState(countryData.countryName,"true")

            }
            else{
                binding.addWishList.tag = R.drawable.orangeheart
                binding.addWishList.setImageResource(R.drawable.orangeheart)
                viewModel.insertData(countryData.countryName, "false")
                viewModel.changeSaveState(countryData.countryName,"false")
            }
        }

    }


}