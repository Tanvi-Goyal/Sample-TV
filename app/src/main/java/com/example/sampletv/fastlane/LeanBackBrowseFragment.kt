package com.example.sampletv.fastlane

import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.core.content.ContextCompat
import android.os.Bundle
import android.view.View
import androidx.leanback.widget.ListRowPresenter
import com.example.sampletv.R
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.SinglePresenterSelector
import androidx.leanback.widget.CursorObjectAdapter
import com.example.sampletv.data.VideoDataManager
import com.example.sampletv.data.VideoItemContract

public class LeanBackBrowseFragment : BrowseSupportFragment() {

    private var mRowsAdapter: ArrayObjectAdapter? = null
    private val HEADERS = arrayOf("Featured", "Popular", "Editor's choice")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = mRowsAdapter

        brandColor = ContextCompat.getColor(context!!, R.color.primaryColor)
        badgeDrawable = ContextCompat.getDrawable(context!!, R.drawable.filmi)

        for (position in HEADERS.indices) {
            val rowContents = CursorObjectAdapter(SinglePresenterSelector(activity?.baseContext?.let {
                CardPresenter(
                    it
                )
            }))
            val manager = activity?.let {
                VideoDataManager(
                    it,
                    loaderManager,
                    VideoItemContract.VideoItem.buildDirUri(),
                    rowContents
                )
            }
            manager?.startDataLoading()

            val headerItem = HeaderItem(position.toLong(), HEADERS[position])
            mRowsAdapter!!.add(ListRow(headerItem, manager?.itemList))
        }
    }

}