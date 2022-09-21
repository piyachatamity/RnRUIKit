package com.amityeko.recognition.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.amityeko.common.base.BaseFragment
import com.amityeko.recognition.R
import com.amityeko.recognition.databinding.FragmentRecognitionHomeBinding
import com.amityeko.recognition.ui.compose.callback.ISendRecognitionListener
import com.amityeko.recognition.ui.home.adapter.RecognitionHomeViewPagerAdapter
import com.amityeko.recognition.ui.home.callback.IDialogHomeListener
import com.amityeko.recognition.ui.home.viewmodel.RecognitionHomeViewModel
import com.amityeko.recognition.ui.home.viewmodel.RecognitionHomeViewModelFactory
import com.amityeko.recognition.ui.home.viewmodel.ThumbsUpListViewModel
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity
import com.amityeko.rnrsdk.category.repository.RnRCategoryRepository
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber


class RecognitionHomeFragment : BaseFragment(), ISendRecognitionListener, IDialogHomeListener {
    private lateinit var viewBinding: FragmentRecognitionHomeBinding
    lateinit var homeViewPagerAdapter: RecognitionHomeViewPagerAdapter

    private val _liveBadgeSelected = MutableLiveData<RnRBadgeEntity>()
    private val liveBadgeSelected: LiveData<RnRBadgeEntity>
        get() {
            return _liveBadgeSelected
        }
    private var rnrBadgeEntitySelected: RnRBadgeEntity? = null

    private val viewModel: RecognitionHomeViewModel by viewModels {
        val rnrCategoryRepository = RnRCategoryRepository(requireContext())
        RecognitionHomeViewModelFactory(rnrCategoryRepository)
    }

    lateinit var thumbsUpListViewModel: ThumbsUpListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thumbsUpListViewModel =
            ViewModelProvider(requireActivity()).get(ThumbsUpListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRecognitionHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabs()
        addListener()
    }

    private fun addListener() {
        viewBinding.btnContinue.setOnClickListener {
//            if (activity is RecognitionHomeActivity) {
//                thumbsUpListViewModel.getSelectedBadge().value?.let { it1 ->
//                    (activity as RecognitionHomeActivity).moveToSendRecognitionFragment(
//                        it1
//                    )
//                }
//            }

            startActivityCreateRecognition()
        }
    }

    private fun setupTabs() {
        homeViewPagerAdapter = RecognitionHomeViewPagerAdapter(
            requireActivity() as AppCompatActivity,
            childFragmentManager,
            requireActivity().lifecycle,
            this
        )

        viewModel.rnrCategories.observe(viewLifecycleOwner) {
            Timber.d("observe cate list ${it.toString()}")
            homeViewPagerAdapter.setCategoryTab(it)

            setCategoryState(it.isEmpty())
        }
        viewModel.getCategory()

        viewBinding.viewPager.adapter = homeViewPagerAdapter
        TabLayoutMediator(viewBinding.tabs, viewBinding.viewPager) { tab, position ->
            tab.text = homeViewPagerAdapter.getItemTitle(position)
        }.attach()
    }

    private fun setCategoryState(isEmpty: Boolean) {
        if (isEmpty) {
            viewBinding.emptyLayout.isVisible = true
            viewBinding.tabs.isVisible = false
            viewBinding.btnContinue.isVisible = false
            viewBinding.viewPager.isVisible = false
        } else {
            viewBinding.emptyLayout.isVisible = false
            viewBinding.tabs.isVisible = true
            viewBinding.btnContinue.isVisible = true
            viewBinding.viewPager.isVisible = true
        }
    }

    private fun startActivityCreateRecognition() {
        if (rnrBadgeEntitySelected != null) {
            val intent = Intent(context, CreateRecognitionActivity::class.java)
            intent.putExtra("badge_select", rnrBadgeEntitySelected)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): RecognitionHomeFragment {
            return RecognitionHomeFragment()
        }
    }

    override fun onClickPreview() {
//        TODO("Not yet implemented")
    }

    override fun onClickDone() {
        activity?.finish()
    }

    override fun onClickClose() {
        activity?.finish()
    }

    override fun backToEdit() {
//        TODO("Not yet implemented")
    }

    override fun onLoadedBadge(isEmpty: Boolean) {
        Timber.d("on badge loaded isEmpty: $isEmpty")
    }

    override fun onSelectedBadge(rnrBadgeEntity: RnRBadgeEntity) {
        Timber.d("selected badge ${rnrBadgeEntity.toString()}")
        rnrBadgeEntitySelected = rnrBadgeEntity
        _liveBadgeSelected.postValue(rnrBadgeEntity)
    }

    override fun getLiveSeletedBadge(): LiveData<RnRBadgeEntity> {
        return liveBadgeSelected
    }
}