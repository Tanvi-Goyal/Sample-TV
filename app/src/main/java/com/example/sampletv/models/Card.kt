package com.example.sampletv.models

import android.content.Context
import android.graphics.Color
import android.util.Log

import com.google.gson.annotations.SerializedName

import java.net.URI
import java.net.URISyntaxException

/**
 * This is a generic example of a custom data object, containing info we might want to keep with
 * each card on the home screen
 */
class Card {

    @SerializedName("title")
    var title = ""
    @SerializedName("description")
    var description = ""
    @SerializedName("extraText")
    var extraText = ""
    @SerializedName("card")
    var imageUrl: String? = null
    @SerializedName("footerColor")
    private var mFooterColor: String? = null
    @SerializedName("selectedColor")
    private var mSelectedColor: String? = null
    @SerializedName("localImageResource")
    var localImageResource: String? = null
    @SerializedName("footerIconLocalImageResource")
    var footerResource: String? = null
    @SerializedName("type")
    var type: Card.Type? = null
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("width")
    var width: Int = 0
    @SerializedName("height")
    var height: Int = 0

    val footerColor: Int
        get() = if (mFooterColor == null) -1 else Color.parseColor(mFooterColor)

    val selectedColor: Int
        get() = if (mSelectedColor == null) -1 else Color.parseColor(mSelectedColor)

    val imageURI: URI?
        get() {
            if (imageUrl == null) return null
            try {
                return URI(imageUrl!!)
            } catch (e: URISyntaxException) {
                Log.d("URI exception: ", imageUrl!!)
                return null
            }

        }

    fun setFooterColor(footerColor: String) {
        mFooterColor = footerColor
    }

    fun setSelectedColor(selectedColor: String) {
        mSelectedColor = selectedColor
    }

    fun getLocalImageResourceId(context: Context): Int {
        return context.resources.getIdentifier(
            getLocalImageResourceName(), "drawable",
            context.packageName
        )
    }

    fun getLocalImageResourceName(): String? {
        return localImageResource
    }

    fun getFooterLocalImageResourceName(): String? {
        return footerResource
    }

    enum class Type {

        MOVIE_COMPLETE,
        MOVIE,
        MOVIE_BASE,
        ICON,
        SQUARE_BIG,
        SINGLE_LINE,
        GAME,
        SQUARE_SMALL,
        DEFAULT,
        SIDE_INFO,
        SIDE_INFO_TEST_1,
        TEXT,
        CHARACTER,
        GRID_SQUARE,
        VIDEO_GRID

    }

}
