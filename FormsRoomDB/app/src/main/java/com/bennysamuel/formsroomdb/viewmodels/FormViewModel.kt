package com.bennysamuel.formsroomdb.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.bennysamuel.formsroomdb.database.Form
import com.bennysamuel.formsroomdb.database.FormDatabase
import com.bennysamuel.formsroomdb.database.FormDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class FormViewModel (
    val database: FormDatabaseDAO,
    application: Application,
    private val lifecycleOwner: LifecycleOwner ) : AndroidViewModel(application)
{
    val _email = MutableLiveData<String>()
    val _gender = MutableLiveData<String>()
    val _age = MutableLiveData<String>()
    val _name = MutableLiveData<String>()
    var email: LiveData<String> = _email
    var gender: LiveData<String> = _gender
    var age: LiveData<String> = _age
    var name:LiveData<String> = _name


    var _finalOut = MutableLiveData<String>()
    var finalOut : LiveData<String> = _finalOut


    fun getDetails(){
        _name.value = database.getName().toString()

        println(database.getName())
        println(database.getName())
        println(database.getName())

        _gender.value = database.getGender().toString()
        _email.value = database.getEmail().toString()
        _age.value = database.getAge().toString()

        _finalOut.value = "Name is: $({_name.value}\nEmail is:${_email.value}\nGender: ${_gender.value}\nAge: ${_age.value} "
        println(_finalOut.value)
    }

    fun clearData(){
        database.clear()
    }

    fun insertData(name:String, email:String, gender:String, age:String){

//        viewModelScope.launch (Dispatchers.IO){
            database.insert(Form(name = name, email = email, age = age, gender = gender))
            println("Insert")
            println(name+email+age+gender)


//        }

    }

    }
