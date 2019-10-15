package com.example.sampletv.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletv.R

class ChildAdapter(val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    private var previousFocusedViewHolder: ViewHolder? = null
    private var previouslyFocusedPos = 0
    private var currentlyFocusedPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_performance_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.layoutCard.setOnFocusChangeListener { _, hasFocus ->
            Log.d("ItemListAdapter", "onBindViewHolder OnFocusChangeListener position: $position")
            updateFocusPositions(holder, hasFocus, position)
            startFocusAnimation(holder, hasFocus, position)
        }

    }

    private fun startFocusAnimation(holder: ViewHolder, hasFocus: Boolean, position: Int) {
        Log.d(
            "ItemListAdapter",
            "startFocusAnimation hasFocus: $hasFocus, currentlyFocusedPos: $currentlyFocusedPos, previouslyFocusedPos: $previouslyFocusedPos"
        )

        if (hasFocus) {
            previousFocusedViewHolder?.let {
                //                val moveOutAnimSet = if (currentlyFocusedPos > previouslyFocusedPos)
//                val moveOutAnim = AnimationUtils.loadAnimation(it.layoutCard.context, moveOutAnimSet)
//                moveOutAnim.fillAfter = true
//                moveOutAnim.duration = 250
//                it.layoutCard.startAnimation(moveOutAnim)
                Log.d("focused", "" + position)

                val anim = AnimationUtils.loadAnimation(context, R.anim.scale_out_tv)
                Toast.makeText(it.itemView.context, position.toString(), Toast.LENGTH_SHORT).show()
                it.layoutCard.startAnimation(anim)
                anim.fillAfter = true
//                previousFocusedViewHolder.layoutCard.startAnimation()
            }

//            val moveInAnimSet = if (currentlyFocusedPos > previouslyFocusedPos) R.anim.zoom_in else R.anim.zoom_out
//            val moveInAnim = AnimationUtils.loadAnimation(holder.layoutCard.context, moveInAnimSet)
//            moveInAnim.fillAfter = true
//            moveInAnim.duration = 400
//            holder.layoutCard.startAnimation(moveInAnim)
            Log.d("notfocused", "" + position)

            val anim = AnimationUtils.loadAnimation(context, R.anim.scale_in_tv)
            holder.layoutCard.startAnimation(anim)
            Toast.makeText(holder.itemView.context, position.toString(), Toast.LENGTH_SHORT).show()
            anim.fillAfter = true
        }
    }

    private fun updateFocusPositions(holder: ViewHolder, hasFocus: Boolean, position: Int) {
        if (hasFocus) {
            previouslyFocusedPos = currentlyFocusedPos
            currentlyFocusedPos = position
        } else {
            previousFocusedViewHolder = holder
        }
    }


    override fun getItemCount(): Int {
        return 4
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var layoutCard: ConstraintLayout = view.findViewById(R.id.layout_card)

}