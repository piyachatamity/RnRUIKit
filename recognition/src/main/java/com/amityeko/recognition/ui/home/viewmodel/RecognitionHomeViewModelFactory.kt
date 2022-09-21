package com.amityeko.recognition.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amityeko.rnrsdk.category.repository.RnRCategoryRepository

class RecognitionHomeViewModelFactory(private val rnrCategoryRepository: RnRCategoryRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecognitionHomeViewModel(rnrCategoryRepository) as T
    }
}