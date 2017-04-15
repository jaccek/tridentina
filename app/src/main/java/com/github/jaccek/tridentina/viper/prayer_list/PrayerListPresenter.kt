package com.github.jaccek.tridentina.viper.prayer_list

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class PrayerListPresenter : BaseRxPresenter<
        PrayerListContract.View,
        PrayerListContract.Interactor,
        PrayerListContract.Routing>(),
        ViperPresenter<PrayerListContract.View> {

    override fun createRouting(): PrayerListContract.Routing =
            PrayerListRouting()

    override fun createInteractor(): PrayerListContract.Interactor =
            PrayerListInteractor()

    override fun attachView(view: PrayerListContract.View?) {
        super.attachView(view)

        addSubscription(subscribeProvidingPrayers(view))
    }

    private fun subscribeProvidingPrayers(view: PrayerListContract.View?): Disposable? =
            interactor.getPrayers()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { view?.showPrayers(it) },
                            { onError(it) })

    private fun onError(it: Throwable) {
        view?.showError(it)
        // TODO: log to Crashlytics
    }
}
