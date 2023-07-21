package com.example.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.learncountriesandflagsgame.R
import com.example.learncountriesandflagsgame.databinding.FragmentChooseRegionScreenBinding
import com.example.learncountriesandflagsgame.presentation.ui.viewmodels.CreateTheGameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseRegionScreenFragment : Fragment() {
    private var _binding: FragmentChooseRegionScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateTheGameViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseRegionScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.isEuropePressed.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.changeBackgroundOfRegionButton(binding.chooseEuropeRegionButton, it)
            }
        }

        viewModel.isAfricaPressed.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.changeBackgroundOfRegionButton(binding.chooseAfricaRegionButton, it)
            }
        }

        viewModel.isAsiaPressed.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.changeBackgroundOfRegionButton(binding.chooseAsiaRegionButton, it)
            }
        }

        viewModel.isOceaniaPressed.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.changeBackgroundOfRegionButton(binding.chooseOceaniaRegionButton, it)
            }
        }

        viewModel.isAmericasPressed.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.changeBackgroundOfRegionButton(binding.chooseAmericasRegionButton, it)
            }
        }

        viewModel.isPlayButtonClickable.observe(viewLifecycleOwner) { playButton ->
            if (playButton) {
                binding.playButton.setBackgroundResource(R.drawable.rounded_button_background)
            } else {
                binding.playButton.setBackgroundResource(R.drawable.rounded_button_unclickeble)
            }
        }



        binding.chooseAsiaRegionButton.setOnClickListener {
            viewModel.changeValueOfClickedRegion(viewModel.isAsiaPressed)
        }

        binding.chooseAfricaRegionButton.setOnClickListener {
            viewModel.changeValueOfClickedRegion(viewModel.isAfricaPressed)
        }

        binding.chooseEuropeRegionButton.setOnClickListener {
            viewModel.changeValueOfClickedRegion(viewModel.isEuropePressed)

        }

        binding.chooseOceaniaRegionButton.setOnClickListener {
            viewModel.changeValueOfClickedRegion(viewModel.isOceaniaPressed)
        }

        binding.chooseAmericasRegionButton.setOnClickListener {
            viewModel.changeValueOfClickedRegion(viewModel.isAmericasPressed)
        }



        binding.playButton.setOnClickListener {
            //viewModel.insertTest()
            val stringOfRegions = viewModel.createStringWithRegionsToBePassedToNextScreen()
            val action = ChooseRegionScreenFragmentDirections.actionChooseRegionScreenFragmentToChooseCapitalsOrFlagsScreenFragment()
            viewModel.updateStringValue(stringOfRegions)
            this.findNavController().navigate(action)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}