package com.github.jaccek.tridentina.viper.prayer_list

import com.github.jaccek.tridentina.DIProviderRule
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.testutils.getPrayer
import com.mateuszkoslacz.moviper.tests.rules.RxAndroidSchedulersOverrideRule
import io.reactivex.subjects.SingleSubject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PrayerListPresenterTest {

    @get:Rule
    val diProviderRule = DIProviderRule()
    @get:Rule
    val rxAndroidRule = RxAndroidSchedulersOverrideRule()

    @Mock
    lateinit var interactor: PrayerListContract.Interactor
    @Mock
    lateinit var view: PrayerListActivity

    @InjectMocks
    lateinit var presenter: PrayerListPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testCreateRouting() {
        val routing = presenter.createRouting()

        assertThat(routing).isInstanceOf(PrayerListRouting::class.java)
    }

    @Test
    fun testCreateInteractor() {
        val interactor = presenter.createInteractor()

        assertThat(interactor).isInstanceOf(PrayerListInteractor::class.java)
    }

    @Test
    fun testOnPrayersReceived() {
        val subject = SingleSubject.create<Collection<Prayer>>()
        `when`(interactor.getPrayers()).thenReturn(subject)
        val prayers = listOf(getPrayer(id = "1"), getPrayer(id = "2"))

        presenter.attachView(view)
        subject.onSuccess(prayers)

        verify(view).showPrayers(prayers)
    }

    @Test
    fun testOnPrayersReceived_withError() {
        val subject = SingleSubject.create<Collection<Prayer>>()
        val throwable = Throwable()
        `when`(interactor.getPrayers()).thenReturn(subject)

        presenter.attachView(view)
        subject.onError(throwable)

        verify(view).showError(throwable)
        // TODO: verify sending crash to Crashlytics
    }
}