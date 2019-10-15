package com.example.sampletv.data

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.leanback.database.CursorMapper
import androidx.leanback.widget.CursorObjectAdapter
import androidx.leanback.widget.ObjectAdapter
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class VideoDataManager(
    protected var mContext: Context,
    protected var mLoaderManager: LoaderManager,
    protected var mRowUri: Uri,
    val itemList: ObjectAdapter
) :
    LoaderManager.LoaderCallbacks<Cursor> {
    protected var mDataLoader: Loader<Cursor>? = null
    protected var LOADER_ID: Int = 0

    private val mMapper: VideoItemMapper

    init {
        LOADER_ID = java.lang.Double.valueOf(Math.random() * Integer.MAX_VALUE).toInt()
        mMapper = VideoItemMapper()
        (itemList as CursorObjectAdapter).setMapper(mMapper)

    }

    fun startDataLoading() {
        if (mDataLoader == null) {
            mDataLoader = mLoaderManager.initLoader(LOADER_ID, null, this)
        } else {
            mLoaderManager.restartLoader(mDataLoader!!.id, null, this)

        }
    }

    override fun onCreateLoader(i: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            mContext,
            mRowUri,
            PROJECTION,
            null,
            null,
            VideoItemContract.VideoItem.DEFAULT_SORT
        )
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor) {
        if (itemList is CursorObjectAdapter) {
            (itemList as CursorObjectAdapter).swapCursor(cursor)
        }
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>) {
        if (itemList is CursorObjectAdapter) {
            (itemList as CursorObjectAdapter).swapCursor(null)
        }
    }

    class VideoItemMapper : CursorMapper() {

        private var mColumnMap: IntArray? = null

        override fun bindColumns(cursor: Cursor) {
            mColumnMap = IntArray(9)
            mColumnMap!![ID] = cursor.getColumnIndex(PROJECTION[0])
            mColumnMap!![TITLE] = cursor.getColumnIndex(PROJECTION[1])
            mColumnMap!![CATEGORY] = cursor.getColumnIndex(PROJECTION[2])
            mColumnMap!![DESCRIPTION] = cursor.getColumnIndex(PROJECTION[3])
            mColumnMap!![RATING] = cursor.getColumnIndex(PROJECTION[4])
            mColumnMap!![YEAR] = cursor.getColumnIndex(PROJECTION[5])
            mColumnMap!![THUMB_IMG_URL] = cursor.getColumnIndex(PROJECTION[THUMB_IMG_URL])
            mColumnMap!![TAGS] = cursor.getColumnIndex(PROJECTION[7])
            mColumnMap!![CONTENT_URL] = cursor.getColumnIndex(PROJECTION[8])
        }

        override fun bind(cursor: Cursor): Video {
            val item = Video()
            item.id = cursor.getLong(mColumnMap?.get(ID)!!)
            item.rating = cursor.getInt(mColumnMap!![RATING])
            item.year = cursor.getInt(mColumnMap!![YEAR])
            item.tags = cursor.getString(mColumnMap!![TAGS])
            item.title = cursor.getString(mColumnMap!![TITLE])
            item.description = cursor.getString(mColumnMap!![DESCRIPTION])
            item.thumbUrl = cursor.getString(cursor.getColumnIndex("thumb_img_url"))
            item.category = cursor.getString(mColumnMap!![CATEGORY])
            item.contentUrl = cursor.getString(mColumnMap!![CONTENT_URL])
            return item
        }

        companion object {
            private val ID = 0
            private val TITLE = 1
            private val CATEGORY = 2
            private val DESCRIPTION = 3
            private val RATING = 4
            private val YEAR = 5
            private val THUMB_IMG_URL = 6
            private val TAGS = 6
            private val CONTENT_URL = 6
        }

    }

    companion object {

        internal var PROJECTION = arrayOf<String>(
            VideoItemContract.VideoItemColumns._ID,
            VideoItemContract.VideoItemColumns.TITLE,
            VideoItemContract.VideoItemColumns.CATEGORY,
            VideoItemContract.VideoItemColumns.DESCRIPTION,
            VideoItemContract.VideoItemColumns.RATING,
            VideoItemContract.VideoItemColumns.YEAR,
            VideoItemContract.VideoItemColumns.THUMB_IMG_URL,
            VideoItemContract.VideoItemColumns.TAGS,
            VideoItemContract.VideoItemColumns.CONTENT_URL
        )
    }
}
