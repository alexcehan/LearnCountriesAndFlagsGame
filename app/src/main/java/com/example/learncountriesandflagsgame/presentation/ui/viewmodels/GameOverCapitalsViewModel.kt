package com.example.learncountriesandflagsgame.presentation.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learncountriesandflagsgame.data.entities.OneLifeStatsCapital
import com.example.learncountriesandflagsgame.data.sources.db.CountriesAndFlagsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameOverViewModel @Inject constructor(val countriesAndFlagsDao: CountriesAndFlagsDao) : ViewModel() {

    val currentGameScore : MutableLiveData<Int> = MutableLiveData(0)

    fun saveScoreToLocalDataBase(name: String) {
        viewModelScope.launch {
            countriesAndFlagsDao.insert(OneLifeStatsCapital(0, name, currentGameScore.value!!))
        }
    }
}