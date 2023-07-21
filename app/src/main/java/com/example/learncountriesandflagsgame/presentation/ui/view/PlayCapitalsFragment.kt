package com.example.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.learncountriesandflagsgame.R
import com.example.learncountriesandflagsgame.databinding.FragmentPlayCapitalsBinding
import com.example.learncountriesandflagsgame.presentation.ui.viewmodels.PlayTheGameViewModel
import dagger.hilt.android.AndroidEntryPoint


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


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.countriesInTheSelectedRegions.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                viewModel.addQuestionOnScreen()
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





