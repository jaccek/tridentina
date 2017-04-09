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
class BookmarksByPrayersIdSharedPrefsSpecificationTest {

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    lateinit var specification: BookmarksByPrayersIdSharedPrefsSpecification

    @Before
    fun setup() {
        specification = BookmarksByPrayersIdSharedPrefsSpecification()
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun testGetResults_withoutPrayersId() {
        specification.getResults(sharedPreferences)
    }

    @Test
    fun testGetResults_withPrayersId() {
        val prayersId: Collection<PrayerId> = listOf("test", "prayer")
        `when`(sharedPreferences.getBoolean(any(), any())).thenReturn(true)

        val single = specification.withPrayersId(prayersId)
                .getResults(sharedPreferences)

        verifySingle(single, prayersId)
        verifySharedPrefsCalls(prayersId)
    }

    private fun verifySharedPrefsCalls(prayersId: Collection<PrayerId>) {
        for (id in prayersId) {
            verify(sharedPreferences).getBoolean(id, false)
        }
    }

    private fun verifySingle(single: Single<Collection<Bookmark>>, prayersId: Collection<PrayerId>) {
        val testObserver = TestObserver<Collection<Bookmark>>()
        single.subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue { verifyCollection(it, prayersId) }
    }

    private fun verifyCollection(collection: Collection<Bookmark>, prayersId: Collection<PrayerId>): Boolean {
        assertThat(collection).hasSize(prayersId.size)
        for ((index, item) in collection.withIndex()) {
            assertThat(item).isEqualTo(Bookmark(prayersId.elementAt(index), true))
        }
        return true
    }
}