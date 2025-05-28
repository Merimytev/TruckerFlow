package com.example.truckerflow

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class VerificationActivity : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var timerTextView: TextView
    private lateinit var code1: EditText
    private lateinit var code2: EditText
    private lateinit var code3: EditText
    private lateinit var code4: EditText
    private lateinit var repeatButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        // Initialize views
        val continueButton = findViewById<Button>(R.id.continueButton)
        val skipButton = findViewById<Button>(R.id.skipButton)
        timerTextView = findViewById(R.id.timerTextView)
        code1 = findViewById(R.id.code1)
        code2 = findViewById(R.id.code2)
        code3 = findViewById(R.id.code3)
        code4 = findViewById(R.id.code4)
        repeatButton = findViewById(R.id.repeatButton)

        // Start the timer
        startTimer()

        // Set up TextWatchers for EditText fields
        setupTextWatchers()

        // Set up click listener for repeat button
        repeatButton.setOnClickListener {
            restartTimer()
            Toast.makeText(this, "Код отправлен повторно", Toast.LENGTH_SHORT).show()
        }

        continueButton.setOnClickListener {
            Toast.makeText(this, "Проверка кода...", Toast.LENGTH_SHORT).show()
            // Здесь логика валидации кода
        }

        skipButton.setOnClickListener {
            countDownTimer.cancel()
            startActivity(Intent(this, RegInfoActivity::class.java))
        }
    }

    private fun setupTextWatchers() {
        code1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    code2.requestFocus()
                }
            }
        })

        code2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    code3.requestFocus()
                } else if (s?.length == 0) {
                    code1.requestFocus()
                }
            }
        })

        code3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    code4.requestFocus()
                } else if (s?.length == 0) {
                    code2.requestFocus()
                }
            }
        })

        code4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 0) {
                    code3.requestFocus()
                }
            }
        })
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(300000, 1000) { // 5 minutes = 300,000 ms
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timerTextView.text = getString(R.string.timer_text, String.format("%02d:%02d", minutes, seconds))
            }

            override fun onFinish() {
                timerTextView.text = "Код истёк"
            }
        }.start()
    }

    private fun restartTimer() {
        countDownTimer.cancel() // Cancel the current timer
        startTimer() // Start a new timer
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}