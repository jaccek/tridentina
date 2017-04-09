package com.github.jaccek.tridentina.di.module

import com.github.jaccek.tridentina.data.rdp.specification.base.bookmark.BookmarksByPrayersIdSpecification
import com.github.jaccek.tridentina.data.rdp.specification.impl.sharedprefs.BookmarksByPrayersIdSharedPrefsSpecification
import dagger.Module
import dagger.Provides

@Module
class BookmarksSharedPrefsSpecificationModule {

    @Provides
    fun provideBookmarsByPrayersIdSpecification(): BookmarksByPrayersIdSpecification {
        return BookmarksByPrayersIdSharedPrefsSpecification()
    }
}
