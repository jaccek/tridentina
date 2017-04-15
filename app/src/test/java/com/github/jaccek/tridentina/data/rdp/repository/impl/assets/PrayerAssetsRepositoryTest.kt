package com.github.jaccek.tridentina.data.rdp.repository.impl.assets

import com.github.jaccek.tridentina.DIProviderRule
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.specification.impl.assets.AssetsSpecification
import com.github.jaccek.tridentina.testutils.getPrayer
import com.nhaarman.mockito_kotlin.any
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.SingleSubject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PrayerAssetsRepositoryTest {

    @get:Rule
    val diProviderRule = DIProviderRule()

    @Mock
    lateinit var specification: AssetsSpecification<Prayer>
    @Mock
    lateinit var assetManagerWrapper: AssetManagerWrapper
    @InjectMocks
    lateinit var repository: PrayerAssetsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testAdd() {
        val prayer = getPrayer()

        repository.add(prayer)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testUpdate() {
        val prayer = getPrayer()

        repository.update(prayer)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testRemove() {
        val prayer = getPrayer()

        repository.remove(prayer)
    }

    @Test
    fun testQuery() {
        val subject = SingleSubject.create<Collection<Prayer>>()
        val collection = listOf(getPrayer())
        `when`(specification.getResults(any())).thenReturn(subject)

        val single = repository.query(specification)
        subject.onSuccess(collection)

        verify(specification).getResults(assetManagerWrapper)
        verifySingle(single, collection)
    }

    private fun verifySingle(single: Single<Collection<Prayer>>, collection: List<Prayer>) {
        val testObserver = TestObserver<Collection<Prayer>>()
        single.subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue { it == collection }
    }
}