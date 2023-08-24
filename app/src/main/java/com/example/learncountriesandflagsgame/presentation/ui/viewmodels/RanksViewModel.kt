package com.example.learncountriesandflagsgame.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.learncountriesandflagsgame.data.entities.RankItem
import com.example.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import com.example.learncountriesandflagsgame.presentation.ui.view.RanksFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RanksViewModel @Inject constructor(
    val countriesAndFlagsDao: CountriesAndFlagsDao,
    val typeOfGame: LiveData<String>,
    val nameOfGame: LiveData<Boolean>) : ViewModel() {

    val currentScore: MutableLiveData<Int> = MutableLiveData(0)



    private lateinit var   _ranksList: LiveData<List<RankItem>>
    private lateinit var _currentRankPosition: LiveData<Int>
    lateinit var restartAction: NavDirections
    init {
        when {
            nameOfGame.value!! -> {
                restartAction= RanksFragmentDirections.actionRanksFragmentToPlayCapitalsFragment()
                when(typeOfGame.value) {
                    "Play3Lives" -> {_ranksList = countriesAndFlagsDao.getTopFiveRankPositionsFromThreeLivesCapitalTable()
                                     }
                    "SuddenDeath"-> {_ranksList = countriesAndFlagsDao.getTopFiveRankPositionsFromOneLifeCapitalTable()
                                    }
                }
            }

            !nameOfGame.value!! -> {
                restartAction = RanksFragmentDirections.actionRanksFragmentToPlayFlagsFragment()
                when(typeOfGame.value) {
                    "Play3Lives" -> _ranksList = countriesAndFlagsDao.getTopFiveRankPositionsFromThreeLivesFlagsTable()
                    "SuddenDeath"-> _ranksList = countriesAndFlagsDao.getTopFiveRankPositionsFromOneLifeFlagsTable()
                }
            }
        }
        updateCurrentRankPosition()
    }

    val rankList get() = _ranksList
    val currentRankPosition get() =  _currentRankPosition




    fun updateCurrentRankPosition() {
        when {
            nameOfGame.value!! -> {
                when(typeOfGame.value) {
                    "Play3Lives" -> _currentRankPosition = countriesAndFlagsDao.getTheRankOnThreeLivesCapitalBasedOnCurrentScore(currentScore.value!!)
                    "SuddenDeath"-> _currentRankPosition = countriesAndFlagsDao.getTheRankOnOneLifeCapitalBasedOnCurrentScore(currentScore.value!!)
                }
            }

            !nameOfGame.value!! -> {
                when(typeOfGame.value) {
                    "Play3Lives" -> _currentRankPosition = countriesAndFlagsDao.getTheRankOnThreeLivesFlagsBasedOnCurrentScore(currentScore.value!!)
                    "SuddenDeath"-> _currentRankPosition = countriesAndFlagsDao.getTheRankOnOneLifeFlagBasedOnCurrentScore(currentScore.value!!)
                }
            }
        }
    }

    fun initializeRanksWhenScoreChange() {

    }
}