package com.example.sampletv.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*

class ListFragment : BrowseSupportFragment() {

    companion object {
        const val HEADER_ID_1: Long = 1
        const val HEADER_NAME_1 = "Fragment 1"
        const val HEADER_ID_2: Long = 2
        const val HEADER_NAME_2 = "Fragment 2"
    }

    private var mBackgroundManager: BackgroundManager? = null
    private var mRowsAdapter: ArrayObjectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
        loadData()
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager?.run { attach(activity?.window) }
        mainFragmentRegistry.registerFragment(
            PageRow::class.java,
            PageRowFragmentFactory(mBackgroundManager)
        )
    }

    private fun setupUi() {
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = resources.getColor(com.example.sampletv.R.color.primaryColor)
        setOnSearchClickedListener {
            Toast.makeText(
                activity, "Search Here", Toast.LENGTH_SHORT
            )
                .show()
        }

//        setHeaderPresenterSelector(object : PresenterSelector() {
//            override fun getPresenter(o: Any): Presenter {
//                return IconHeaderItemPresenter()
//            }
//        })
//        prepareEntranceTransition()
    }

    private fun loadData() {
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = mRowsAdapter
        createRows()
    }

    private fun createRows() {
        val headerItem1 = HeaderItem(HEADER_ID_1, HEADER_NAME_1)
        val pageRow1 = PageRow(headerItem1)
        mRowsAdapter?.add(pageRow1)

        val headerItem2 = HeaderItem(HEADER_ID_2, HEADER_NAME_2)
        val pageRow2 = PageRow(headerItem2)
        mRowsAdapter?.add(pageRow2)
    }

    private class PageRowFragmentFactory internal constructor(var mBackgroundManager: BackgroundManager?) :
        BrowseSupportFragment.FragmentFactory<Fragment>() {
        override fun createFragment(rowObj: Any?): Fragment? {
            val row = rowObj as Row
            mBackgroundManager?.drawable = null

            if (row.headerItem.id == HEADER_ID_1) {
                return SampleFragmentA()
            } else if (row.headerItem.id == HEADER_ID_2) {
                return SampleFragmentB()
            }

            throw IllegalArgumentException(String.format("Invalid row %s", rowObj))
        }
    }
}