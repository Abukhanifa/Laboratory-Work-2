package com.example.historicalfigures.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.historicalfigures.databinding.HistoryFigureBinding
import com.example.historicalfigures.model.FigureApiResponse

class FigureAdapter(
    private val onMovieRemoved: (FigureApiResponse) -> Unit
) : ListAdapter<FigureApiResponse, FigureAdapter.ViewHolder>(FigureUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HistoryFigureBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: HistoryFigureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(figure: FigureApiResponse) {
            with(binding) {
                figureName.text= figure.name
                figureTitle.text = figure.title
                figureMother.text = Html.fromHtml("<b>Mother:</b> " + (figure.info.mother ?: "Unknown"))
                figureFather.text = Html.fromHtml("<b>Father:</b> " + (figure.info.father ?: "Unknown"))
                figureBorn.text = Html.fromHtml("<b>Born:</b> " + (figure.info.born ?: "Unknown"))
                figureDied.text = Html.fromHtml("<b>Died:</b> " + (figure.info.died ?: "Unknown"))
            }
        }
    }
}