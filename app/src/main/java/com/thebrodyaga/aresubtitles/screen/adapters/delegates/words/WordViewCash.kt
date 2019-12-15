package com.thebrodyaga.aresubtitles.screen.adapters.delegates.words

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.google.android.flexbox.FlexboxLayout
import com.thebrodyaga.aresubtitles.R
import com.thebrodyaga.aresubtitles.domine.entities.data.WordDto
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger

class WordViewCash constructor(private val context: Context) {
    private val cash: MutableMap<Int, TextView> = mutableMapOf()
    private var freeIdForView = AtomicInteger(1)

    fun getViews(words: List<WordDto>): List<TextView> {
        val result = mutableListOf<TextView>()
        words.forEach {
            val textView = getAndRemoveViewFromCash()
            bindView(textView, it)
            result.add(textView)
        }
        return result
    }

    fun setToCash(views: Sequence<TextView>) {
        views.forEach {
            cash[it.id] = it
            Timber.i("setToCash id = ${it.id}")
            Timber.i("cash.size  = ${cash.size}")
        }
    }

    private fun getAndRemoveViewFromCash(): TextView {
        val idKey = cash.keys.firstOrNull() ?: return inflateView()
        return cash[idKey]?.also {
            Timber.i("getAndRemoveViewFromCash id = ${it.id}")
            cash.remove(idKey)
        } ?: inflateView()
    }

    private fun bindView(view: TextView, text: WordDto) {
        view.text = text.word
        Timber.i("bindView id = ${view.id}")
    }

    private fun inflateView(): TextView {
        val view: TextView =
            /*LayoutInflater.from(context)
            .inflate(R.layout.item_word, rootView, false) as TextView*/
            TextView(context)
        TextViewCompat.setTextAppearance(view, R.style.BodyRegular)
        view.id = freeIdForView.getAndIncrement()
        view.layoutParams = FlexboxLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        Timber.i("inflateView id = ${view.id}")
        return view
    }
}