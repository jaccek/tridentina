package com.github.jaccek.tridentina.viper.prayer_list

import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.entity.PrayerId
import com.github.jaccek.tridentina.data.rdp.repository.base.Repository
import com.github.jaccek.tridentina.di.DIProvider
import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor
import io.reactivex.Observable
import io.reactivex.Single

internal class PrayerListInteractor : BaseRxInteractor(), PrayerListContract.Interactor {

    var prayerRepository: Repository<Prayer> = DIProvider.repositoryComponent.providePrayerRepository()
    var bookmarkRepository: Repository<Bookmark> = DIProvider.repositoryComponent.provideBookmarkRepository()

    override fun getPrayers(): Single<Collection<Prayer>> {
        return prayerRepository.query(DIProvider.specificationComponent.provideAllPrayersSpecification())
                .flatMap { getBookmarkedPrayers(it) }
    }

    private fun getBookmarkedPrayers(prayers: Collection<Prayer>): Single<Collection<Prayer>> {
        return Observable.fromIterable(prayers)
                .map { it.id }
                .toList()
                .flatMap { getAllBookmarks(it) }
                .flatMapObservable { Observable.fromIterable(it) }
                .zipWith<Prayer, Prayer>(prayers, { (_, isBookmark), prayer: Prayer -> Prayer(prayer, isBookmark) })
                .toList()
                .map { it }
    }

    private fun getAllBookmarks(prayerIds: Collection<PrayerId>) =
            bookmarkRepository.query(getBookmarksByPrayersIdSpecification(prayerIds))

    private fun getBookmarksByPrayersIdSpecification(prayerIds: Collection<PrayerId>) =
            DIProvider.specificationComponent
                    .provideBookmarksByPrayersIdsSpecification()
                    .withPrayersId(prayerIds)

    override fun addToBookmarks(prayer: Prayer) {
        bookmarkRepository.add(Bookmark(prayerId = prayer.id, isBookmark = true))
    }

    override fun removeFromBookmarks(prayer: Prayer) {
        bookmarkRepository.remove(Bookmark(prayerId = prayer.id, isBookmark = true))
    }
}
