package yeungkc.com.gankio_for_android_proficiency_exercise.model.repo

import android.content.Context

object DBManager {
    private const val DB_NAME = "gank.io"
//    lateinit var daoSession: DaoSession
//
//    private var master: DaoMaster? = null

    fun init(context: Context) {
//        val openHelper = object : DaoMaster.DevOpenHelper(context, DB_NAME){}
//        val db = openHelper.writableDb
//        daoSession = DaoMaster(db).newSession()
    }

//        fun init(context: Context) {
//        if (master == null) {
//            synchronized(DBManager,{
//                if (master == null) {
//                    val value: DaoMaster.DevOpenHelper = object : DaoMaster.DevOpenHelper(context, DB_NAME, null){}
//                    val writableDb = value.writableDb
//                    master = DaoMaster(writableDb)
//                }
//            })
//        }
//    }
}