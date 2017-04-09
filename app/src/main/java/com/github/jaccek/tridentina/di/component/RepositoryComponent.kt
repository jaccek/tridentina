package com.github.jaccek.tridentina.di.component

import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.repository.base.Repository
import com.github.jaccek.tridentina.di.module.BookmarkSharedPrefsRepositoryModule
import com.github.jaccek.tridentina.di.module.PrayerAssetsRepositoryModule
import dagger.Component

@Component(modules = arrayOf(
        PrayerAssetsRepositoryModule::class,
        BookmarkSharedPrefsRepositoryModule::class
))
interface RepositoryComponent {

    fun providePrayerRepository(): Repository<Prayer>

    fun provideBookmarkRepository(): Repository<Bookmark>
}