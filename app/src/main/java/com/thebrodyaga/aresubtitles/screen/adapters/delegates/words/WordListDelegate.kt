package com.thebrodyaga.aresubtitles.screen.adapters.delegates.words

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.thebrodyaga.aresubtitles.R
import kotlinx.android.synthetic.main.item_word_list.view.*

class WordListDelegate :
    AbsListItemAdapterDelegate<List<String>, Any, WordListDelegate.ViewHolder>() {
    var viewCash: WordViewCash? = null

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word_list, parent, false)
    )

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int)
            : Boolean = item is List<*>

    override fun onBindViewHolder(
        item: List<String>,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) = holder.bind(item)

    fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        viewCash = WordViewCash(recyclerView.context)
    }


    fun onDetachedFromRecyclerView() {
        viewCash = null
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(any: List<String>): Unit = with(itemView) {
            viewCash?.setToCash(flexbox_layout.children.filterIsInstance<TextView>())
            flexbox_layout.removeAllViewsInLayout()
            val views = viewCash?.getViews(any)
            views?.forEach { flexbox_layout.addView(it) }
        }
    }


}