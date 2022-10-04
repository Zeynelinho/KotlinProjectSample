package com.zeynelinho.kotlincountriesproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zeynelinho.kotlincountriesproject.R
import com.zeynelinho.kotlincountriesproject.databinding.RecyclerRowBinding
import com.zeynelinho.kotlincountriesproject.model.Country
import com.zeynelinho.kotlincountriesproject.util.downloadFromUrl
import com.zeynelinho.kotlincountriesproject.util.placeHolderProgressBar
import com.zeynelinho.kotlincountriesproject.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.recycler_row.view.*

class CountryAdapter(private val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>(), CountryClickListener {

    class CountryHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = DataBindingUtil.inflate<RecyclerRowBinding>(LayoutInflater.from(parent.context),R.layout.recycler_row,parent,false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        holder.binding.country = countryList[position]
        holder.binding.listener = this

        /*holder.binding.recyclerCountryName.text = countryList[position].countryName
        holder.binding.recyclerCountryRegion.text = countryList[position].countryRegion

        holder.itemView.setOnClickListener {

            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.recyclerImage.downloadFromUrl(countryList[position].countryImageUrl,
            placeHolderProgressBar(holder.itemView.context)
        )

         */

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>) {

        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()

    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}