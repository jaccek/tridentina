package com.github.jaccek.tridentina.viper.prayer_list


import com.github.jaccek.tridentina.R
import com.github.jaccek.tridentina.data.entity.Prayer
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState
import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.ViperViewStateAiPassiveActivity
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter

class PrayerListActivity : ViperViewStateAiPassiveActivity<
        PrayerListContract.View,
        PrayerListViewState>(),
        PrayerListContract.View {

    override fun createPresenter(): ViperPresenter<PrayerListContract.View> {
        return PrayerListPresenter()
    }

    override fun createViewState(): ViewState<PrayerListContract.View> {
        return PrayerListViewState()
    }

    override fun onNewViewStateInstance() {

    }

    override fun injectViews() {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_prayer_list
    }

    override fun showPrayers(prayers: Collection<Prayer>) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun showError(error: Throwable) {
        throw UnsupportedOperationException("not implemented")
    }
}
