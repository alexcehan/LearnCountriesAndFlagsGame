package com.example.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.learncountriesandflagsgame.R
import com.example.learncountriesandflagsgame.databinding.FragmentChooseTypeOfGameScreenBinding
import com.example.learncountriesandflagsgame.presentation.ui.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseTypeOfGameScreenFragment : Fragment() {
    private var _binding: FragmentChooseTypeOfGameScreenBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseTypeOfGameScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner

        val gameName = ChooseTypeOfGameScreenFragmentArgs.fromBundle(requireArguments()).nameOfTheGame
        val stringOfRegions = ""


        binding.choosePracticeButoon.setOnClickListener {
            val typeOfTheGame = "Practice"
            if(gameName.equals("Capitals")) {
                val action = ChooseTypeOfGameScreenFragmentDirections.actionChooseTypeOfGameScreenFragmentToPlayCapitalsFragment(typeOfTheGame)
                this.findNavController().navigate(action)
            } else if(gameName.equals("Flags")) {
                val action = ChooseTypeOfGameScreenFragmentDirections.actionChooseTypeOfGameScreenFragmentToPlayFlagsFragment(typeOfTheGame)
                this.findNavController().navigate(action)

            }



        }
        binding.choosePlay3LivesButton.setOnClickListener {
            val typeOfTheGame = "Play3Lives"
            if(gameName.equals("Capitals")) {
                val action = ChooseTypeOfGameScreenFragmentDirections.actionChooseTypeOfGameScreenFragmentToPlayCapitalsFragment(typeOfTheGame)
                this.findNavController().navigate(action)
            } else if(gameName.equals("Flags")) {
                val action = ChooseTypeOfGameScreenFragmentDirections.actionChooseTypeOfGameScreenFragmentToPlayFlagsFragment(typeOfTheGame)
                this.findNavController().navigate(action)

            }



        }
        binding.chooseSuddenDeathButoon.setOnClickListener {
            val typeOfTheGame = "SuddenDeath"
            if(gameName.equals("Capitals")) {
                val action = ChooseTypeOfGameScreenFragmentDirections.actionChooseTypeOfGameScreenFragmentToPlayCapitalsFragment(typeOfTheGame)
                this.findNavController().navigate(action)
            } else if(gameName.equals("Flags")) {
                val action = ChooseTypeOfGameScreenFragmentDirections.actionChooseTypeOfGameScreenFragmentToPlayFlagsFragment(typeOfTheGame)
                this.findNavController().navigate(action)

            }
        }



        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}