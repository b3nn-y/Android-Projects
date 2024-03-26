package com.bennysamuel.whatsmynextcountry.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bennysamuel.whatsmynextcountry.database.CountryDatabaseDAO
import java.lang.IllegalArgumentException

class ExploreViewModelFactory(
    private val dataSource: CountryDatabaseDAO,
    private val application: Application): ViewModelProvider.Factory{
    @Suppress
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ExploreViewModel::class.java)){
            return ExploreViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

    }

