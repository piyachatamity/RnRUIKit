package com.amityeko.recognition.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amityeko.common.base.BaseFragment
import com.amityeko.recognition.R
import com.amityeko.recognition.databinding.FragmentRecognitionHomeBinding
import com.amityeko.recognition.ui.compose.callback.ISendRecognitionListener
import com.amityeko.recognition.ui.home.callback.IDialogHomeListener
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity


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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recognition_home, container, false)
    }

    companion object {
        fun newInstance(): RecognitionHomeFragment {
            return RecognitionHomeFragment()
        }
    }

    override fun onClickPreview() {
        TODO("Not yet implemented")
    }

    override fun onClickDone() {
        TODO("Not yet implemented")
    }

    override fun onClickClose() {
        TODO("Not yet implemented")
    }

    override fun backToEdit() {
        TODO("Not yet implemented")
    }

    override fun onLoadedBadge(isEmpty: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onSelectedBadge(rnrBadgeEntity: RnRBadgeEntity) {
        TODO("Not yet implemented")
    }

    override fun getLiveSeletedBadge(): LiveData<RnRBadgeEntity> {
        TODO("Not yet implemented")
    }
}