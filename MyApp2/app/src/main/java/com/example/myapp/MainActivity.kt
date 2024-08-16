package com.example.myapp

import android.app.AlertDialog
import android.content.Intent

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
       
        val bt=findViewById<Button>(R.id.button)
        val pas=findViewById<EditText>(R.id.pass)
        val emai=findViewById<EditText>(R.id.email)


        // funciones
        bt.setOnClickListener {
            val pass = pas.text.toString()
            val email = emai.text.toString()

            if (pass == "1406" && email == "ivan@gmail.com") {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                // Successful login - what to do next?
            } else {
                androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("usuario o contraseña erroneos")
                    .setPositiveButton("Aceptar", null)
                    .show()
                // Failed login - handle the error
            }
        }
    }
        // variables
    }
