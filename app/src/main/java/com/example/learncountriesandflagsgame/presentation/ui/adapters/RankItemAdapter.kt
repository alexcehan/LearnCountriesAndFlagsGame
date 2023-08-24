package com.example.learncountriesandflagsgame.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.learncountriesandflagsgame.data.entities.RankItem
import com.example.learncountriesandflagsgame.databinding.RanksItemBinding

class RankItemAdapter : ListAdapter<RankItem, RankItemAdapter.RankItemViewHolder >(RankItemDiffItemCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankItemViewHolder = RankItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: RankItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }


    class RankItemViewHolder(val binding: RanksItemBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): RankItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RanksItemBinding.inflate(layoutInflater,parent, false)

                return RankItemViewHolder(binding)
            }
        }

        fun bind(item: RankItem, position: Int) {
            binding.rankItem = item
            binding.rankPosition.text ="${position+1}."

        }
    }


}