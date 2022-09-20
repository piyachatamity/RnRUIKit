package com.amityeko.common.base

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    var permissionList: Array<String>? = null

    fun addChildFragment(
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

    fun hideKeyboard(activity: Activity) {
        val v: View? = activity.window.currentFocus
        if (v != null) {
            val imm: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }


    fun hasPermissions(context: Context, permissionList: Array<String>) = permissionList.all {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(permissionList: Array<String>) {
        this.permissionList = permissionList
        activityResultLauncher.launch(permissionList)
    }

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        { permissions ->
            // Handle Permission granted/rejected
            if (permissionList != null) {
                var permissionGranted = true
                permissions.entries.forEach {
                    if (it.key in permissionList!! && !it.value) {
                        permissionGranted = false
                    }
                }
                permissionList = null
                if (!permissionGranted) {
                    onPermissionDenied()
                } else {
                    onPermissionGranted()
                }
            }
        }

    open fun onPermissionGranted() {

    }

    open fun onPermissionDenied() {
        Toast.makeText(context, "Permission request denied", Toast.LENGTH_LONG).show()
        //activity?.onBackPressed()
    }
}