package com.amityeko.rnruikit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amityeko.common.CommonModule
import com.amityeko.media.MediaModule
import com.amityeko.recognition.RecognitionModule
import com.amityeko.reward.RewardModule
import com.amityeko.rnr.RnRModule
import com.amityeko.rnrsdk.RnRSdkModule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CommonModule().getMessage()
        MediaModule().getMessage()
        RecognitionModule().getMessage()
        RewardModule().getMessage()
        RnRModule().getMessage()
        RnRSdkModule().getMessage()
    }
}