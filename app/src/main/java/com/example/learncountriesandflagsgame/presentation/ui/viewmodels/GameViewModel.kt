package com.example.learncountriesandflagsgame.presentation.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncountriesandflagsgame.data.entities.Countries
import com.example.learncountriesandflagsgame.data.entities.OneLifeStatsCapital
import com.example.learncountriesandflagsgame.data.mappers.ApiReturnedCountryToRoomCountryMapper
import com.example.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import com.example.learncountriesandflagsgame.data.usecases.FetchDataFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val fetchDataFromApiUseCase: FetchDataFromApiUseCase,
    private val countriesAndFlagsDao: CountriesAndFlagsDao
) : ViewModel() {

    private val apiReturnedCountryToRoomCountryMapper = ApiReturnedCountryToRoomCountryMapper()
    private val countriesList= mutableListOf<Countries>()




    fun getAllCountriesFromApi() {


        viewModelScope.launch {
            fetchDataFromApiUseCase().collect {
                result -> result
                .filter { apiJsonResultItem -> apiJsonResultItem.capital.size != 0 && apiJsonResultItem.independent}
                .forEach { apiJsonResultItem -> countriesList.add(apiReturnedCountryToRoomCountryMapper.mapper(apiJsonResultItem)) }
            }



            countriesAndFlagsDao.insertAllCountries(countriesList)
        }
    }

    fun insertTest() {
        viewModelScope.launch {
            countriesAndFlagsDao.insert(OneLifeStatsCapital(1, "alex", 20))
        }
    }






}