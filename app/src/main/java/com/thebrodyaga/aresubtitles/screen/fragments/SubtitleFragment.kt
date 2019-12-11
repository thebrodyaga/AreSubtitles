package com.thebrodyaga.aresubtitles.screen.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.thebrodyaga.aresubtitles.R
import com.thebrodyaga.aresubtitles.screen.adapters.SubtitleSourceAdapter
import com.thebrodyaga.aresubtitles.screen.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_subtitle.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

class SubtitleFragment : BaseFragment() {
    private val adapter = SubtitleSourceAdapter()

    private var freeIdForView = AtomicInteger(1)

    override fun getLayoutId(): Int = R.layout.fragment_subtitle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SubtitleFragment.adapter
        }
    }
    override fun onStart() {
        super.onStart()
        adapter.items = generateData().toList()
    }

    private fun generateData(): List<List<String>> {
        val result = mutableListOf<List<String>>()
        for (i in 0..100) {
            result.add(List(Random.nextInt(10, 20)) { freeIdForView.getAndIncrement().toString() })
        }
        return result
    }

    companion object {
        fun newInstance() = SubtitleFragment()
    }
}
