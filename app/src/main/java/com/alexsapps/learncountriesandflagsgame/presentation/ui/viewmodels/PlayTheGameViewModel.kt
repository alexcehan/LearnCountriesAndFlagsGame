package com.alexsapps.learncountriesandflagsgame.presentation.ui.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexsapps.learncountriesandflagsgame.R
import com.alexsapps.learncountriesandflagsgame.data.entities.Countries
import com.alexsapps.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayTheGameViewModel @Inject constructor(
    countriesAndFlagsDao: CountriesAndFlagsDao,
    stringOfRegions: LiveData<List<String>>,
    typeOfGame: LiveData<String>



    ) : ViewModel() {

    var isFirstAnswerClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    var isSecondAnswerClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    var isThirdAnswerClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    var isFourthAnswerClicked: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _isViewClickable: MutableLiveData<Boolean> = MutableLiveData(true)
    val isViewClickable: LiveData<Boolean> get() = _isViewClickable

    val scoreOfCurrentGame: MutableLiveData<Int> = MutableLiveData(0)
    var numberOfLives: MutableLiveData<Int> = MutableLiveData(3)

    var isScoreBoardVisible: MutableLiveData<Boolean> = MutableLiveData(true)
    var isHeartsBoardVVisible: MutableLiveData<Boolean> = MutableLiveData(true)

    var countriesInTheSelectedRegions: MutableLiveData<MutableList<Countries>> = MutableLiveData((emptyList<Countries>().toMutableList()))

    init {


        when (typeOfGame.value) {
            "Play3Lives"-> {
                isScoreBoardVisible.value = true
                isHeartsBoardVVisible.value = true
            }

            "SuddenDeath"-> {
                isScoreBoardVisible.value = true
                isHeartsBoardVVisible.value = false
                numberOfLives.value = 1
            }

            "Practice"-> {
                isScoreBoardVisible.value = false
                isHeartsBoardVVisible.value = false
                numberOfLives.value = -1
            }
        }
    }

    val _countriesInTheSelectedRegions = countriesAndFlagsDao.getCountriesFromSelectedRegions(stringOfRegions.value!!)


    var currentCountryToBePlayed: MutableLiveData<Countries> = MutableLiveData()
    var listOfCurrentQuestionOptions: MutableLiveData<List<Countries>> = MutableLiveData()

    init {
        countriesInTheSelectedRegions.value = _countriesInTheSelectedRegions.value?.toMutableList()
    }

    val firstLifeHeart: MutableLiveData<Boolean> = MutableLiveData(true)
    val secondLifeHeart: MutableLiveData<Boolean> = MutableLiveData(true)
    val thirdLifeHeart: MutableLiveData<Boolean> = MutableLiveData(true)

    fun addQuestionOnScreen() {

        listOfCurrentQuestionOptions.value = generateTheListOfFourCountries(countriesInTheSelectedRegions.value!!)
        currentCountryToBePlayed.value = MutableLiveData<Countries>(listOfCurrentQuestionOptions.value!!.get(randomlyDecideTheRightAnswer())).value

    }

    fun updateScoreOfCurrentGame() {
        scoreOfCurrentGame.value = scoreOfCurrentGame.value!!.plus(1)
    }

    fun updateNumberOfLives() {
        numberOfLives.value = numberOfLives.value!!.minus(1)
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
        return currentCountryToBePlayed.value!!.equals(country)
    }

    fun removeCountryFromList(country: Countries) {
        countriesInTheSelectedRegions.value?.remove(country)

    }

    fun changeBackgroundOfButton(view: View, indexOfCoutry: Int) {
        val country = listOfCurrentQuestionOptions.value?.get(indexOfCoutry)!!
        if (checkIfTheAnswerIsRight(country)) {
            view.setBackgroundResource(R.drawable.rounded_button_correct_answer)
            updateScoreOfCurrentGame()
        } else {
            view.setBackgroundResource(R.drawable.rounded_button_wrong_answer)
            updateNumberOfLives()
        }

    }

    fun changeValueOfIsButtonClick(isButton: MutableLiveData<Boolean>) {
        isButton.value = !isButton.value!!

    }

    fun changeQuestionOnScreen() {
        viewModelScope.launch {
            delay(2000)
            resetAllIsClickedButtons()
            checkIfCountryListHasEnoughElementsToPlay()
            addQuestionOnScreen()
            lockTheScreenSwitch()

        }

    }

    fun checkIfCountryListHasEnoughElementsToPlay() {
        if (countriesInTheSelectedRegions.value!!.size > 4) {
            removeCountryFromList(currentCountryToBePlayed.value!!)
        } else {
            countriesInTheSelectedRegions.value = _countriesInTheSelectedRegions.value?.toMutableList()
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
            _isViewClickable.value = _isViewClickable.value != true
        }

    }

    fun clickAnswerOption(isButtonPressed: MutableLiveData<Boolean>) {
        lockTheScreenSwitch()
        changeValueOfIsButtonClick(isButtonPressed)
        changeQuestionOnScreen()

    }

}