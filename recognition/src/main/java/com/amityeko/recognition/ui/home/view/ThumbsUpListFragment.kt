package com.amityeko.recognition.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.amityeko.common.base.BaseFragment
import com.amityeko.recognition.R
import com.amityeko.recognition.databinding.FragmentThumbsUpListBinding
import com.amityeko.recognition.ui.home.callback.IDialogHomeListener
import com.amityeko.recognition.ui.home.callback.IThumbsUpListListener
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity
import com.amityeko.rnrsdk.category.api.entity.RnRCategoryEntity
import timber.log.Timber

class ThumbsUpListFragment : BaseFragment(), IThumbsUpListListener {
    private lateinit var thumbsUpAdapter: ThumbsUpListAdapter
    private lateinit var iDialogHomeListener: IDialogHomeListener
    lateinit var viewModel: ThumbsUpListViewModel
    lateinit var viewBinding: FragmentThumbsUpListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentThumbsUpListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListData()
    }

    private fun setupListData() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        thumbsUpAdapter = ThumbsUpListAdapter(this)
        viewBinding.thumsUpRv.layoutManager =
            GridLayoutManager(requireContext(), resources.getInteger(R.integer.thumbs_up_grid_size))
        viewBinding.thumsUpRv.adapter = thumbsUpAdapter

        viewModel.badges.observe(viewLifecycleOwner) {

            iDialogHomeListener.onLoadedBadge(it.isEmpty())
            thumbsUpAdapter.setItems(it)
        }

        viewModel.badgeListViewState.observe(viewLifecycleOwner) {
            if (it.equals(UIViewStage.Loading)) {
                setViewStateLoading()
            } else if (it.equals(UIViewStage.Empty)) {
                setViewSateEmpty()
            } else if (it.equals(UIViewStage.ShowData)) {
                setViewStateData()
            }
        }

        viewModel.getBadges(requireContext())
    }

    private fun setViewSateEmpty() {
        viewBinding.emptyLayout.isVisible = true
        viewBinding.thumsUpRv.isVisible = false
        viewBinding.loadingStateView.isVisible = false
    }
    private fun setViewStateData() {
        viewBinding.emptyLayout.isVisible = false
        viewBinding.thumsUpRv.isVisible = true
        viewBinding.loadingStateView.isVisible = false
    }
    private fun setViewStateLoading() {
        viewBinding.emptyLayout.isVisible = false
        viewBinding.thumsUpRv.isVisible = false
        viewBinding.loadingStateView.isVisible = true
    }

    fun setListener(listener: IDialogHomeListener) {
        Timber.d("setListener")
        iDialogHomeListener = listener
    }


    class Builder internal constructor() {
        private lateinit var category: RnRCategoryEntity

        fun build(activity: AppCompatActivity): ThumbsUpListFragment {
            val fragment = ThumbsUpListFragment()
//            fragment.viewModel =
//                ViewModelProvider(activity).get(ThumbsUpListViewModel::class.java)
            fragment.viewModel = ThumbsUpListViewModel()
            fragment.viewModel.category = category
//            Timber.d("build fragment ${category.name}")
            return fragment
        }

        internal fun category(category: RnRCategoryEntity): Builder {
            this.category = category
            return this
        }
    }

    companion object {
        fun newInstance(category: RnRCategoryEntity): Builder {
            return Builder().category(category)
        }
    }

    override fun onItemClick(item: RnRBadgeEntity) {
        iDialogHomeListener.onSelectedBadge(item)
        viewModel.onBadgeSelected(item)
    }

    override fun selectedItem(): LiveData<RnRBadgeEntity> {
//        return viewModel.getSelectedBadge()
        return iDialogHomeListener.getLiveSeletedBadge()
    }
}