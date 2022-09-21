package com.amityeko.recognition.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amityeko.common.base.BaseViewModel
import com.amityeko.rnrsdk.category.api.entity.RnRCategoryEntity
import com.amityeko.rnrsdk.category.model.CategoryListStatus
import com.amityeko.rnrsdk.category.repository.RnRCategoryRepository
import com.amityeko.rnrsdk.category.usecase.RnRGetCategoryNetworkUseCase
import com.amityeko.rnrsdk.category.usecase.RnRGetCategoryUseCase
import kotlinx.coroutines.launch
import okhttp3.Response
import timber.log.Timber


class RecognitionHomeViewModel(private val rnrCategoryRepository: RnRCategoryRepository) : BaseViewModel() {
    private val _rnrCategories = MutableLiveData<List<RnRCategoryEntity>>()
    val rnrCategories: LiveData<List<RnRCategoryEntity>>
    get() {
        return _rnrCategories
    }

    fun getCategory() {
        Timber.d("get category")
        viewModelScope.launch {
            val categoryListStatus = RnRGetCategoryUseCase(rnrCategoryRepository).execute()
            when (categoryListStatus) {
//                is CategoryListStatus.CategoryListDb -> {
//                    val cateListDb = categoryListStatus.categoryListEntity
//                    _rnrCategories.postValue(cateListDb)
//
//                    RnRGetCategoryNetworkUseCase(rnrCategoryRepository).execute()
//                    updateCategoryFromNetworkApi()
//                }
                is CategoryListStatus.CategoryListResponse -> {
                    if (categoryListStatus.isSuccess()) {
                        _rnrCategories.postValue(categoryListStatus.getCategoryList())
                    } else {
                        // TODO handle error
                        handleErrorGetCategoryFromApi(categoryListStatus.getError())
                    }
                }
            }
        }

    }

    private suspend fun updateCategoryFromNetworkApi() {
        val cateResponse = RnRGetCategoryNetworkUseCase(rnrCategoryRepository).execute()
        if (cateResponse.isSuccessful) {
            _rnrCategories.postValue(cateResponse.body())
        } else {
            handleErrorGetCategoryFromApi(cateResponse.raw())
        }
    }

    private fun handleErrorGetCategoryFromApi(response: Response) {
        Timber.d("Error get category from api: ${response.toString()}")
    }

//    fun exampleReturnFlowable(): Flowable<String> {
//        val getDataRelay = PublishProcessor.create<String>()
//        viewModelScope.launch {
//            val payloadData = "Data"
//            if (payloadData != null) {
//                getDataRelay.onNext(payloadData)
//            } else {
//                getDataRelay.onError(Throwable("Error message"))
//            }
//        }
//
//        return getDataRelay
//    }
//
//    fun exampleCallFlowable() {
//        exampleReturnFlowable()
//            .doOnNext {
//                Timber.d("payload data: $it")
//            }
//            .onErrorReturn {
//                Timber.e("error message ${it.message}")
//                "empty data"
//            }
//    }
}