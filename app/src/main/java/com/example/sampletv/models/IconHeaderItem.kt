package com.example.sampletv.models

import androidx.leanback.widget.HeaderItem
import com.example.sampletv.models.IconHeaderItem.ICON.ICON_NONE

public class IconHeaderItem(name: String?) : HeaderItem(name) {
    object ICON{
        public const val ICON_NONE = -1
    }

    private val TAG = IconHeaderItem::class.java.simpleName

    /** Hold an icon resource id  */
    private var mIconResId = ICON_NONE

    fun getIconResId(): Int {
        return mIconResId
    }

    fun setIconResId(iconResId: Int) {
        this.mIconResId = iconResId
    }
}