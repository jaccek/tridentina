package com.github.jaccek.tridentina.data.rdp.specification.impl.sharedprefs

import android.content.SharedPreferences
import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.PrayerId
import com.github.jaccek.tridentina.data.rdp.specification.base.bookmark.BookmarkByPrayerIdSpecification
import io.reactivex.Single
import java.util.*

class BookmarkByPrayerIdSharedPrefsSpecification
    : BookmarkByPrayerIdSpecification, SharedPrefsSpecification<Bookmark> {

    private lateinit var prayerId: PrayerId

    override fun withPrayerId(prayerId: PrayerId): BookmarkByPrayerIdSharedPrefsSpecification {
        this.prayerId = prayerId
        return this
    }

    override fun getResults(sharedPreferences: SharedPreferences): Single<Collection<Bookmark>> {
        return Single.just(sharedPreferences.getBoolean(prayerId, false))
                .map { Bookmark(prayerId, it) }
                .map { Collections.singletonList(it) }
    }
}