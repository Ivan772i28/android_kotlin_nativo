package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp.R.id.btn
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn = findViewById<Button>(R.id.btn)
        var fechaSeleccionada: Long = 0
        val calendario = findViewById<CalendarView>(R.id.calendar)
        val btnss =findViewById<Button>(R.id.btns3)
        btnss.setOnClickListener{
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        calendario.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            fechaSeleccionada = calendar.timeInMillis
        }

        btn.setOnClickListener {
            if (fechaSeleccionada != 0L) {
                val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val fechaFormateada = formatoFecha.format(Date(fechaSeleccionada)) // Format the Date object

                AlertDialog.Builder(this)
                    .setTitle("Fecha Seleccionada")
                    .setMessage(fechaFormateada)
                    .setPositiveButton("Aceptar", null)
                    .show()
            } else {
                // Handle the case where no date is selected
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Por favor, selecciona una fecha.")
                    .setPositiveButton("Aceptar", null)
                    .show()
            }
        }
    }
    }