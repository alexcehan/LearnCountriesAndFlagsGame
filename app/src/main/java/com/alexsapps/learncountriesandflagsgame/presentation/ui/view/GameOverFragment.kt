package com.alexsapps.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alexsapps.learncountriesandflagsgame.databinding.FragmentGameOverBinding
import com.alexsapps.learncountriesandflagsgame.presentation.ui.viewmodels.GameOverCapitalsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameOverFragment : Fragment() {
    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameOverCapitalsViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameOverBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.currentGameScore.value =
            GameOverFragmentArgs.fromBundle(requireArguments()).score.toIntOrNull() ?: 0

        binding.submitScoreButton.setOnClickListener {
            viewModel.saveScoreToLocalDataBase()
            val action = GameOverFragmentDirections.actionGameOverFragmentToRanksFragment("${viewModel.currentGameScore.value}")
            findNavController().navigate(action)
        }

        binding.enterNameEditText.setOnEditorActionListener { _, actionId, _ ->
            if ( actionId == EditorInfo.IME_ACTION_SEND ) {
                viewModel.saveScoreToLocalDataBase()
                val action = GameOverFragmentDirections.actionGameOverFragmentToRanksFragment("${viewModel.currentGameScore.value}")
                findNavController().navigate(action)
                true
            } else {
                false
            }
        }


        return view
    }


}