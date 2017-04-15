package com.github.jaccek.tridentina.viper.prayer_list

import android.app.Activity
import com.github.jaccek.tridentina.data.entity.Prayer

import com.hannesdorfmann.mosby.mvp.MvpView
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting
import io.reactivex.Single

interface PrayerListContract {

    interface View : MvpView {

        fun showPrayers(prayers: Collection<Prayer>)

        interface State {

            fun setState(state: Int)
        }

        fun  showError(error: Throwable)
    }

    interface Interactor : ViperRxInteractor {

        fun getPrayers(): Single<Collection<Prayer>>

        fun addToBookmarks(prayer: Prayer)

        fun removeFromBookmarks(prayer: Prayer)
    }

    interface Routing : ViperRxRouting<Activity> {

        fun showPrayer(prayer: Prayer)
    }
}
