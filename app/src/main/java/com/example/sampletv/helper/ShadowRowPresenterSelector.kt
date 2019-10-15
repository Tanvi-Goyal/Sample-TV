package com.example.sampletv.helper

import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector
import com.example.sampletv.utils.CardListRow

/**
 * This [PresenterSelector] will return a [ListRowPresenter] which has shadow support
 * enabled or not depending on [CardRow.useShadow] for a given row.
 */
class ShadowRowPresenterSelector : PresenterSelector() {

    private val mShadowEnabledRowPresenter = ListRowPresenter()
    private val mShadowDisabledRowPresenter = ListRowPresenter()

    init {
        mShadowEnabledRowPresenter.setNumRows(1)
        mShadowDisabledRowPresenter.shadowEnabled = false
    }

    override fun getPresenter(item: Any): Presenter {
        if (item !is CardListRow) return mShadowDisabledRowPresenter
        val listRow = item as CardListRow
        val row = listRow.cardRow
        return if (row?.useShadow()!!) mShadowEnabledRowPresenter else mShadowDisabledRowPresenter
    }

    override fun getPresenters(): Array<Presenter> {
        return arrayOf(mShadowDisabledRowPresenter, mShadowEnabledRowPresenter)
    }
}