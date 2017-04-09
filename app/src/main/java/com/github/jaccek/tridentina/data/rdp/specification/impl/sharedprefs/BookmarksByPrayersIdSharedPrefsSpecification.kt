package com.github.jaccek.tridentina.data.rdp.specification.impl.sharedprefs

import android.content.SharedPreferences
import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.PrayerId
import com.github.jaccek.tridentina.data.rdp.specification.base.bookmark.BookmarksByPrayersIdSpecification
import io.reactivex.Observable
import io.reactivex.Single

class BookmarksByPrayersIdSharedPrefsSpecification
    : BookmarksByPrayersIdSpecification, SharedPrefsSpecification<Bookmark> {

    private lateinit var prayersId: Collection<PrayerId>

    override fun withPrayersId(prayersId: Collection<PrayerId>): BookmarksByPrayersIdSharedPrefsSpecification {
        this.prayersId = prayersId
        return this
    }

    override fun getResults(sharedPreferences: SharedPreferences): Single<Collection<Bookmark>> {
        return Observable.fromIterable(prayersId)
                .map { Bookmark(it, sharedPreferences.getBoolean(it, false)) }
                .toList()
                .map { it }
    }
}