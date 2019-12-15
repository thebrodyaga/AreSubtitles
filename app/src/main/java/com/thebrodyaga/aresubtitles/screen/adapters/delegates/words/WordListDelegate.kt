package com.thebrodyaga.aresubtitles.screen.adapters.delegates.words

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.thebrodyaga.aresubtitles.R
import com.thebrodyaga.aresubtitles.domine.entities.data.LineDto
import com.thebrodyaga.aresubtitles.domine.entities.data.SubtitleDto
import kotlinx.android.synthetic.main.item_word_list.view.*

class WordListDelegate :
    AbsListItemAdapterDelegate<LineDto, Any, WordListDelegate.ViewHolder>() {
    var viewCash: WordViewCash? = null

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_word_list, parent, false)
    )

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int)
            : Boolean = item is LineDto

    override fun onBindViewHolder(
        item: LineDto,
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

        fun bind(lineDto: LineDto): Unit = with(itemView) {
            viewCash?.setToCash(flexbox_layout.children.filterIsInstance<TextView>())
            flexbox_layout.removeAllViewsInLayout()
            val views = viewCash?.getViews(lineDto.words)
            views?.forEach { flexbox_layout.addView(it) }
            val s = lineDto.start
            time.text = String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60))
        }
    }


}