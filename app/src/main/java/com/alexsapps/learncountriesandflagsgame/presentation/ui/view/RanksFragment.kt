package com.alexsapps.learncountriesandflagsgame.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alexsapps.learncountriesandflagsgame.databinding.FragmentRanksBinding
import com.alexsapps.learncountriesandflagsgame.presentation.ui.adapters.RankItemAdapter
import com.alexsapps.learncountriesandflagsgame.presentation.ui.viewmodels.RanksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RanksFragment : Fragment() {
    private var _binding: FragmentRanksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RanksViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRanksBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.currentScore.value = RanksFragmentArgs.fromBundle(requireArguments()).score.toIntOrNull() ?: 0
        val adapter = RankItemAdapter()
        binding.top5ListId.adapter = adapter

        viewModel.currentScore.observe(viewLifecycleOwner) {
            viewModel.updateCurrentRankPosition()
        }

        viewModel.rankList.observe(viewLifecycleOwner) {
            it?.let { adapter.submitList(it) }
        }

        binding.homeButton.setOnClickListener {
            val action = RanksFragmentDirections.actionRanksFragmentToChooseRegionScreenFragment()
            findNavController().navigate(action)
        }

        binding.restartGameButton.setOnClickListener {
            findNavController().navigate(viewModel.restartAction)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}