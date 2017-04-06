package com.github.jaccek.tridentina.viper.prayer_list

import android.os.Bundle

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState

class PrayerListViewState : RestorableViewState<PrayerListContract.View>, PrayerListContract.View.State {

    private var mState: Int = 0

    override fun saveInstanceState(out: Bundle) {

    }

    override fun restoreInstanceState(`in`: Bundle?): RestorableViewState<PrayerListContract.View>? {
        if (`in` == null) {
            return null
        }

        return this
    }

    override fun apply(view: PrayerListContract.View, retained: Boolean) {
        if (retained) {
            when (mState) {

            }
        }
    }

    override fun setState(state: Int) {
        mState = state
    }
}
