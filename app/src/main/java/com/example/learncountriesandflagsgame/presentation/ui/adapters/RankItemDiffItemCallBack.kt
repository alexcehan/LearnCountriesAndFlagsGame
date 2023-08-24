package com.example.learncountriesandflagsgame.presentation.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.learncountriesandflagsgame.data.entities.RankItem

class RankItemDiffItemCallBack : DiffUtil.ItemCallback<RankItem>(){
    override fun areItemsTheSame(oldItem: RankItem, newItem: RankItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RankItem, newItem: RankItem): Boolean {
        return (oldItem.id==newItem.id && oldItem.user==newItem.user && oldItem.score==newItem.score)
    }
}