package com.amityeko.recognition.ui.home.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.amityeko.recognition.ui.home.view.RecognitionHomeFragment
import com.amityeko.rnrsdk.category.api.entity.RnRCategoryEntity

class RecognitionHomeViewPagerAdapter(
    private val activity: AppCompatActivity,
    fm: FragmentManager,
    lifeCycle: Lifecycle,
    private val homeFragment: RecognitionHomeFragment
) :
    FragmentStateAdapter(fm, lifeCycle) {

    private var rnrCategories: List<RnRCategoryEntity> = emptyList()

    override fun getItemCount(): Int {
        return rnrCategories.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ThumbsUpListFragment.newInstance(rnrCategories[position]).build(activity)
        fragment.setListener(homeFragment)
        return fragment
    }

    fun getItemTitle(position: Int): String {
        return rnrCategories[position].name
    }

    fun setCategoryTab(categories: List<RnRCategoryEntity>) {
        rnrCategories = categories
        notifyDataSetChanged()
    }
}