package com.thebrodyaga.aresubtitles.repository.impl

import com.thebrodyaga.aresubtitles.domine.entities.data.LineDto
import com.thebrodyaga.aresubtitles.domine.entities.data.SubtitleDto
import com.thebrodyaga.aresubtitles.domine.entities.data.WordDto
import com.thebrodyaga.aresubtitles.repository.SubtitleRepository
import io.reactivex.Observable
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random

class SubtitleRepositoryImpl : SubtitleRepository {
    private val freeIdForView = AtomicInteger(0)
    private val time = AtomicLong(0)
    override fun getSubtitleById(id: Int): Observable<SubtitleDto> {
        return Observable.just(generateData())
    }

    private fun generateData(): SubtitleDto {
        val result = mutableListOf<LineDto>()
        for (i in 0..30) {
            val wordsList = mutableListOf<WordDto>()
            val wordsCount = Random.nextInt(5, 15)
            for (k in 0..wordsCount) {
                wordsList.add(WordDto("Word ${freeIdForView.getAndIncrement()} "))
            }
            result.add(LineDto(time.get(), time.getAndSet(time.get() + 20), wordsList))
        }
        return SubtitleDto(result)
    }
}