package com.example.sampletv.utils

import android.content.Context
import androidx.leanback.widget.BaseCardView
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sampletv.R
import com.example.sampletv.models.Card
import java.security.AccessController.getContext

/**
 * This Presenter will display a card consisting of an image on the left side of the card followed
 * by text on the right side. The image and text have equal width. The text will work like a info
 * box, thus it will be hidden if the parent row is inactive. This behavior is unique to this card
 * and requires a special focus handler.
 */
class SideInfoCardPresenter(context: Context) : AbstractCardPresenter<BaseCardView>(context) {

    protected override fun onCreateView(): BaseCardView {
        val cardView = BaseCardView(
            context, null,
            R.style.SideInfoCardStyle
        )
        cardView.isFocusable = true
        cardView.addView(LayoutInflater.from(context).inflate(R.layout.side_info_card, null))
        return cardView
    }

    override fun onBindViewHolder(card: Card, cardView: BaseCardView) {
        val imageView = cardView.findViewById<ImageView>(R.id.main_image)
        if (card.getLocalImageResourceName() != null) {
//            val width = context.resources
//                .getDimension(R.dimen.sidetext_image_card_width) as Int
//            val height = context.resources
//                .getDimension(R.dimen.sidetext_image_card_height) as Int
            val resourceId = context.resources
                .getIdentifier(
                    card.getLocalImageResourceName(),
                    "drawable", context.packageName
                )
//            val myOptions = RequestOptions()
//                .override(width, height)
//            Glide.with(context)
//                .asBitmap()
//                .load("https://media.licdn.com/dms/image/C510BAQF1sywM2r1bEQ/company-logo_400_400/0?e=1578528000&v=beta&t=zwT56oGLxmpoeQeM90vZkuXjueF1HJO1nmKhz1tYQUI")
//                .into(imageView)

            Glide.with(context)
                .asBitmap()
                .load(resourceId)
                .into(imageView)
        }
    }

}
