package com.amityeko.recognition.ui.home.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.amityeko.common.base.BaseViewHolder
import com.amityeko.common.theme.AmityEkoTheme
import com.amityeko.recognition.R
import com.amityeko.recognition.databinding.LayoutBadgeListItemBinding
import com.amityeko.recognition.ui.home.callback.IThumbsUpListListener
import com.amityeko.rnrsdk.auth.usecase.RnRGetAccessTokenStoreUseCase
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class ThumbsUpViewHolder(
    private val context: Context,
    private val listener: IThumbsUpListListener,
) :
    BaseViewHolder<RnRBadgeEntity, LayoutBadgeListItemBinding>(
        View.inflate(
            context,
            R.layout.layout_badge_list_item,
            null
        )
    ) {

    override fun generateViewBinding(): LayoutBadgeListItemBinding {
        return LayoutBadgeListItemBinding.bind(itemView)
    }

    override fun onBind(item: RnRBadgeEntity) {
        Timber.d("onBind ${item.name.toString()}")
        val bgDrawable = viewBinding.mainContainer.background as GradientDrawable
        listener.selectedItem().observe(itemView.context as LifecycleOwner) {
            if (it.id == item.id) {
                bgDrawable.setStroke(
                    context.resources.getDimension(com.amityeko.common.R.dimen.thumbs_up_border_selected).toInt(),
                    AmityEkoTheme.colorPrimary
                )
            } else {

                bgDrawable.setStroke(
                    context.resources.getDimension(com.amityeko.common.R.dimen.thumbs_up_border_default).toInt(),
                    ContextCompat.getColor(itemView.context, com.amityeko.common.R.color.neutral_gray_8)
                )
            }
        }
        viewBinding.root.setOnClickListener { listener.onItemClick(item) }
        viewBinding.categoryTv.text = item.name
        viewBinding.pointTv.text = item.getPoint().toString()

        setImageBadge(item)
    }

    private fun setImageBadge(item: RnRBadgeEntity) {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            val token = RnRGetAccessTokenStoreUseCase(context).execute()
            val glideUrl = GlideUrl(item.getImageUrl()) { mapOf(Pair("Authorization", "Bearer $token")) }
            Glide.with(context)
                .load(glideUrl)
                .placeholder(R.drawable.badge_placeholder)
                .into(viewBinding.avatar)
        }
    }
}

