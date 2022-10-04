package com.zeynelinho.kotlincountriesproject.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zeynelinho.kotlincountriesproject.model.Country

@Dao
interface CountryDao {

    @Query("SELECT * FROM Country")
    suspend fun getAllCountries() : List<Country>


    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>

    @Query("SELECT * FROM Country WHERE uuid= :countryId")
    suspend fun getCountry (countryId : Int) : Country

    @Query("DELETE FROM Country")
    suspend fun deleteAllCountries()

}