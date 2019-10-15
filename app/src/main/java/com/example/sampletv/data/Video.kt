package com.example.sampletv.data

import java.io.Serializable

public class Video : Serializable {
    var id: Long = 0
    var year: Int = 0
    var rating: Int = 0
    var description: String? = null
    var title: String? = null
    var thumbUrl: String? = null
    var contentUrl: String? = null
    var category: String? = null
    var tags: String? = null

    companion object {
        val INTENT_EXTRA_VIDEO = "INTENT_EXTRA_VIDEO"
    }
}
