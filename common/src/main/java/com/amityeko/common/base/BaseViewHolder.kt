package com.amityeko.common.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<E : Any, VB : ViewBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var vb: VB

    var viewBinding: VB
        get() = vb
        private set(value) {
            vb = value
        }

    init {
        vb = generateViewBinding()
    }

    abstract fun generateViewBinding(): VB

    open fun onBind(item: E) {

    }
}