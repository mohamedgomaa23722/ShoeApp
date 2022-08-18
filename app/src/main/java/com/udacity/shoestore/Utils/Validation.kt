package com.udacity.shoestore.Utils

import com.udacity.shoestore.WhichIsValidate

open class Validation(
    private val name: String,
    private val size: String,
    private val company: String,
    private val Desc: String,
    validationInterface: WhichIsValidate
) {
    init {
        //Check Values isEmpty or not and pass it to Interface
        validationInterface.validateName(name.isEmpty().not())
        validationInterface.validateCompany(company.isEmpty().not())
        validationInterface.validateSize(size.isEmpty().not())
        validationInterface.validateDescription(Desc.isEmpty().not())
    }

    /**
     * Check Validation for passed Values
     */
    fun validate(): Boolean {
        //if not it will return true
        return (name.isEmpty() || company.isEmpty() || Desc.isEmpty() || size.isEmpty()).not()
    }

}