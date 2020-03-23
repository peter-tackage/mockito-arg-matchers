package com.petertackage.matchers

import com.nhaarman.mockitokotlin2.any
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
    fun `getLatestSongs limits results from api`() {
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
}