package com.github.jaccek.tridentina.data.rdp.specification.impl.sharedprefs

import android.content.SharedPreferences
import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.PrayerId
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkByPrayerIdSharedPrefsSpecificationTest {

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    lateinit var specification: BookmarkByPrayerIdSharedPrefsSpecification

    @Before
    fun setup() {
        specification = BookmarkByPrayerIdSharedPrefsSpecification()
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun testGetResults_withoutPrayerId() {
        specification.getResults(sharedPreferences)
    }

    @Test
    fun testGetResults_withPrayerId() {
        val prayerId: PrayerId = "test"
        `when`(sharedPreferences.getBoolean(any(), any())).thenReturn(true)

        val single = specification.withPrayerId(prayerId)
                .getResults(sharedPreferences)

        verify(sharedPreferences).getBoolean(prayerId, false)
        verifySingle(single, prayerId)
    }

    private fun verifySingle(single: Single<Collection<Bookmark>>, prayerId: PrayerId) {
        val testObserver = TestObserver<Collection<Bookmark>>()
        single.subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue { verifyCollection(it, prayerId) }
    }

    private fun verifyCollection(collection: Collection<Bookmark>, prayerId: PrayerId): Boolean {
        assertThat(collection).hasSize(1)
        assertThat(collection.first()).isEqualTo(Bookmark(prayerId, true))
        return true
    }
}