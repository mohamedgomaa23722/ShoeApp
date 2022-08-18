package com.udacity.shoestore.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

/**
 * We used Encapsulation at this Class
 */
class ShoeViewModel : ViewModel() {

    private val _Shoe = MutableLiveData<MutableList<Shoe>>()
    private val shoe_list: MutableList<Shoe> = mutableListOf()

    /**
     * Return LiveData object
     */
    val shoe: LiveData<MutableList<Shoe>>
        get() = _Shoe

    /**
     * Add new Shoe to the list
     */
    fun addNewShoe(shoe: Shoe) {
        //First add this shoe to shoe mutable list
        shoe_list.add(shoe)
        //Second update the value of MutableLiveData with the shoe MutableList
        _Shoe.value = shoe_list
    }
}