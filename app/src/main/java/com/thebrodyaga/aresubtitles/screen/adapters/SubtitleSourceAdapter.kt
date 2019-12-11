package com.thebrodyaga.aresubtitles.screen.adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.thebrodyaga.aresubtitles.screen.adapters.delegates.words.WordListDelegate

class SubtitleSourceAdapter : AsyncListDifferDelegationAdapter<Any>(DiffCallback()) {
    private var wordListDelegate =
        WordListDelegate()

    init {
        delegatesManager.addDelegate(wordListDelegate)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        wordListDelegate.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        wordListDelegate.onDetachedFromRecyclerView()
    }


    class DiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun getChangePayload(oldItem: Any, newItem: Any): Any? = null

        override fun areItemsTheSame(
                oldItem: Any,
                newItem: Any
        ): Boolean {
            return false
        }

        override fun areContentsTheSame(
                oldItem: Any,
                newItem: Any
        ): Boolean {
            return false
        }
    }
}