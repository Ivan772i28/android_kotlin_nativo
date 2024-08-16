package com.example.myapp

import DatabaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity4: AppCompatActivity() {

    private lateinit var editTextMensaje: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var btnGuardar: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        editTextMensaje = findViewById(R.id.textm)
        datePicker = findViewById(R.id.datePicker)
        btnGuardar = findViewById(R.id.btnGuardar)
        databaseHelper = DatabaseHelper(this)
         val btns=findViewById<Button>(R.id.btnsig)
        btns.setOnClickListener{
            val intent = Intent(this, RecordatoriosActivity5::class.java)
            startActivity(intent)

        }

        btnGuardar.setOnClickListener {
            guardarRecordatorio()
        }
    }

    private fun guardarRecordatorio() {
        val mensaje = editTextMensaje.text.toString().trim()
        val calendar = Calendar.getInstance()
        calendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
        val fecha = calendar.timeInMillis

        if (mensaje.isNotEmpty()) {
            val id = databaseHelper.insertRecordatorio(mensaje, fecha)
            if (id != -1L) {
                Toast.makeText(this, "Recordatorio guardado con ID: $id", Toast.LENGTH_SHORT).show()
                editTextMensaje.text.clear()
            } else {
                Toast.makeText(this, "Error al guardar el recordatorio", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor ingresa un mensaje para el recordatorio", Toast.LENGTH_SHORT).show()
        }
    }
}
