package com.github.jaccek.tridentina.data.rdp.repository.impl.sharedprefs

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.github.jaccek.tridentina.DIProviderRule
import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.PrayerId
import com.github.jaccek.tridentina.data.rdp.specification.impl.sharedprefs.SharedPrefsSpecification
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*

@SuppressLint("CommitPrefEdits")
class BookmarkSharedPrefsRepositoryTest {

    @get:Rule
    val diProviderRule = DIProviderRule()

    @Mock
    lateinit var sharedPrefs: SharedPreferences
    @Mock
    lateinit var editor: SharedPreferences.Editor
    @Mock
    lateinit var specification: SharedPrefsSpecification<Bookmark>

    @InjectMocks
    lateinit var repository: BookmarkSharedPrefsRepository

    val prayerId: PrayerId = "test id"
    val isBookmark = true
    val bookmark = Bookmark(prayerId, isBookmark)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        `when`(sharedPrefs.edit()).thenReturn(editor)
        `when`(editor.putBoolean(any(), any())).thenReturn(editor)
        `when`(editor.remove(any())).thenReturn(editor)
    }

    @Test
    fun testAdd() {
        repository.add(bookmark)

        verifyBookmarkSet()
    }

    @Test
    fun testUpdate() {
        repository.update(bookmark)

        verifyBookmarkSet()
    }

    private fun verifyBookmarkSet() {
        verifyEdition()
        verify(editor).putBoolean(bookmark.prayerId, bookmark.isBookmark)
    }

    @Test
    fun testRemove() {
        repository.remove(bookmark)

        verifyBookmarkRemoved()
    }

    private fun verifyBookmarkRemoved() {
        verifyEdition()
        verify(editor).remove(bookmark.prayerId)
    }

    private fun verifyEdition() {
        verify(sharedPrefs).edit()
        verify(editor).apply()
    }

    @Test
    fun testQuery() {
        val single = Single.just<Collection<Bookmark>>(Collections.singletonList(bookmark))
        `when`(specification.getResults(sharedPrefs)).thenReturn(single)

        val returnedSingle = repository.query(specification)

        verify(specification).getResults(sharedPrefs)
        assertThat(returnedSingle).isEqualTo(single)
    }
}