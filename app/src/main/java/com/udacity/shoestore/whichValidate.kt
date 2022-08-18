package com.udacity.shoestore

interface WhichIsValidate {
    fun validateName(isNameValid:Boolean)
    fun validateSize(isSizeValid:Boolean)
    fun validateCompany(isCompanyValid:Boolean)
    fun validateDescription(isDescriptionValid:Boolean)
}