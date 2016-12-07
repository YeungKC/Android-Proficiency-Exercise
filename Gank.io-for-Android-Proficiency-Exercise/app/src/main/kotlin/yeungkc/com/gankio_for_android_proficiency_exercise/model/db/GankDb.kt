package yeungkc.com.gankio_for_android_proficiency_exercise.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.select
import yeungkc.com.gankio_for_android_proficiency_exercise.App
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult

class GankDb constructor(val dbHelper: GankDbHelper = GankDbHelper.instance,
                         val dataMapper: IDataMapper<GankResult> = GankDataMapper) {

    constructor(context: Context = App.context) : this(GankDbHelper(context), GankDataMapper)

    companion object {
        val instance by lazy { GankDb() }
    }

    fun saveGank(dataSet: List<GankResult>) {
        dbHelper.use {
            dataSet.forEach {
                insertWithOnConflict(GankTable.NAME, null, dataMapper.convertToDomain(it),
                        SQLiteDatabase.CONFLICT_REPLACE)
            }
        }
    }

    fun getDataSet(categorical: String): List<GankResult> {
        return dbHelper.use {
            select(GankTable.NAME)
                    .whereSimple("${GankTable.TYPE} = ?", categorical)
                    .orderBy(GankTable.PUBLISHED_AT, SqlOrderDirection.DESC)
                    .parseList(object : MapRowParser<GankResult> {
                        override fun parseRow(columns: Map<String, Any?>) =
                                columns.run {
                                    dataMapper.convertFromDomain(this)
                                }
                    })
        }
    }
}
