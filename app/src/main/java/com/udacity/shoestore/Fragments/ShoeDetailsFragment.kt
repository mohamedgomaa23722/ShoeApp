package com.udacity.shoestore.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.Utils.Validation
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ViewModel.ShoeViewModel
import com.udacity.shoestore.WhichIsValidate
import kotlinx.android.synthetic.main.fragment_shoe_details.view.*
import timber.log.Timber


class ShoeDetailsFragment : Fragment(), WhichIsValidate {
    lateinit var binding: FragmentShoeDetailsBinding
    lateinit var shoeName: String
    lateinit var shoeCompany: String
    lateinit var shoeSize: String
    lateinit var shoeDesc: String
    lateinit var viewModel: ShoeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShoeDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Initialize view Model with Activity lifeCycle
        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]

        binding.Submit.setOnClickListener {
            //First : check validation of data
            if (validateShoeDetails()) {
                //Second: if all data is correct then pass our inputs to shoe object
                val shoe = Shoe(shoeName, shoeSize.toDouble(), shoeCompany, shoeDesc)
                //Third: add prev object to our livedata to be notified by shoe List Fragment
                viewModel.addNewShoe(shoe)
                //Fourth: Move back To shoe List Fragment
                goBackToShoeList(it)
            } else {
                // if Data is not validate so shoe this toast
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_LONG).show()
            }
        }
        //When user clicked on cancel
        binding.cancel.setOnClickListener {
            //move to shoe List Fragment
            goBackToShoeList(it)
        }

    }

    /**
     * This Method help you to move back to shoe list however user
     * clicked into submit or cancel
     */
    private fun goBackToShoeList(it:View){
        Navigation.findNavController(it)
            .navigate(
                ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment()
            )
    }

    /**
     * Main used pf this class to avoid empty fields which may cause huge problems and
     * some bugs so we check the values for each field
     */
    private fun validateShoeDetails(): Boolean {
        //First : Get All values from Input fields
        shoeName = binding.shoeName.text.toString()
        shoeCompany = binding.shoeCompany.text.toString()
        shoeSize = binding.shoeSize.text.toString()
        shoeDesc = binding.shoeDetails.shoeDetails.text.toString()
        return Validation(shoeName, shoeSize, shoeCompany, shoeDesc,this).validate()
    }

    /**
     * This Method Help us to check if Name Input Field is empty or not
     */
    override fun validateName(isNameValid: Boolean) {
        if (!isNameValid)
            binding.shoeName.error = "Please Enter The Name of The Shoe"
    }
    /**
     * This Method Help us to check if Size Input Field is empty or not
     */
    override fun validateSize(isSizeValid: Boolean) {
        if (!isSizeValid)
            binding.shoeSize.error = "Please Enter The Size of The Shoe"
    }
    /**
     * This Method Help us to check if Company Input Field is empty or not
     */
    override fun validateCompany(isCompanyValid: Boolean) {
        if (!isCompanyValid)
            binding.shoeCompany.error = "Please Enter The Company of The Shoe"
    }
    /**
     * This Method Help us to check if Description Input Field is empty or not
     */
    override fun validateDescription(isDescriptionValid: Boolean) {
        if (!isDescriptionValid)
            binding.shoeDetails.error = "Please Enter The description of The Shoe"
    }
}