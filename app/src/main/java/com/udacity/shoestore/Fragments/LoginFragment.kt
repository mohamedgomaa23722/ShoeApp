package com.udacity.shoestore.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment(),View.OnClickListener {
    lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        //initialize data binding for that fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //This is for Handling on clicks Buttons
        binding.Login.setOnClickListener(this)
        binding.signUp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        //Either login or signUp it will navigate to Welcome fragment
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_welcomeFragment)
    }

}