package com.example.myapp

import DatabaseHelper
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.TextView

class RecordatoriosActivity5() : AppCompatActivity(), Parcelable {

    private lateinit var textViewRecordatorios: TextView
    private lateinit var databaseHelper: DatabaseHelper

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorios5)

        textViewRecordatorios = findViewById(R.id.txtre)
        databaseHelper = DatabaseHelper(this)

        mostrarRecordatorios()
    }

    private fun mostrarRecordatorios() {
        val cursor: Cursor? = databaseHelper.getAllRecordatorios()
        val stringBuilder = StringBuilder()
        cursor?.let {
            while (it.moveToNext()) {
                val mensaje = it.getString(it.getColumnIndex(DatabaseHelper.COLUMN_MENSAJE))
                val fecha = it.getString(it.getColumnIndex(DatabaseHelper.COLUMN_FECHA))
                stringBuilder.append("Fecha: $fecha\nMensaje: $mensaje\n\n")
            }
        }
        cursor?.close()
        textViewRecordatorios.text = stringBuilder.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecordatoriosActivity5> {
        override fun createFromParcel(parcel: Parcel): RecordatoriosActivity5 {
            return RecordatoriosActivity5(parcel)
        }

        override fun newArray(size: Int): Array<RecordatoriosActivity5?> {
            return arrayOfNulls(size)
        }
    }
}
