package com.example.truckerflow

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class VerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        val continueButton = findViewById<Button>(R.id.continueButton)
        val skipButton = findViewById<Button>(R.id.skipButton)

        continueButton.setOnClickListener {
            Toast.makeText(this, "Проверка кода...", Toast.LENGTH_SHORT).show()
            // Здесь логика валидации кода
        }

        skipButton.setOnClickListener {
            // Переход в SuccessActivity
            startActivity(Intent(this, RegInfoActivity::class.java))
        }
    }
}
