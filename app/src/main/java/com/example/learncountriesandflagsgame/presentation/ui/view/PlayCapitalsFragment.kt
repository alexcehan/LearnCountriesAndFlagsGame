package com.example.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.learncountriesandflagsgame.R
import com.example.learncountriesandflagsgame.databinding.FragmentPlayCapitalsBinding
import com.example.learncountriesandflagsgame.presentation.ui.viewmodels.PlayTheGameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class PlayCapitalsFragment : Fragment() {
    private var _binding: FragmentPlayCapitalsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlayTheGameViewModel by viewModels()







    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentPlayCapitalsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner




        viewModel.firstLifeHeart.observe(viewLifecycleOwner) {
            if (it) {
                binding.firstLifeHeart.setBackgroundResource(R.drawable.full_heart_life)
            } else {
                binding.firstLifeHeart.setBackgroundResource(R.drawable.empty_heart_life)
            }
        }

        viewModel.secondLifeHeart.observe(viewLifecycleOwner) {
            if (it) {
                binding.secondLifeHeart.setBackgroundResource(R.drawable.full_heart_life)
            } else {
                binding.secondLifeHeart.setBackgroundResource(R.drawable.empty_heart_life)
            }
        }

        viewModel.thirdLifeHeart.observe(viewLifecycleOwner) {
            if (it) {
                binding.thirdLifeHeart.setBackgroundResource(R.drawable.full_heart_life)
            } else {
                binding.thirdLifeHeart.setBackgroundResource(R.drawable.empty_heart_life)
            }
        }

        viewModel.numberOfLives.observe(viewLifecycleOwner) {
            when(it) {
                2 -> viewModel.thirdLifeHeart.value = false
                1 -> viewModel.secondLifeHeart.value = false
                0 -> {viewModel.firstLifeHeart.value = false
                    viewModel.viewModelScope.launch {
                        delay(2000)


                        val currentScore = viewModel.scoreOfCurrentGame.value?: 0
                        val action = if (currentScore !=0) {
                            PlayCapitalsFragmentDirections.actionPlayCapitalsFragmentToGameOverFragment("${currentScore}")
                        } else {
                            PlayCapitalsFragmentDirections.actionPlayCapitalsFragmentToChooseRegionScreenFragment()
                        }
                        findNavController().navigate(action)
                    }
                    }

            }
        }

        viewModel.isScoreBoardVisible.observe(viewLifecycleOwner) {
            when(it) {
                true -> binding.groupOfScore.visibility = View.VISIBLE
                false -> binding.groupOfScore.visibility = View.GONE
            }
        }

        viewModel.isHeartsBoardVVisible.observe(viewLifecycleOwner) {
            when(it) {
                true -> binding.groupOfHearts.visibility = View.VISIBLE
                false -> binding.groupOfHearts.visibility = View.GONE
            }
        }




        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.countriesInTheSelectedRegions.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                if (list.isNotEmpty()) {
                    viewModel.addQuestionOnScreen()
                }
            }
        }

        viewModel._countriesInTheSelectedRegions.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.countriesInTheSelectedRegions.value = viewModel._countriesInTheSelectedRegions.value?.toMutableList()
            }
        }

        viewModel.isFirstAnswerClicked.observe(viewLifecycleOwner, Observer {

            if (it) {
                viewModel.changeBackgroundOfButton(binding.firstAnswerCapitals, 0)


            } else {
                binding.firstAnswerCapitals.setBackgroundResource(R.drawable.rounded_button_background)


            }
        })

        viewModel.isSecondAnswerClicked.observe(viewLifecycleOwner, Observer {

            if (it) {
                viewModel.changeBackgroundOfButton(binding.secondAnswerCapitals, 1)


            } else {
                binding.secondAnswerCapitals.setBackgroundResource(R.drawable.rounded_button_background)

            }
        })

        viewModel.isThirdAnswerClicked.observe(viewLifecycleOwner, Observer {

            if (it) {
                viewModel.changeBackgroundOfButton(binding.thirdAnswerCapitals, 2)


            } else {
                binding.thirdAnswerCapitals.setBackgroundResource(R.drawable.rounded_button_background)

            }
        })

        viewModel.isFourthAnswerClicked.observe(viewLifecycleOwner, Observer {

            if (it) {
                viewModel.changeBackgroundOfButton(binding.fourthAnswersCapitals, 3)
            } else {
                binding.fourthAnswersCapitals.setBackgroundResource(R.drawable.rounded_button_background)

            }
        })

        binding.firstAnswerCapitals.setOnClickListener {
            viewModel.clickAnswerOption(viewModel.isFirstAnswerClicked)

        }

        binding.secondAnswerCapitals.setOnClickListener {
            viewModel.clickAnswerOption(viewModel.isSecondAnswerClicked)

        }

        binding.thirdAnswerCapitals.setOnClickListener {
            viewModel.clickAnswerOption(viewModel.isThirdAnswerClicked)

        }

        binding.fourthAnswersCapitals.setOnClickListener {
            viewModel.clickAnswerOption(viewModel.isFourthAnswerClicked)

        }
    }

}





