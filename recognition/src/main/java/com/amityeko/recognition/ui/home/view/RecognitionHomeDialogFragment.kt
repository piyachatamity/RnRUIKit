package com.amityeko.recognition.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amityeko.common.base.BaseFragment
import com.amityeko.recognition.R
import com.amityeko.recognition.databinding.FragmentRecognitionHomeDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class RecognitionHomeDialogFragment : BottomSheetDialogFragment() {
    private lateinit var viewBinding: FragmentRecognitionHomeDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentRecognitionHomeDialogBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null)
            addSelectCateAndBadgeFragment()
    }

    override fun getTheme(): Int {
        return com.amityeko.common.R.style.AppBottomSheetDialogTheme
    }

    private fun addChildFragment(
        containerId: Int,
        fragment: BaseFragment,
        addToBackStack: Boolean = false,
        tag: String? = null
    ) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        if (addToBackStack)
            transaction.addToBackStack(tag)
        transaction.commit()
    }

    private fun addSelectCateAndBadgeFragment() {
        addChildFragment(R.id.container, RecognitionHomeFragment.newInstance())
    }

    companion object {
        val TAG = RecognitionHomeDialogFragment::class.java.simpleName
        fun newInstance() = RecognitionHomeDialogFragment()
    }
}