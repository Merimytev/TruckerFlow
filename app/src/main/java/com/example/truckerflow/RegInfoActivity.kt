package com.example.truckerflow

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
//import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class RegInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_info)

class IdGenerator(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("ProfilePrefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()
    private val KEY_ID = "last_profile_id"

    fun generateId(): Int {
        val lastId = prefs.getInt(KEY_ID, 0)
        val newId = lastId + 1
        editor.putInt(KEY_ID, newId).apply()
        return newId
    }
}
        // Находим элементы по их ID
        val firstNameEditText = findViewById<EditText>(R.id.FirstNameEditText)
        val secondNameEditText = findViewById<EditText>(R.id.SecondNameEditText)
        val middleNameEditText = findViewById<EditText>(R.id.MiddleNameEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val sendCodeButton = findViewById<Button>(R.id.sendCodeButton)

        // Настройка DatePicker для поля "Дата рождения"
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%02d.%02d.%d", selectedDay, selectedMonth + 1, selectedYear)
            phoneEditText.setText(formattedDate)
        }, year, month, day)

        // Отключаем ручной ввод в поле даты
        phoneEditText.isFocusable = false
        phoneEditText.isFocusableInTouchMode = false

        // Обработчик нажатия на поле даты или кнопку
        phoneEditText.setOnClickListener {
            datePickerDialog.show()
        }

        // Создаем генератор ID
        val idGenerator = IdGenerator(this)

        // Обработчик нажатия на кнопку "Дальше"
        sendCodeButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val secondName = secondNameEditText.text.toString()
            val middleName = middleNameEditText.text.toString()
            val birthDate = phoneEditText.text.toString()

            if (firstName.isEmpty() || secondName.isEmpty() || middleName.isEmpty() || birthDate.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Генерация ID для профиля
            val profileId = idGenerator.generateId()

            // Переход на следующую активность
            val intent = Intent(this, SuccessActivity::class.java).apply {
                putExtra("PROFILE_ID", profileId)
                putExtra("FIRST_NAME", firstName)
                putExtra("SECOND_NAME", secondName)
                putExtra("MIDDLE_NAME", middleName)
                putExtra("BIRTH_DATE", birthDate)
            }


            startActivity(intent)
        }
    }
}