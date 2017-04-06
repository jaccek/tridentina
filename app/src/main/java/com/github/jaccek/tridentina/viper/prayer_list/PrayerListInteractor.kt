package com.github.jaccek.tridentina.viper.prayer_list

import com.github.jaccek.tridentina.data.entity.Prayer
import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor
import io.reactivex.Single

internal class PrayerListInteractor : BaseRxInteractor(), PrayerListContract.Interactor {

    override fun getPrayers(): Single<Collection<Prayer>> {
        throw UnsupportedOperationException("not implemented")
    }

    override fun addToBookmarks(prayer: Prayer) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun removeFromBookmarks(prayer: Prayer) {
        throw UnsupportedOperationException("not implemented")
    }
}
