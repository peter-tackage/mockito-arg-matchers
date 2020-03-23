package com.petertackage.matchers

interface Api {
    fun fetchLatestSongs(criteria: Criteria): List<Song>
}