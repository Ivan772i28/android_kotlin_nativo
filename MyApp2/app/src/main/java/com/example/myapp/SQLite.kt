import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        public const val DATABASE_VERSION = 1
        public const val DATABASE_NAME = "Recordatorios.db"
        public const val TABLE_NAME = "recordatorios"
        public const val COLUMN_ID = "id"
        public const val COLUMN_MENSAJE = "mensaje"
        public const val COLUMN_FECHA = "fecha"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableSQL = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_MENSAJE TEXT," +
                "$COLUMN_FECHA TEXT" +
                ")"
        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableSQL = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableSQL)
        onCreate(db)
    }

    fun insertRecordatorio(mensaje: String, fecha: Long): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_MENSAJE, mensaje)
        contentValues.put(COLUMN_FECHA, SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date(fecha)))
        val id = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return id
    }

    fun getAllRecordatorios(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_FECHA ASC", null)
    }
}
