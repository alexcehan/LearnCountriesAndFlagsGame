package com.example.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.learncountriesandflagsgame.databinding.FragmentChooseTypeOfGameScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseTypeOfGameScreenFragment  : Fragment() {
    private var _binding: FragmentChooseTypeOfGameScreenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var typeOfGame: LiveData<String>

    @Inject
    lateinit var setTypeOfGame: MutableLiveData<String>


    @Inject
    lateinit var nameOfGame: LiveData<Boolean>








    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseTypeOfGameScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner






        binding.choosePracticeButoon.setOnClickListener {
            setTypeOfTheGameFunc("Practice")
            navigateToNextScreen()



        }
        binding.choosePlay3LivesButton.setOnClickListener {
            setTypeOfTheGameFunc("Play3Lives")
            navigateToNextScreen()



        }
        binding.chooseSuddenDeathButoon.setOnClickListener {
            setTypeOfTheGameFunc("SuddenDeath")
            navigateToNextScreen()
        }



        return view
    }

    fun setTypeOfTheGameFunc(typeOfGame: String) {
        setTypeOfGame.value = typeOfGame
    }

    fun navigateToNextScreen() {
        if(nameOfGame.value!!) {
            val action = ChooseTypeOfGameScreenFragmentDirections.actionChooseTypeOfGameScreenFragmentToPlayCapitalsFragment()
            this.findNavController().navigate(action)
        } else if(!nameOfGame.value!!) {
            val action = ChooseTypeOfGameScreenFragmentDirections.actionChooseTypeOfGameScreenFragmentToPlayFlagsFragment()
            this.findNavController().navigate(action)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}