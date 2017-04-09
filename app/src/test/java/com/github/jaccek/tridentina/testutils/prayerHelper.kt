package com.github.jaccek.tridentina.testutils

import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.entity.PrayerId

fun getPrayer(id: PrayerId = "id") = Prayer(
        id = id,
        name = "name",
        text = "text"
)

fun getBookmark(id: PrayerId = "id", isBookmark: Boolean = true) = Bookmark(
        prayerId = id,
        isBookmark = isBookmark
)
