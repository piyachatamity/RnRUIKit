package com.amityeko.rnruikit

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amityeko.recognition.ui.home.view.RecognitionHomeDialogFragment
import com.amityeko.rnr.main.RewardAndRecognition
import com.amityeko.rnruikit.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)


        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        binding.btn.setOnClickListener {
            openRnR()
        }

        registerDevice()
    }

    private fun openRnR() {
//        val intent = Intent(this, RnRMenuActivity::class.java)
//        startActivity(intent)
        openDialogCreateRecognition()
    }

    private fun registerDevice() {
        val apiKey = "ffe5bd3b1cb3128fc9e185f0057c33856f5dc665d4a71ae1fc653d3fe69501344ea3a82dc84344cf" // DEV
//        val apiKey = "a5f644dd5f6faa78c12a6347748f145d090e83e4a1c6de939be9eb283ad51a1caca27ae53a35c3fe" // STG
        RewardAndRecognition.registerDevice(this, apiKey, "132", "aaa")
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn {
                Timber.e("Register device error: ${it.message}")
                Toast.makeText(this, "Register device error: ${it.message}", Toast.LENGTH_SHORT).show()
                false
            }
            .doOnNext {
                Timber.d("register finish with result: $it")
            }
            .subscribe{
                Timber.d("register result $it")
                Toast.makeText(this, "register result $it", Toast.LENGTH_SHORT).show()

//                if (it) {
//                    setupEventRnR()
//                }
            }
    }

    private fun openDialogCreateRecognition() {
        val fragment = RecognitionHomeDialogFragment.newInstance()
        fragment.show(
            supportFragmentManager,
            RecognitionHomeDialogFragment.TAG
        )
    }
}