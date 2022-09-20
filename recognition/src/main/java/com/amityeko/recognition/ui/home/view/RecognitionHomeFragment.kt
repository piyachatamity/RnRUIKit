package com.amityeko.recognition.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amityeko.common.base.BaseFragment
import com.amityeko.recognition.R


class RecognitionHomeFragment : BaseFragment() {

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
}