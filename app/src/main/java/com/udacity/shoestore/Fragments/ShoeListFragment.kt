package com.udacity.shoestore.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.ViewModel.ShoeViewModel
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {
    lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Initialize Data binding for this fragment
        binding = FragmentShoeListBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //notify that this fragment has menu
        setHasOptionsMenu(true)
        //Initialize view Model
        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]

        //Observe all data from live data object
        viewModel.shoe.observe(viewLifecycleOwner) { shoes ->
            Timber.i("values = ${shoes.size}")
            //So at here we will add new view for each shoe object
            for (shoe in shoes) {
                Timber.i("values = ")
                addView(shoe)
            }
        }
        //Listen to Floating actionbar clicked
        binding.AddShoe.setOnClickListener {
            //Move To Details Fragment To Add new Shoe
            Navigation.findNavController(it)
                .navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
        }
    }

    /**
     * This Method Help us Add new view from (shoe_item.xml) to the linearLayout
     * So we used Data binding and pass the shoe object to observe data for
     * each view with out needing to use findViewById method.
     */
    @SuppressLint("SetTextI18n", "InflateParams")
    private fun addView(shoe: Shoe): Boolean {
        //Initialize Data binding for shoe Item view
        val shoeItemViewBinding: ShoeItemBinding = ShoeItemBinding.inflate(layoutInflater)
        //pass data to xml layout to make it's processes on it
        shoeItemViewBinding.shoe = shoe
        //then pass parent of shoeItem to our LinearLayout
        binding.ShoeListView.addView(shoeItemViewBinding.root)
        //at the end return true
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        //inflate the menu at this fragment
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Navigate to login fragment
        Navigation.findNavController(requireView())
            .navigate(R.id.action_shoeListFragment_to_loginFragment)
        return super.onOptionsItemSelected(item)

    }

}