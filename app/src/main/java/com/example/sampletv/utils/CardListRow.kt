package com.example.sampletv.utils

import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ObjectAdapter
import com.example.sampletv.models.CardRow

/**
 * The [CardListRow] allows the [ShadowRowPresenterSelector] to access the [CardRow]
 * held by the row and determine whether to use a [androidx.leanback.widget.Presenter]
 * with or without a shadow.
 */
class CardListRow(header: HeaderItem, adapter: ObjectAdapter, cardRow: CardRow) :
    ListRow(header, adapter) {

    var cardRow: CardRow? = null

    init {
        this.cardRow = cardRow
    }
}
