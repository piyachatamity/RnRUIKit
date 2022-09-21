package com.amityeko.recognition.ui.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.amityeko.recognition.R
import com.amityeko.rnrsdk.badge.api.entity.RnRBadgeEntity
import timber.log.Timber


class CreateRecognitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recognition)

        val badge = intent.getParcelableExtra<RnRBadgeEntity>("badge_select")
        Timber.d("get badge ${badge.toString()}")
//        if (badge != null) {
//            addFragment(
//                R.id.container,
//                SendRecognitionComposeFragment.newInstance(badge).build(this as AppCompatActivity),
//                null
//            )
//        }

        supportActionBar?.hide()
    }

    private fun addFragment(containerId: Int, fragment: Fragment, addToBackStack: String?) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        if (addToBackStack != null)
            transaction.addToBackStack(addToBackStack)
        transaction.commit()
        supportFragmentManager.executePendingTransactions()
        supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
    }
}