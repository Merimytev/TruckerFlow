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
    private lateinit var passwordEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var sendCodeButton: Button
    private lateinit var privacyBlock: LinearLayout
    private lateinit var closePrivacyButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        sendCodeButton = findViewById(R.id.sendCodeButton)
        privacyBlock = findViewById(R.id.privacyBlock)
        closePrivacyButton = findViewById(R.id.closePrivacyButton)

        val skipButton = findViewById<Button>(R.id.skipButton)

        sendCodeButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()

            when {
                email.isBlank() -> {
                    Toast.makeText(this, "Пожалуйста, введите адрес электронной почты", Toast.LENGTH_SHORT).show()
                }
                !isValidEmail(email) -> {
                    Toast.makeText(this, "Введите корректный адрес электронной почты", Toast.LENGTH_SHORT).show()
                }
                password.isBlank() -> {
                    Toast.makeText(this, "Пожалуйста, введите пароль", Toast.LENGTH_SHORT).show()
                }
                phone.isBlank() -> {
                    Toast.makeText(this, "Пожалуйста, введите номер телефона", Toast.LENGTH_SHORT).show()
                }
                !isValidPhone(phone) -> {
                    Toast.makeText(this, "Номер телефона должен содержать ровно 9 цифр", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Код отправлен!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, VerificationActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        skipButton.setOnClickListener {
            startActivity(Intent(this, VerificationActivity::class.java))
        }
        // Обработчик для кнопки закрытия блока конфиденциальности
        closePrivacyButton.setOnClickListener {
            privacyBlock.visibility = View.GONE // Скрываем блок
        }
    }

    private fun isValidEmail(email: String): Boolean {
        // Регулярное выражение для проверки общего формата email
        val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return emailPattern.matches(email)
    }

    private fun isValidPhone(phone: String): Boolean {
        // Проверяем, что телефон содержит ровно 9 цифр
        return phone.matches("^[0-9]{9}$".toRegex())
    }
}