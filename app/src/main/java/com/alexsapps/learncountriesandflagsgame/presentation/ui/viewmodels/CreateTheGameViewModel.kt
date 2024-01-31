package com.alexsapps.learncountriesandflagsgame.presentation.ui.viewmodels

import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexsapps.learncountriesandflagsgame.R
import com.alexsapps.learncountriesandflagsgame.data.entities.OneLifeStatsCapital
import com.alexsapps.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTheGameViewModel @Inject constructor(
    val countriesAndFlagsDao: CountriesAndFlagsDao

    ): ViewModel() {

    @Inject
    lateinit var stringOfRegions: LiveData<List<String>>

    @Inject
    lateinit var setStringOfRegions: MutableLiveData<List<String>>

    var isEuropePressed: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var isAsiaPressed: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var isAfricaPressed: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var isOceaniaPressed: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var isAmericasPressed: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var isPlayButtonClickable: MutableLiveData<Boolean> = MutableLiveData(false)
    var listOfRegionsButtonPressed: MutableLiveData<List<MutableLiveData<Boolean>>> = MutableLiveData(mutableListOf(isEuropePressed, isAsiaPressed, isAmericasPressed, isAfricaPressed, isOceaniaPressed))



    fun updateStringValue(listOfRegions: List<String>) {
        setStringOfRegions.value = listOfRegions
    }


    fun changeValueOfClickedRegion(region: MutableLiveData<Boolean>) {
        region.value = !region.value!!
        checkIfAnyRegionButtonIsPressed()

    }

    fun checkIfAnyRegionButtonIsPressed() {
        val listOfRegionsButtons = listOfRegionsButtonPressed.value!!
        isPlayButtonClickable.value = listOfRegionsButtons.any { (it.value == true) }

    }

    fun createStringWithRegionsToBePassedToNextScreen(): List<String> {
        val listOfRegions = mutableListOf<String>()


        if(isEuropePressed.value!!) {

            listOfRegions.add("Europe")
        }

        if (isAfricaPressed.value!!) {

            listOfRegions.add("Asia")
        }

        if(isAfricaPressed.value!!) {
            listOfRegions.add("Africa")
        }

        if(isOceaniaPressed.value!!) {
            listOfRegions.add("Oceania")
        }

        if(isAmericasPressed.value!!) {
            listOfRegions.add("Americas")
        }


        return listOfRegions
    }

    fun changeBackgroundOfRegionButton(regionButton: AppCompatButton, isPressed: Boolean) {
        if (isPressed) {
            regionButton.setBackgroundResource(R.drawable.rounded_button_background_checked)
        } else {
            regionButton.setBackgroundResource(R.drawable.rounded_button_background)

        }

    }

    fun insertTest() {
        viewModelScope.launch {
            countriesAndFlagsDao.insert(OneLifeStatsCapital(1, "alex", 20))
        }
    }


}