package com.zeynelinho.kotlincountriesproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zeynelinho.kotlincountriesproject.R
import com.zeynelinho.kotlincountriesproject.databinding.FragmentCountryBinding
import com.zeynelinho.kotlincountriesproject.util.downloadFromUrl
import com.zeynelinho.kotlincountriesproject.util.placeHolderProgressBar
import com.zeynelinho.kotlincountriesproject.viewmodel.CountryViewModel


class CountryFragment : Fragment() {

    private  var _binding : FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private var countryUuid = 0
    private lateinit var countryViewModel : CountryViewModel
    private lateinit var dataBinding : FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        countryViewModel.getDataFromRoom(countryUuid)



        observeLiveData()

    }


    private fun observeLiveData() {

        countryViewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->

            country?.let {

                dataBinding.selectedCountry = it

                /*
                binding.countryName.text = country.countryName
                binding.countryCapital.text = country.countryCapital
                binding.countryLanguage.text = country.countryLanguage
                binding.countryCurrency.text = country.countryCurrency
                binding.countryRegion.text = country.countryRegion
                context?.let {
                    binding.countryImage.downloadFromUrl(country.countryImageUrl,
                        placeHolderProgressBar(it))
                }

                 */
            }
        })

    }


}