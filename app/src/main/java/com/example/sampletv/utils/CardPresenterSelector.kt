package com.example.sampletv.utils

import android.content.Context
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector
import com.example.sampletv.models.Card

import java.util.HashMap

/**
 * This PresenterSelector will decide what Presenter to use depending on a given card's type.
 */
class CardPresenterSelector(private val mContext: Context) : PresenterSelector() {

    override fun getPresenter(item: Any): Presenter? {
        if (item !is Card)
            throw RuntimeException(
                String.format(
                    "The PresenterSelector only supports data items of type '%s'",
                    Card::class.java!!.getName()
                )
            )
        var presenter = SideInfoCardPresenter(mContext)
        return presenter
    }

}
