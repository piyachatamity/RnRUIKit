package com.amityeko.common.base

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<ITEM : Any,
        VH : BaseViewHolder<ITEM, ViewBinding>>() : RecyclerView.Adapter<VH>() {

    protected val list: ArrayList<ITEM> = arrayListOf()

    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        list[position].let {
            holder.onBind(it)
        }
    }

    fun setItems(listItems: List<ITEM>) {
        list.clear()
        list.addAll(listItems)
        notifyDataSetChanged()
    }


    fun setItems(
        listItems: List<ITEM>,
        diffCallBack: DiffUtil.Callback
    ) {
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        list.clear()
        list.addAll(listItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = list.size

    fun getItem(position: Int): ITEM = list[position]

}