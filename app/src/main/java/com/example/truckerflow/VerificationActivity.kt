package com.example.truckerflow

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class VerificationActivity : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var timerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val continueButton = findViewById<Button>(R.id.continueButton)
        val skipButton = findViewById<Button>(R.id.skipButton)
        timerTextView = findViewById(R.id.timerTextView)

        startTimer()

        continueButton.setOnClickListener {
            Toast.makeText(this, "Проверка кода...", Toast.LENGTH_SHORT).show()
            // Здесь логика валидации кода
        }

        skipButton.setOnClickListener {
            // Cancel the timer when skipping
            countDownTimer.cancel()
            startActivity(Intent(this, RegInfoActivity::class.java))
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(300000, 1000) { // 5 minutes = 300,000 ms
            override fun onTick(millisUntilFinished: Long) {
                // Convert milliseconds to minutes and seconds
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                // Update TextView with formatted time (MM:SS)
                timerTextView.text = getString(R.string.timer_text, String.format("%02d:%02d", minutes, seconds))
            }

            override fun onFinish() {
                // When timer finishes, display "Код истёк"
                timerTextView.text = "Код истёк"
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the timer to prevent memory leaks
        countDownTimer.cancel()
    }
}