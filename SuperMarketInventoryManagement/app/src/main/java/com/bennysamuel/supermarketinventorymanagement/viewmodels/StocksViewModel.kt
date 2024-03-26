package com.bennysamuel.supermarketinventorymanagement.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StocksViewModel:ViewModel() {

    private val _cheeseStock = MutableLiveData<Int>()
    var cheeseStock: LiveData<Int> = _cheeseStock

    private val _chipsStock = MutableLiveData<Int>()
    var chipsStock: LiveData<Int> = _chipsStock

    private val _chocoStock = MutableLiveData<Int>()
    var chocoStock: LiveData<Int> = _chocoStock

    private val _milkStock = MutableLiveData<Int>()
    var milkStock: LiveData<Int> = _milkStock

    private val _ramenStock = MutableLiveData<Int>()
    var ramenStock: LiveData<Int> = _ramenStock

    private val _sodaStock = MutableLiveData<Int>()
    var sodaStock: LiveData<Int> = _sodaStock


    init {
        _cheeseStock.value = 0
        _chipsStock.value = 0
        _chocoStock.value = 0

        _milkStock.value = 0
        _ramenStock.value = 0
        _sodaStock.value = 0
    }

    fun incCheese(){
       _cheeseStock.value = _cheeseStock.value?.plus(1)
    }

    fun decCheese(){
        if(_cheeseStock.value!! >0){
            _cheeseStock.value = _cheeseStock.value?.minus(1)
        }
    }
    fun setCheeseValue(num:Int){
        _cheeseStock.value = num
    }





    fun incChips(){
        _chipsStock.value = _chipsStock.value?.plus(1)
    }

    fun decChips(){
        if(_chipsStock.value!! >0){
            _chipsStock.value = _chipsStock.value?.minus(1)
        }
    }
    fun setChipsValue(num:Int){
        _chipsStock.value = num
    }


    fun incChoco(){
        _chocoStock.value = _chocoStock.value?.plus(1)
    }

    fun decChoco(){
        if(_chocoStock.value!! >0){
            _chocoStock.value = _chocoStock.value?.minus(1)
        }
    }
    fun setChocoValue(num:Int){
        _chocoStock.value = num
    }


    ////////////////////////

    fun incMilk(){
        _milkStock.value = _milkStock.value?.plus(1)
    }

    fun decMilk(){
        if(_milkStock.value!! >0){
            _milkStock.value = _milkStock.value?.minus(1)
        }
    }
    fun setMilkValue(num:Int){
        _milkStock.value = num
    }





    fun incRamen(){
        _ramenStock.value = _ramenStock.value?.plus(1)
    }

    fun decRamen(){
        if(_ramenStock.value!! >0){
            _ramenStock.value = _ramenStock.value?.minus(1)
        }
    }
    fun setRamenValue(num:Int){
        _ramenStock.value = num
    }


    fun incSoda(){
        _sodaStock.value = _sodaStock.value?.plus(1)
    }

    fun decSoda(){
        if(_sodaStock.value!! >0){
            _sodaStock.value = _sodaStock.value?.minus(1)
        }
    }
    fun setSodaValue(num:Int){
        _sodaStock.value = num
    }
}