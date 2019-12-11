package com.thebrodyaga.aresubtitles.domine.entities.data

data class SubtitleDto(
    val lines: List<LineDto>
)

data class LineDto(
    val start: Long,
    val end: Long,
    val words: List<WordDto>
)

data class WordDto(
    val word: String
)