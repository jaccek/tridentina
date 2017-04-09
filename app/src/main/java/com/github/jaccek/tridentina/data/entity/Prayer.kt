package com.github.jaccek.tridentina.data.entity

typealias PrayerId = String

data class Prayer(
        val id: PrayerId,
        val name: String,
        val text: String,
        val isBookmark: Boolean = false
) {
    constructor(prayer: Prayer, isBookmark: Boolean) : this(
            id = prayer.id,
            name = prayer.name,
            text = prayer.text,
            isBookmark = isBookmark
    )
}