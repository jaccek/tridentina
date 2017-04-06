package com.github.jaccek.tridentina.data.rdp.repository.impl.assets

import com.github.jaccek.tridentina.DIProviderRule
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.specification.impl.assets.AssetsSpecification
import com.github.jaccek.tridentina.testutils.getPrayer
import com.nhaarman.mockito_kotlin.any
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PrayerAssetsRepositoryTest {

    @get:Rule
    val diProviderRule = DIProviderRule()

    @Mock
    lateinit var specification: AssetsSpecification<Prayer>

    lateinit var repository: PrayerAssetsRepository

    @Before
    fun setup() {
        repository = PrayerAssetsRepository()
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
        val single = Single.just<Collection<Prayer>>(ArrayList<Prayer>())
        `when`(specification.getResults(any())).thenReturn(single)

        val returnedSingle = repository.query(specification)

        assertThat(returnedSingle).isEqualTo(single)
    }
}