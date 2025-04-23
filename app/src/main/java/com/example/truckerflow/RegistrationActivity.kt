package com.example.truckerflow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var sendCodeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        sendCodeButton = findViewById(R.id.sendCodeButton)

        sendCodeButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (email.isNotBlank() && phone.isNotBlank()) {
                Toast.makeText(this, "Код отправлен!", Toast.LENGTH_SHORT).show()
                // Создаем Intent для перехода на VerificationActivity
                val intent = Intent(this, VerificationActivity::class.java)
                // Запускаем VerificationActivity
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}