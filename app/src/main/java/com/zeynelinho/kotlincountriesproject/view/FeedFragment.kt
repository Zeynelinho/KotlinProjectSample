package com.zeynelinho.kotlincountriesproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeynelinho.kotlincountriesproject.adapter.CountryAdapter
import com.zeynelinho.kotlincountriesproject.databinding.FragmentFeedBinding
import com.zeynelinho.kotlincountriesproject.viewmodel.FeedViewModel


class FeedFragment : Fragment() {

    private  var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var feedViewModel : FeedViewModel
    private var countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        feedViewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        feedViewModel.refreshData()


        binding.countryRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.countryRecyclerView.adapter = countryAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.countryRecyclerView.visibility = View.GONE
            binding.countryErrorText.visibility = View.GONE
            binding.countryRecyclerProgress.visibility = View.VISIBLE
            feedViewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }


    private fun observeLiveData() {

        feedViewModel.countries.observe(viewLifecycleOwner, Observer { countries ->

            countries?.let {
                binding.countryRecyclerView.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })


        feedViewModel.countryError.observe(viewLifecycleOwner, Observer { error ->

            error?.let {
                if (it) {
                    binding.countryErrorText.visibility = View.VISIBLE
                    binding.countryRecyclerProgress.visibility = View.GONE
                    binding.countryRecyclerView.visibility = View.GONE
                }else {
                    binding.countryErrorText.visibility = View.GONE
                }
            }
        })

        feedViewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->

            loading?.let {
                if (it) {
                    binding.countryRecyclerProgress.visibility = View.VISIBLE
                    binding.countryRecyclerView.visibility = View.GONE
                    binding.countryErrorText.visibility = View.GONE

                }else {
                    binding.countryRecyclerProgress.visibility = View.GONE
                }
            }
        })
    }


}