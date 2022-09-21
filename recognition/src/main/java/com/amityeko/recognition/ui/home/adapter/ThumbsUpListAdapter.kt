package com.amityeko.recognition.ui.home.adapter

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.amityeko.common.base.BaseRecyclerViewAdapter
import com.amityeko.common.base.BaseViewHolder
import com.amityeko.recognition.ui.home.callback.IThumbsUpListListener
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity

class ThumbsUpListAdapter(private val listener: IThumbsUpListListener) :
    BaseRecyclerViewAdapter<RnRBadgeEntity, BaseViewHolder<RnRBadgeEntity, ViewBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RnRBadgeEntity, ViewBinding>
    {
        return ThumbsUpViewHolder(
            parent.context,
            listener,
        ) as BaseViewHolder<RnRBadgeEntity, ViewBinding>
    }
}