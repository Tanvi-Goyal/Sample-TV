package com.example.sampletv.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletv.R

class MainAdapter(private val items: ArrayList<String>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_RECYCLER = 0
        private const val TYPE_CHART = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_RECYCLER -> {
                RecyclerViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_sub_recycler,
                        parent,
                        false
                    )
                )
            }
            TYPE_CHART -> {
                ChartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chart, parent, false))
            }
            else -> throw IllegalArgumentException("Invalid view type")

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            "A" -> TYPE_RECYCLER
            "B" -> TYPE_CHART
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var subRecyclerView : RecyclerView = view.findViewById(R.id.recyclerView)

    init {
        subRecyclerView.adapter = ChildAdapter(view.context)
    }

}

class ChartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
}