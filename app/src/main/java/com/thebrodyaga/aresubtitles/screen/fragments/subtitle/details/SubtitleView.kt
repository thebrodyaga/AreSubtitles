package com.thebrodyaga.aresubtitles.screen.fragments.subtitle.details

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SubtitleView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData(data: List<Any>)
}