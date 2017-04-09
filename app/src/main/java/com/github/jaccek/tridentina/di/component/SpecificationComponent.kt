package com.github.jaccek.tridentina.di.component

import com.github.jaccek.tridentina.data.rdp.specification.base.bookmark.BookmarksByPrayersIdSpecification
import com.github.jaccek.tridentina.data.rdp.specification.base.prayer.AllPrayersSpecification
import com.github.jaccek.tridentina.di.module.BookmarksSharedPrefsSpecificationModule
import com.github.jaccek.tridentina.di.module.PrayerAssetsSpecificationModule
import dagger.Component

@Component(modules = arrayOf(
        PrayerAssetsSpecificationModule::class,
        BookmarksSharedPrefsSpecificationModule::class
))
interface SpecificationComponent {

    fun provideAllPrayersSpecification() : AllPrayersSpecification

    fun provideBookmarksByPrayersIdsSpecification(): BookmarksByPrayersIdSpecification
}