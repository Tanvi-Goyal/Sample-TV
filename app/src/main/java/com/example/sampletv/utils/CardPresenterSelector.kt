package com.example.sampletv.utils

import android.content.Context
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector
import com.example.sampletv.models.Card
import java.util.*

/**
 * This PresenterSelector will decide what Presenter to use depending on a given card's type.
 */
class CardPresenterSelector(private val mContext: Context) : PresenterSelector() {

    private val presenters = HashMap<Card.Type, Presenter>()

    override fun getPresenter(item: Any): Presenter? {
        if (item !is Card)
            throw RuntimeException(
                String.format(
                    "The PresenterSelector only supports data items of type '%s'",
                    Card::class.java.name
                )
            )
        var presenter = presenters[item.type]
        if (presenter == null) {
            when (item.type) {
                Card.Type.PERFORMANCE -> presenter = PerformanceCardPresenter(mContext)
                Card.Type.CHART -> presenter = ChartPresenter(mContext)
            }
        }
        presenter?.let { item.type?.let { it1 -> presenters.put(it1, it) } }
        return presenter
    }
}
