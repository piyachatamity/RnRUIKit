package com.amityeko.rnr.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.amityeko.recognition.ui.RecognitionPageActivity
import com.amityeko.rnr.R

class RnRMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rn_rmenu)

        val btnOpenRecognition = findViewById<Button>(R.id.btnOpenRecognition)
        btnOpenRecognition.setOnClickListener {
            openRecognition()
        }
    }

    private fun openRecognition() {
        val intent = Intent(this, RecognitionPageActivity::class.java)
        startActivity(intent)
    }
}