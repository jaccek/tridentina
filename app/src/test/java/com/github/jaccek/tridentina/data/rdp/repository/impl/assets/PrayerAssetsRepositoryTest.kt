package com.github.jaccek.tridentina.data.rdp.repository.impl.assets

import android.content.res.AssetManager
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
    lateinit var assetManager: AssetManager
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
        val single = Single.just<Collection<Prayer>>(ArrayList<Prayer>())
        `when`(specification.getResults(any())).thenReturn(single)

        val returnedSingle = repository.query(specification)

        verify(specification).getResults(assetManager)
        assertThat(returnedSingle).isEqualTo(single)
    }
}