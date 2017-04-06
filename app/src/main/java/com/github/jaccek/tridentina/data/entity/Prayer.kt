package com.github.jaccek.tridentina.data.entity

typealias PrayerId = String

data class Prayer(
        val id: PrayerId,
        val name: String,
        val text: String
)