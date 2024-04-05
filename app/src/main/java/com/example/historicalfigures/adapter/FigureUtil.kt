package com.example.historicalfigures.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.historicalfigures.model.FigureApiResponse

class FigureUtil : DiffUtil.ItemCallback<FigureApiResponse>() {
    override fun areItemsTheSame(oldItem: FigureApiResponse, newItem: FigureApiResponse): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: FigureApiResponse, newItem: FigureApiResponse): Boolean {
        return oldItem == newItem
    }

}