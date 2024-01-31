package com.alexsapps.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.alexsapps.learncountriesandflagsgame.R
import com.alexsapps.learncountriesandflagsgame.databinding.FragmentPlayFlagsBinding
import com.alexsapps.learncountriesandflagsgame.presentation.ui.viewmodels.PlayTheGameViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlayFlagsFragment : Fragment() {
    private var _binding: FragmentPlayFlagsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PlayTheGameViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayFlagsBinding.inflate(inflater, container, false)
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
                        val action = PlayFlagsFragmentDirections.actionPlayFlagsFragmentToGameOverFragment("${currentScore}")





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

        viewModel.currentCountryToBePlayed.observe(viewLifecycleOwner) {
            it?.let {
                Log.e("ImageView", "ImageView observer is Runned! image_${it.countryName}" )

                val file = "file:///storage/emulated/0/Android/data/com.example.learncountriesandflagsgame/files/Pictures/image_${it.countryName}.png"
                Picasso.get().load(file).into(binding.flagImageToBePlayed)
            }

        }

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
                viewModel.changeBackgroundOfButton(binding.firstAnswerFlags, 0)


            } else {
                binding.firstAnswerFlags.setBackgroundResource(R.drawable.rounded_button_background)


            }
        })

        viewModel.isSecondAnswerClicked.observe(viewLifecycleOwner, Observer {

            if (it) {
                viewModel.changeBackgroundOfButton(binding.secondAnswerFlags, 1)


            } else {
                binding.secondAnswerFlags.setBackgroundResource(R.drawable.rounded_button_background)

            }
        })

        viewModel.isThirdAnswerClicked.observe(viewLifecycleOwner, Observer {

            if (it) {
                viewModel.changeBackgroundOfButton(binding.thirdAnswerFlags, 2)


            } else {
                binding.thirdAnswerFlags.setBackgroundResource(R.drawable.rounded_button_background)

            }
        })

        viewModel.isFourthAnswerClicked.observe(viewLifecycleOwner, Observer {

            if (it) {
                viewModel.changeBackgroundOfButton(binding.fourthAnswersFlags, 3)
            } else {
                binding.fourthAnswersFlags.setBackgroundResource(R.drawable.rounded_button_background)

            }
        })

        binding.firstAnswerFlags.setOnClickListener {
            viewModel.clickAnswerOption(viewModel.isFirstAnswerClicked)

        }

        binding.secondAnswerFlags.setOnClickListener {
            viewModel.clickAnswerOption(viewModel.isSecondAnswerClicked)

        }

        binding.thirdAnswerFlags.setOnClickListener {
            viewModel.clickAnswerOption(viewModel.isThirdAnswerClicked)

        }

        binding.fourthAnswersFlags.setOnClickListener {
            viewModel.clickAnswerOption(viewModel.isFourthAnswerClicked)

        }
    }


}