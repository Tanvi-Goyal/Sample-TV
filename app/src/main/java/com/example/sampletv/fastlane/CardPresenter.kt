package com.example.sampletv.fastlane

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.example.sampletv.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import android.graphics.Color.LTGRAY
import android.widget.TextView
import com.example.sampletv.data.Video



public class CardPresenter(var mContext: Context) : Presenter() {

    private val CARD_WIDTH = 200
    private val CARD_HEIGHT = 200


    inner class ViewHolder(view: View) : Presenter.ViewHolder(view) {
        var mCardView: ImageCardView = view as ImageCardView
        var mDefaultCardImage: Drawable = mContext.resources.getDrawable(R.drawable.filmi)
        var mImageCardViewTarget: PicassoImageCardViewTarget = PicassoImageCardViewTarget(mCardView)

//        init {
//
//            mCardView = view as ImageCardView
//            mImageCardViewTarget = PicassoImageCardViewTarget(mCardView)
//            mDefaultCardImage = mContext.resources.getDrawable(R.drawable.filmi)
//        }

        fun getCardView(): ImageCardView {
            return mCardView
        }

        fun updateCardViewImage(url: String) {

            Picasso.with(mContext)
                .load(url)
                .resize(CARD_WIDTH, CARD_HEIGHT)
                .centerCrop()
                .error(mDefaultCardImage)
                .into(mCardView.mainImageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder? {
        mContext = parent!!.context
        val cardView = ImageCardView(mContext)
        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        (cardView.findViewById(R.id.content_text) as TextView).setTextColor(LTGRAY)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {
        val video = item as Video
        (viewHolder as ViewHolder).mCardView.titleText = video.title
        viewHolder.mCardView.contentText = video.description
        viewHolder.mCardView.setMainImageDimensions(CARD_WIDTH * 2, CARD_HEIGHT * 2)
        viewHolder.updateCardViewImage(video.thumbUrl!!)

    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder?) {
    }

    inner class PicassoImageCardViewTarget(private val mImageCardView: ImageCardView) : Target {

        override fun onBitmapLoaded(bitmap: Bitmap, loadedFrom: Picasso.LoadedFrom) {
            val bitmapDrawable = BitmapDrawable(mContext.resources, bitmap)
            mImageCardView.mainImageView.setImageDrawable(bitmapDrawable)
        }

        override fun onBitmapFailed(drawable: Drawable) {
            mImageCardView.mainImageView.setImageDrawable(drawable)
        }

        override fun onPrepareLoad(drawable: Drawable) {
        }
    }

}