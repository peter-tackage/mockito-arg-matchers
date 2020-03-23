package com.petertackage.matchers

class SongService(private val api: Api) {
    fun getLatestSongs(criteria: Criteria, limit: Int): List<Song> =
        api.fetchLatestSongs(criteria)
            .take(limit)
}