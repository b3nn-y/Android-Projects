package com.bennysamuel.formsroomdb.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bennysamuel.formsroomdb.database.FormDatabaseDAO
import java.lang.IllegalArgumentException

class FormViewModelFactory(
    private val dataSource: FormDatabaseDAO,
    private val application: Application,
    private val lifecycleOwner: LifecycleOwner) : ViewModelProvider.Factory{
    @Suppress
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FormViewModel::class.java)){
            return FormViewModel(dataSource, application, lifecycleOwner) as T
        }

        throw IllegalArgumentException("Unknown viewmodel")
    }


}