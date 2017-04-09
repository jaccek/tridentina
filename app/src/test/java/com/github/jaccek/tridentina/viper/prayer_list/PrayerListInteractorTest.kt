package com.github.jaccek.tridentina.viper.prayer_list

import com.github.jaccek.tridentina.DIProviderRule
import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.repository.base.Repository
import com.github.jaccek.tridentina.data.rdp.specification.base.bookmark.BookmarksByPrayersIdSpecification
import com.github.jaccek.tridentina.data.rdp.specification.base.prayer.AllPrayersSpecification
import com.github.jaccek.tridentina.testutils.getBookmark
import com.github.jaccek.tridentina.testutils.getPrayer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PrayerListInteractorTest {

    @get:Rule
    val diProviderRule = DIProviderRule()

    @Mock
    lateinit var prayerRepository: Repository<Prayer>
    @Mock
    lateinit var bookmarkRepository: Repository<Bookmark>

    @InjectMocks
    internal lateinit var interactor: PrayerListInteractor

    val prayers = listOf(getPrayer("first"), getPrayer("second"))
    val bookmarks = listOf(getBookmark("first"), getBookmark("second", false))

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetPrayers() {
        val prayersSingle = Single.just<Collection<Prayer>>(prayers)
        val bookmarksSingle = Single.just<Collection<Bookmark>>(bookmarks)
        `when`(prayerRepository.query(any())).thenReturn(prayersSingle)
        `when`(bookmarkRepository.query(any())).thenReturn(bookmarksSingle)

        val returnedSingle = interactor.getPrayers()

        verifySingle(returnedSingle)
        verifyRepositoriesCalls()
    }

    private fun verifySingle(single: Single<Collection<Prayer>>) {
        val testObserver = TestObserver<Collection<Prayer>>()
        single.subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue {
            verifyCollection(it)
        }
    }

    private fun verifyCollection(collection: Collection<Prayer>): Boolean {
        assertThat(collection).hasSize(prayers.size)
        verifyItems(collection)
        return true
    }

    private fun verifyItems(collection: Collection<Prayer>) {
        for ((index, item) in collection.withIndex()) {
            verifySingleItem(item, index)
        }
    }

    private fun verifySingleItem(item: Prayer, index: Int) {
        val prayer = Prayer(prayers[index], bookmarks[index].isBookmark)
        assertThat(item).isEqualToComparingFieldByField(prayer)
    }

    private fun verifyRepositoriesCalls() {
        verify(prayerRepository).query(any<AllPrayersSpecification>())
        verify(bookmarkRepository).query(any<BookmarksByPrayersIdSpecification>())
    }
}
