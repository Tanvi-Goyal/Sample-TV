package com.example.sampletv.utils

import androidx.leanback.widget.RowHeaderPresenter
import androidx.leanback.widget.Presenter
import android.content.Context
import android.widget.TextView
import android.graphics.drawable.Drawable
import com.example.sampletv.models.IconHeaderItem
import androidx.leanback.widget.ListRow
import android.content.Context.LAYOUT_INFLATER_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.sampletv.R

class IconHeaderItemPresenter : RowHeaderPresenter() {

    private var mUnselectedAlpha: Float = 0.toFloat()

    override fun onCreateViewHolder(viewGroup: ViewGroup): RowHeaderPresenter.ViewHolder {
        mUnselectedAlpha = viewGroup.resources
            .getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1)
        val inflater = viewGroup.context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.icon_header_item, null)
        return RowHeaderPresenter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, o: Any?) {
        val iconHeaderItem = (o as ListRow).headerItem as IconHeaderItem
        val rootView = viewHolder.view

        val iconView = rootView.findViewById(R.id.header_icon) as ImageView
        val iconResId = iconHeaderItem.getIconResId()
        if (iconResId != IconHeaderItem.ICON.ICON_NONE) { // Show icon only when it is set.
            val icon = rootView.resources.getDrawable(iconResId, null)
            iconView.setImageDrawable(icon)
        }

        val label = rootView.findViewById(R.id.header_label) as TextView
        label.text = iconHeaderItem.name
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        // no op
    }

    override fun onSelectLevelChanged(holder: RowHeaderPresenter.ViewHolder) {
        holder.view.alpha = mUnselectedAlpha + holder.selectLevel * (1.0f - mUnselectedAlpha)
    }

    companion object {
        private val TAG = IconHeaderItemPresenter::class.java.simpleName
    }

}