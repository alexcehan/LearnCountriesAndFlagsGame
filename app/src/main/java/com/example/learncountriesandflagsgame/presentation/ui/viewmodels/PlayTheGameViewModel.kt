package com.example.learncountriesandflagsgame.presentation.ui.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncountriesandflagsgame.R
import com.example.learncountriesandflagsgame.data.entities.Countries
import com.example.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayTheGameViewModel @Inject constructor(
    private val countriesAndFlagsDao: CountriesAndFlagsDao,
    val stringOfRegions: LiveData<List<String>>



    ) : ViewModel() {

    var isFirstAnswerClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    var isSecondAnswerClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    var isThirdAnswerClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    var isFourthAnswerClicked: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _isFirstAnswerButtonClickable: MutableLiveData<Boolean> = MutableLiveData(true)
    val isViewClickable: LiveData<Boolean> get() = _isFirstAnswerButtonClickable

    var isSecondAnswerButtonClickable: MutableLiveData<Boolean> = MutableLiveData(true)
    var isThirdAnswerButtonClickable: MutableLiveData<Boolean> = MutableLiveData(true)
    var isFourthAnswerButtonClickable: MutableLiveData<Boolean> = MutableLiveData(true)



    var numberOfLives: MutableLiveData<Int> = MutableLiveData(3)
    var typeOfTheGame: String = ""
    var countriesInTheSelectedRegions = countriesAndFlagsDao.getCountriesFromSelectedRegions(stringOfRegions.value!!)
    var currentCountryToBePlayed: MutableLiveData<Countries> = MutableLiveData()
    var listOfCurrentQuestionOptions: MutableLiveData<List<Countries>> = MutableLiveData()

    fun addQuestionOnScreen() {

        listOfCurrentQuestionOptions.value = generateTheListOfFourCountries(countriesInTheSelectedRegions.value!!)
        currentCountryToBePlayed.value = MutableLiveData<Countries>(listOfCurrentQuestionOptions!!.value!!.get(randomlyDecideTheRightAnswer())).value

    }

    fun generateTheListOfFourCountries(countrieList: List<Countries>): List<Countries> {
        val randomList = mutableSetOf<Countries>()
        val maxIndex = countrieList.size -1

        while (randomList.size < 4) {
            val randomCountry = (0..maxIndex).random()
            randomList.add(countrieList.get(randomCountry))
        }

        return (randomList.toList())
    }

    fun randomlyDecideTheRightAnswer(): Int {
        return (0 .. 3).random()
    }

    fun checkIfTheAnswerIsRight(country: Countries): Boolean {
        return currentCountryToBePlayed!!.value!!.equals(country)
    }

    fun removeCountryFromList(country: Countries) {

    }

    fun changeBackgroundOfButton(view: View, indexOfCoutry: Int) {
        val country = listOfCurrentQuestionOptions.value?.get(indexOfCoutry)!!
        if (checkIfTheAnswerIsRight(country)) {
            view.setBackgroundResource(R.drawable.rounded_button_correct_answer)
        } else {
            view.setBackgroundResource(R.drawable.rounded_button_wrong_answer)
        }

    }

    fun changeValueOfIsButtonClick(isButton: MutableLiveData<Boolean>) {
        isButton.value = !isButton.value!!

    }

    fun changeQuestionOnScreen() {
        viewModelScope.launch {
            delay(2000)
            resetAllIsClickedButtons()
            addQuestionOnScreen()
            lockTheScreenSwitch()

        }

    }

    fun resetAllIsClickedButtons() {
        isFirstAnswerClicked.value = false
        isSecondAnswerClicked.value = false
        isThirdAnswerClicked.value = false
        isFourthAnswerClicked.value = false
    }

    fun lockTheScreenSwitch() {
        viewModelScope.launch {
            _isFirstAnswerButtonClickable.value = _isFirstAnswerButtonClickable.value != true
        }

    }

    fun clickAnswerOption(isButtonPressed: MutableLiveData<Boolean>) {
        lockTheScreenSwitch()
        changeValueOfIsButtonClick(isButtonPressed)
        changeQuestionOnScreen()

    }


}