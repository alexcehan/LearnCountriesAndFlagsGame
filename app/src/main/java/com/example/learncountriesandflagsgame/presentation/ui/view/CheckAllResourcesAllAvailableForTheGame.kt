package com.example.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.learncountriesandflagsgame.databinding.FragmentCheckAllResourcesAllAvailableForTheGameBinding
import com.example.learncountriesandflagsgame.presentation.ui.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckAllResourcesAllAvailableForTheGame : Fragment() {
    private var _binding: FragmentCheckAllResourcesAllAvailableForTheGameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckAllResourcesAllAvailableForTheGameBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.numberOfCountriesInDb.observe(viewLifecycleOwner) {
            Log.e("NumberUpdated", "Observer in fragment triggered" )


            if(it == 194) {
                Log.e("NumberUpdated", "Observer in fragment triggered in if 194" )

                viewModel.numberOfCountriesInDbIsMet.value = true

            }else if (it >= 0) {
                Log.e("NumberUpdated", "Observer in fragment triggered in if ${it}" )
                viewModel.numberOfCountriesInDbIsMet.value = false
                viewModel.getAllCountriesFromApi()
            }
        }

        viewModel.checkForFlagsMethod.observe(viewLifecycleOwner) {
            if (it == 1) {
                viewModel.allFlagsAreAvailable.value = true
            } else if(it==2) {
                viewModel.downloadAllFlagsImages(requireContext())
            } else if(it == 0) {
                viewModel.checkIfAllFlagsAreDownloaded(requireContext())
            }
        }

        viewModel.numberOfCountriesInDbIsMet.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.checkIfAllFlagsAreDownloaded(requireContext())

            }
        }

        viewModel.allFlagsAreAvailable.observe(viewLifecycleOwner) {
            viewModel.checkIfGameCanBeStarted()
        }



        viewModel.startGameNavigate.observe(viewLifecycleOwner) {
            if (it!!) {
                val action = CheckAllResourcesAllAvailableForTheGameDirections.actionCheckAllResourcesAllAvailableForTheGameToChooseRegionScreenFragment()
                Log.e("NavGraph", "Navigation is triggered " )
                findNavController().navigate(action)
            }
        }

        viewModel.retryButton.observe(viewLifecycleOwner) {
            if (it!!) {
                binding.retryExitButtons.visibility = View.VISIBLE
            }
        }

        binding.retryButton.setOnClickListener {
            viewModel.changeRetryButtonsValues()
            viewModel.numberOfCountriesInDb.value = 1
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}