package yeungkc.com.gankio_for_android_proficiency_exercise.model.db

import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.select
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult

class GankDb private constructor(val gankDbHelper: GankDbHelper = GankDbHelper.instance,
                                 val gankDataMapper: IDataMapper<GankResult> = GankDataMapper) {
    companion object {
        val instance by lazy { GankDb() }
    }

    fun saveGank(dataSet: List<GankResult>) {
        gankDbHelper.use {
            dataSet.forEach {
                insertWithOnConflict(GankTable.NAME, null, gankDataMapper.convertToDomain(it),
                        SQLiteDatabase.CONFLICT_REPLACE)
            }
        }
    }

    fun getDataSet(categorical: String): List<GankResult> {
        return gankDbHelper.use {
            select(GankTable.NAME)
                    .whereSimple("${GankTable.TYPE} = ?", categorical)
                    .orderBy(GankTable.PUBLISHED_AT, SqlOrderDirection.DESC)
                    .parseList(object : MapRowParser<GankResult> {
                        override fun parseRow(columns: Map<String, Any?>) =
                                columns.run {
                                    gankDataMapper.convertFromDomain(this)
                                }
                    })
        }
    }
}
