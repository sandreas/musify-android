package com.codewithfk.musify_android.mediaSource.api

enum class MediaSourceMediaType(val value: Int) {
    Podcast(0),
    Music(1),
    Audiobook(2),

    /*
    // iTunes Media Type - match where possible
    Unspecified = 0,
    Music = 1,
    Audiobook = 2,
    MusicVideo = 6,
    Movie = 9,
    TvShow = 10,
    Booklet = 11,
    Ringtone = 14,
    ItunesU = 23,

    Mixed = int.MaxValue
     */
}
