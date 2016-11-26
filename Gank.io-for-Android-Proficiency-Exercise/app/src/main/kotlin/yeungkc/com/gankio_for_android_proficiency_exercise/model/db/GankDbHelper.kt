package yeungkc.com.gankio_for_android_proficiency_exercise.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import yeungkc.com.gankio_for_android_proficiency_exercise.App

class GankDbHelper private constructor(context: Context = App.context) : ManagedSQLiteOpenHelper(
        context,DB_NAME,null, DB_VERSION) {
    companion object{
        val DB_NAME = "gank.db"
        val DB_VERSION = 1
        val instance by lazy { GankDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
                GankTable.NAME,true,
                GankTable.ID to TEXT + PRIMARY_KEY,
                GankTable.DESC to TEXT,
                GankTable.PUBLISHED_AT to INTEGER,
                GankTable.TYPE to TEXT,
                GankTable.URL to TEXT,
                GankTable.WHO to TEXT,
                GankTable.IMAGES to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(GankTable.NAME, true)
        onCreate(db)
    }
}