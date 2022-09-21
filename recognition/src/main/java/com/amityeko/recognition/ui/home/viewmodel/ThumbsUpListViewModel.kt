package com.amityeko.recognition.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amityeko.common.base.BaseViewModel
import com.amityeko.common.state.UIViewStage
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity
import com.amityeko.rnrsdk.badge.model.BadgeListState
import com.amityeko.rnrsdk.badge.usecase.RnRGetBadgeNetworkUseCase
import com.amityeko.rnrsdk.badge.usecase.RnRGetBadgeUseCase
import com.amityeko.rnrsdk.category.api.entity.RnRCategoryEntity
import kotlinx.coroutines.launch
import okhttp3.Response
import timber.log.Timber

class ThumbsUpListViewModel : BaseViewModel() {
    lateinit var category: RnRCategoryEntity
//    private var mockData = mockData()
    private var selectedBadge = MutableLiveData<RnRBadgeEntity>()

    private var _badges = MutableLiveData<List<RnRBadgeEntity>>()
    val badges: LiveData<List<RnRBadgeEntity>>
    get() {
        return _badges
    }

    private var _badgeListViewState = MutableLiveData<UIViewStage>()
    val badgeListViewState: LiveData<UIViewStage>
    get() {
        return _badgeListViewState
    }

    fun onBadgeSelected(item: RnRBadgeEntity) {
        selectedBadge.postValue(item)
    }

    fun getBadges(context: Context) {
        _badgeListViewState.postValue(UIViewStage.Loading)
        _badges.postValue(emptyList())

//        Timber.d("getBadges: category = ${category.toString()}")
        viewModelScope.launch {
            val badgeListState = RnRGetBadgeUseCase(context).execute(category.id)
            when (badgeListState) {
                is BadgeListState.BadgeListDb -> {
                    Timber.d("get badge from DB: ${badgeListState.badgeList.toString()}")
                    _badges.postValue(badgeListState.badgeList)
                    if (badgeListState.badgeList.isNotEmpty()) {
                        _badgeListViewState.postValue(UIViewStage.ShowData)
                    } else {
                        _badgeListViewState.postValue(UIViewStage.Empty)
                    }

                    updateBadgeFromNetworkApi(context)
                }
                is BadgeListState.BadgeListResponse -> {
                    if (badgeListState.isSuccess()) {
                        Timber.d("get badge from api ${badgeListState.getBadgeList().toString()}")
                        val badgeList = badgeListState.getBadgeList()
                        _badges.postValue(badgeList)
                        if (badgeList.isNotEmpty()) {
                            _badgeListViewState.postValue(UIViewStage.ShowData)
                        } else {
                            _badgeListViewState.postValue(UIViewStage.Empty)
                        }
                    } else {
                        _badgeListViewState.postValue(UIViewStage.Empty)
                        Timber.d("get badge api error: ${badgeListState.getError().toString()}")
                        // TODO Handle error state
                        handleErrorGetBadgeFromApi(badgeListState.getError())
                    }
                }
            }
        }
    }

    private suspend fun updateBadgeFromNetworkApi(context: Context) {
        val badgeResponse = RnRGetBadgeNetworkUseCase(context).execute(category.id)
        if (badgeResponse.isSuccessful) {
            val badgeList = badgeResponse.body()?.data ?: emptyList()
            _badges.postValue(badgeList)
        } else {
            handleErrorGetBadgeFromApi(badgeResponse.raw())
        }
    }

    private fun handleErrorGetBadgeFromApi(response: Response) {
        Timber.d("get badge network error: ${response.toString()}")
    }

    fun getSelectedBadge(): LiveData<RnRBadgeEntity> = selectedBadge
}