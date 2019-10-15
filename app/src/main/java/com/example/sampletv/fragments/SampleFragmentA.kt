package com.example.sampletv.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Row
import com.example.sampletv.R
import com.example.sampletv.helper.ShadowRowPresenterSelector
import com.example.sampletv.models.CardRow
import com.example.sampletv.utils.CardListRow
import com.example.sampletv.utils.CardPresenterSelector
import com.example.sampletv.utils.Utils
import com.google.gson.Gson

class SampleFragmentA : RowsSupportFragment() {

    private var mRowsAdapter: ArrayObjectAdapter =
        ArrayObjectAdapter(ShadowRowPresenterSelector())

    init {
        adapter = mRowsAdapter
        onItemViewClickedListener =
            OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
                Toast.makeText(activity, "Implement click handler", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createRows()
        mainFragmentAdapter.fragmentHost.notifyDataReady(mainFragmentAdapter)
    }

    private fun createRows() {
        val json = Utils.inputStreamToString(
            resources.openRawResource(
                R.raw.page_row_example
            )
        )
        val rows = Gson().fromJson(json, Array<CardRow>::class.java)
        for (row in rows) {
            if (row.type === CardRow.TYPE_DEFAULT) {
                mRowsAdapter.add(createCardRow(row))
            }
        }
    }

    private fun createCardRow(cardRow: CardRow): Row {
        val presenterSelector = activity?.let { CardPresenterSelector(it) }
        val adapter = ArrayObjectAdapter(presenterSelector)
        for (card in cardRow.cards!!) {
            adapter.add(card)
        }

        val headerItem = HeaderItem(cardRow.title)
        return CardListRow(headerItem, adapter, cardRow)
    }
}