package com.github.jaccek.tridentina.data.rdp.repository.impl.sharedprefs

import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.rdp.repository.base.Repository
import com.github.jaccek.tridentina.data.rdp.specification.base.Specification
import com.github.jaccek.tridentina.data.rdp.specification.impl.sharedprefs.SharedPrefsSpecification
import com.github.jaccek.tridentina.di.DIProvider
import io.reactivex.Single

class BookmarkSharedPrefsRepository : Repository<Bookmark> {

    var sharedPreferences = DIProvider.sharedPreferences

    override fun add(item: Bookmark) = update(item)

    override fun update(item: Bookmark) = sharedPreferences.edit()
            .putBoolean(item.prayerId, item.isBookmark)
            .apply()

    override fun remove(item: Bookmark) = sharedPreferences.edit()
            .remove(item.prayerId)
            .apply()

    override fun query(specification: Specification<Bookmark>): Single<Collection<Bookmark>> {
        val sharedPrefsSpecification = specification as SharedPrefsSpecification<Bookmark>
        return sharedPrefsSpecification.getResults(sharedPreferences)
    }
}