package com.github.jaccek.tridentina.viper.prayer_list

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter

class PrayerListPresenter : BaseRxPresenter<
        PrayerListContract.View,
        PrayerListContract.Interactor,
        PrayerListContract.Routing>(),
        ViperPresenter<PrayerListContract.View> {

    override fun createRouting(): PrayerListContract.Routing {
        return PrayerListRouting()
    }

    override fun createInteractor(): PrayerListContract.Interactor {
        return PrayerListInteractor()
    }
}
