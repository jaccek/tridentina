package com.github.jaccek.tridentina.di.module

import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.rdp.repository.base.Repository
import com.github.jaccek.tridentina.data.rdp.repository.impl.sharedprefs.BookmarkSharedPrefsRepository
import dagger.Module
import dagger.Provides

@Module
class BookmarkSharedPrefsRepositoryModule {

    @Provides
    fun provideBookmarkRepository(): Repository<Bookmark> {
        return BookmarkSharedPrefsRepository()
    }
}
