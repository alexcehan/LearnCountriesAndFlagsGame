package com.example.learncountriesandflagsgame.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncountriesandflagsgame.data.entities.OneLifeStatsCapital
import com.example.learncountriesandflagsgame.data.entities.OneLifeStatsFlags
import com.example.learncountriesandflagsgame.data.entities.ThreeLivesStatsCapital
import com.example.learncountriesandflagsgame.data.entities.ThreeLivesStatsFlags
import com.example.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameOverCapitalsViewModel @Inject constructor(
    val countriesAndFlagsDao: CountriesAndFlagsDao,
    val typeOfGame: LiveData<String>,
    val nameOfGame: LiveData<Boolean>
    ) : ViewModel() {



    val currentGameScore : MutableLiveData<Int> = MutableLiveData(0)
    val playerName: MutableLiveData<String> = MutableLiveData("")

    fun saveScoreToLocalDataBase() {
        when {
            nameOfGame.value!! ->{
                when(typeOfGame.value) {
                    "Play3Lives"-> {
                        viewModelScope.launch {
                            countriesAndFlagsDao.insert(ThreeLivesStatsCapital(0, playerName.value!!, currentGameScore.value!!))
                        }
                    }

                    "SuddenDeath"-> {
                        viewModelScope.launch {
                            countriesAndFlagsDao.insert(OneLifeStatsCapital(0, playerName.value!!, currentGameScore.value!!))
                        }

                    }
                }
            }

            !nameOfGame.value!! -> {
                when(typeOfGame.value) {
                    "Play3Lives"-> {
                        viewModelScope.launch {
                            countriesAndFlagsDao.insert(ThreeLivesStatsFlags(0, playerName.value!!, currentGameScore.value!!))
                        }
                    }

                    "SuddenDeath"-> {
                        viewModelScope.launch {
                            countriesAndFlagsDao.insert(OneLifeStatsFlags(0, playerName.value!!, currentGameScore.value!!))
                        }

                    }
                }
            }

        }


    }
}