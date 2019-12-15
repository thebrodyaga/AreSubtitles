package com.thebrodyaga.aresubtitles.screen.fragments.subtitle.details

import com.thebrodyaga.aresubtitles.repository.SubtitleRepository
import com.thebrodyaga.aresubtitles.screen.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SubtitlePresenter @Inject constructor(
    private val repository: SubtitleRepository
) : BasePresenter<SubtitleView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        unSubscribeOnDestroy(
            repository.getSubtitleById(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.setData(it.lines) }, { it.printStackTrace() })
        )
    }
}