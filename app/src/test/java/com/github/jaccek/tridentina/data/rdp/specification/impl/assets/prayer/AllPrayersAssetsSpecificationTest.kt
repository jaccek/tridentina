package com.github.jaccek.tridentina.data.rdp.specification.impl.assets.prayer

import android.content.res.AssetManager
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.repository.impl.assets.AssetsRepositoryException
import com.github.jaccek.tridentina.testutils.getPrayer
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream

@RunWith(MockitoJUnitRunner::class)
class AllPrayersAssetsSpecificationTest {

    @Mock
    lateinit var assetManager: AssetManager

    lateinit var specification: AllPrayersAssetsSpecification

    val prayer = getPrayer()

    @Before
    fun setup() {
        specification = AllPrayersAssetsSpecification()
    }

    @Test
    fun testGetResults_assetsFound() {
        val gson = Gson()
        val prayerAsString = gson.toJson(prayer)
        val fileNames = arrayOf("test.json", "test2.json")
        prepareAssetManager(fileNames, prayerAsString)

        val single = specification.getResults(assetManager)

        verifySingle(single)
    }

    private fun prepareAssetManager(fileNames: Array<String>, prayerAsString: String) {
        `when`(assetManager.list(".")).thenReturn(fileNames)
        for (filename in fileNames) {
            val prayerInputStream = ByteArrayInputStream(prayerAsString.toByteArray())
            `when`(assetManager.open(eq(filename), any())).thenReturn(prayerInputStream)
        }
    }

    private fun verifySingle(single: Single<Collection<Prayer>>) {
        val testObserver = TestObserver<Collection<Prayer>>()
        single.subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue {
            verifyPrayerCollection(it)
        }
    }

    private fun verifyPrayerCollection(collection: Collection<Prayer>): Boolean {
        assertThat(collection).hasSize(2)
        for (item in collection) {
            verifyPrayer(item)
        }
        return true
    }

    private fun verifyPrayer(item: Prayer) {
        assertThat(item.id).isEqualTo(prayer.id)
        assertThat(item.name).isEqualTo(prayer.name)
        assertThat(item.text).isEqualTo(prayer.text)
    }

    @Test
    fun testGetResults_assetsNotFound() {
        `when`(assetManager.list(".")).thenReturn(arrayOf())

        val single = specification.getResults(assetManager)

        val testObserver = TestObserver<Collection<Prayer>>()
        single.subscribe(testObserver)
        testObserver.assertError(AssetsRepositoryException::class.java)
    }
}