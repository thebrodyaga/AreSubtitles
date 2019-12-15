package com.thebrodyaga.aresubtitles.screen.fragments.subtitle.details

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.thebrodyaga.aresubtitles.R
import com.thebrodyaga.aresubtitles.app.App
import com.thebrodyaga.aresubtitles.screen.adapters.SubtitleSourceAdapter
import com.thebrodyaga.aresubtitles.screen.base.BaseFragment
import kotlinx.android.synthetic.main.bottom_sheet_player.*
import kotlinx.android.synthetic.main.fragment_subtitle.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import kotlin.random.Random

class SubtitleFragment : BaseFragment(), SubtitleView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SubtitlePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val adapter = SubtitleSourceAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_subtitle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setOnMenuItemClickListener(this)
        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SubtitleFragment.adapter
        }
        val sheetBehavior = BottomSheetBehavior.from(bottom_sheet_player)
    }

    override fun setData(data: List<Any>) {
        adapter.items = data.toList()
    }

    companion object {
        fun newInstance() =
            SubtitleFragment()
    }
}
