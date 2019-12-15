package com.thebrodyaga.aresubtitles.repository

import com.thebrodyaga.aresubtitles.domine.entities.data.SubtitleDto
import io.reactivex.Observable

interface SubtitleRepository {
    fun getSubtitleById(id: Int): Observable<SubtitleDto>
}