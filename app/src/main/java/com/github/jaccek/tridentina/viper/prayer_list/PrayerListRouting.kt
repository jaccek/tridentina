package com.github.jaccek.tridentina.viper.prayer_list

import android.app.Activity
import com.github.jaccek.tridentina.data.entity.Prayer

import com.mateuszkoslacz.moviper.base.routing.BaseRxRouting

internal class PrayerListRouting : BaseRxRouting<Activity>(), PrayerListContract.Routing {

    override fun showPrayer(prayer: Prayer) {
        throw UnsupportedOperationException("not implemented")
    }
}
