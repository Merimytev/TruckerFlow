package com.example.truckerflow

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var sendCodeButton: Button
    private lateinit var privacyBlock: LinearLayout
    private lateinit var closePrivacyButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        sendCodeButton = findViewById(R.id.sendCodeButton)
        privacyBlock = findViewById(R.id.privacyBlock)
        closePrivacyButton = findViewById(R.id.closePrivacyButton)

        sendCodeButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (email.isNotBlank() && phone.isNotBlank()) {
                Toast.makeText(this, "Код отправлен!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, VerificationActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        // Обработчик для кнопки закрытия блока конфиденциальности
        closePrivacyButton.setOnClickListener {
            privacyBlock.visibility = View.GONE // Скрываем блок
        }
    }
}