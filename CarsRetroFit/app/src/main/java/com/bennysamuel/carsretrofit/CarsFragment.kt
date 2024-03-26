package com.bennysamuel.carsretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.bennysamuel.carsretrofit.databinding.FragmentCarsBinding
import retrofit2.Response

class CarsFragment : Fragment() {
    private lateinit var binding: FragmentCarsBinding
    private lateinit var viewModel: CarViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarsBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(CarViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getCarImages()

        val retrofitService = RetrofitInstance. getRestrofitInstance().create(CarsApiService::class.java)
        val responseLiveData: LiveData<Response<CarPicCollection>> = liveData {
            val response = retrofitService.getProperties()
            emit(response)
        }
        responseLiveData.observe(viewLifecycleOwner, Observer {

            var outputString = ""
            val photoList = it.body()?.listIterator()
            if(photoList != null){
                while (photoList.hasNext()){
                    val photo = photoList.next()
                    println(photo.url)
                    outputString += photo.title + "\n"

                }
            }
            binding.text.text = outputString

        })


    }


}