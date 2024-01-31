package com.alexsapps.learncountriesandflagsgame.presentation.ui.viewmodels

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexsapps.learncountriesandflagsgame.data.entities.Countries
import com.alexsapps.learncountriesandflagsgame.data.entities.OneLifeStatsCapital
import com.alexsapps.learncountriesandflagsgame.data.mappers.ApiReturnedCountryToRoomCountryMapper
import com.alexsapps.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import com.alexsapps.learncountriesandflagsgame.data.usecases.FetchDataFromApiUseCase
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val fetchDataFromApiUseCase: FetchDataFromApiUseCase,
    private val countriesAndFlagsDao: CountriesAndFlagsDao
) : ViewModel() {

    private val apiReturnedCountryToRoomCountryMapper = ApiReturnedCountryToRoomCountryMapper()
    private val countriesList= mutableSetOf<Countries>()

    var numberOfCountriesInDb: MutableLiveData<Int> = MutableLiveData(-1)
    var numberOfCountriesInDbIsMet:  MutableLiveData<Boolean> = MutableLiveData()
    val allFlagsAreAvailable: MutableLiveData<Boolean> = MutableLiveData(false)
    val startGameNavigate: MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingGameMessage: MutableLiveData<String> = MutableLiveData("The game is loading!")
    val retryButton: MutableLiveData<Boolean> = MutableLiveData(false)
    val checkForFlagsMethod: MutableLiveData<Int> = MutableLiveData(0)



    init {
        countriesAndFlagsDao.getTheNumberOfCountriesInDb().observeForever { count ->

            numberOfCountriesInDb.value = count
            Log.e("NumberUpdated", "Number of countries in DB: ${count} " )


        }

    }





    fun changeRetryButtonsValues() {
        if(!retryButton.value!!) {
            retryButton.value = true
            loadingGameMessage.value = "There was a problem!"
        } else {
            retryButton.value = false
            loadingGameMessage.value = "The game is loading!"
        }
    }

    fun getAllCountriesFromApi() {

        try {
            viewModelScope.launch {
                fetchDataFromApiUseCase().collect {
                        result -> result
                    .filter { apiJsonResultItem -> apiJsonResultItem.capital.size != 0 && apiJsonResultItem.independent}
                    .forEach { apiJsonResultItem -> countriesList.add(apiReturnedCountryToRoomCountryMapper.mapper(apiJsonResultItem)) }
                }

                if (countriesList.size == 194) {
                    countriesAndFlagsDao.insertAllCountries(countriesList.toList())


                } else {
                    changeRetryButtonsValues()
                    throw Exception("Problem with importing all countries data!")

                }

                numberOfCountriesInDbIsMet.value = true



            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e

        }

    }

    fun downloadAllFlagsImages(context: Context)  {
        viewModelScope.launch {
            try {
                countriesList.forEach {
                        countries -> if (!checkIfFileExists(countries.countryName)) {
                        loadImageToPicassoAndSaveLocally(countries, context)

                }
                }
                checkForFlagsMethod.value = 0
            } catch (e: IOException) {
                e.printStackTrace()
                changeRetryButtonsValues()
            }

        }


    }




    fun checkIfAllFlagsAreDownloaded(context: Context) {
        Log.e("NumberUpdated", "CheckIfAllFlags method Started!" )
        var numberOfFlags = 0
        viewModelScope.launch {

                Log.e("NumberUpdated", "CheckIfAllFlags method Started! In Suspended block!" )
                countriesList.forEach {
                        countries ->
                    run {
                        if (!checkIfFileExists(countries.countryName)) {
                            Log.e("CreateGame", "Doesn't Exists flag: ${countries.countryName}")
                            checkForFlagsMethod.value = 2
                            return@launch

                        }
                    }
                }
            checkForFlagsMethod.value = 1






        }




    }

    fun loadImageToPicassoAndSaveLocally(countries: Countries, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {

                try {
                    val bitmap: Bitmap = Picasso.get().load(countries.flagUrl).get()
                    saveImageToPictures(bitmap, countries.countryName, context)
                } catch (e: IOException) {
                    throw e
                }



        }
    }

    fun saveImageToPictures(bitmap: Bitmap, countryName: String, context: Context) {



        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image_${countryName}.png")
        file.createNewFile()

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
        } catch (e: IOException) {
            throw e
        } finally {
            fos?.close()
        }
    }

    private fun checkIfFileExists(countryName: String): Boolean {
        val directory =
            File("/storage/emulated/0/Android/data/com.example.learncountriesandflagsgame/files/Pictures")
        val file = File(directory, "image_${countryName}.png")
        return file.exists()
    }

    fun checkIfGameCanBeStarted() {
       Log.e("CreateGame", "checkIfGameCanBeStarted: allflagsavailable: ${allFlagsAreAvailable.value} and number of countries: ${numberOfCountriesInDb.value}", )
        if (allFlagsAreAvailable.value == true && numberOfCountriesInDbIsMet.value == true) {
            startGameNavigate.value = true
        }
    }

    fun insertTest() {
        viewModelScope.launch {
            countriesAndFlagsDao.insert(OneLifeStatsCapital(1, "alex", 20))
        }
    }








}