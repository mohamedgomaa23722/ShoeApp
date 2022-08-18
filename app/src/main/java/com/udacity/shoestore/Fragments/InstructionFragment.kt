package com.udacity.shoestore.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstractionBinding


class InstructionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        //initialize data binding for that fragment
        val binding:FragmentInstractionBinding = FragmentInstractionBinding.inflate(layoutInflater)
        //Handle onclick listener for moveToShowList button
        binding.moveToShowList.setOnClickListener {
            //when click on this button navigate to shoe list fragment
            Navigation.findNavController(it).navigate(R.id.action_instructionFragment_to_shoeListFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}