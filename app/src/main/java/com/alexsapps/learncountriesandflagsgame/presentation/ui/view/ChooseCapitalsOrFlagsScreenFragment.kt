package com.alexsapps.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.alexsapps.learncountriesandflagsgame.databinding.FragmentChooseCapitalsOrFlagsScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ChooseCapitalsOrFlagsScreenFragment  : Fragment() {
    private var _binding: FragmentChooseCapitalsOrFlagsScreenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var setNameOfTheGame: MutableLiveData<Boolean>








    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseCapitalsOrFlagsScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner

        val stringOfRegions = ""



        binding.chooseCapitalsGameButton.setOnClickListener {

            setNameOfTheGame.value = true

            val action = ChooseCapitalsOrFlagsScreenFragmentDirections.actionChooseCapitalsOrFlagsScreenFragmentToChooseTypeOfGameScreenFragment()
            this.findNavController().navigate(action)
        }

        binding.chooseFlagsGameButoon.setOnClickListener {

            setNameOfTheGame.value = false

            val action =
                ChooseCapitalsOrFlagsScreenFragmentDirections.actionChooseCapitalsOrFlagsScreenFragmentToChooseTypeOfGameScreenFragment()
            this.findNavController().navigate(action)
        }


        return view

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}