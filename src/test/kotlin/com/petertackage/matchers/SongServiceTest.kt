package com.petertackage.matchers

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

internal class SongServiceTest {

    @Mock
    lateinit var api: Api

    lateinit var songService: SongService

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        songService = SongService(api)
    }

    @Test
    fun `getLatestSongs limits results from API`() {
        // given
        val songs = listOf(
            Song("Herbie", "Headhunters"),
            Song("Miles", "So What")
        )
        `when`(api.fetchLatestSongs(any())).thenReturn(songs)

        // when
        val result = songService.getLatestSongs(criteria = Criteria("jazz"), limit = 1);

        // then
        assert(result.size == 1)
    }

    @Test
    fun `getLatestSongs calls API with criteria parameters`() {
        // given
        val criteria = Criteria("jazz")
        val songs = listOf(
            Song("Herbie", "Headhunters"),
            Song("Miles", "So What")
        )
        `when`(api.fetchLatestSongs(any())).thenReturn(songs)

        // when
        val result = songService.getLatestSongs(criteria, limit = 1);

        // then
        verify(api).fetchLatestSongs(eq(criteria))
    }
}