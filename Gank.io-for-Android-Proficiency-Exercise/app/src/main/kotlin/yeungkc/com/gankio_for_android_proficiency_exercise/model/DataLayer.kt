package yeungkc.com.gankio_for_android_proficiency_exercise.model

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import yeungkc.com.gankio_for_android_proficiency_exercise.App
import yeungkc.com.gankio_for_android_proficiency_exercise.model.repo.DBManager
import java.text.SimpleDateFormat
import java.util.*


object DataLayer {
    private const val PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private const val SIMPLE_PATTERN = "yyyy-MM-dd"
    private const val DB_NAME = "gank.io"

    val simpleDateFormat = SimpleDateFormat(SIMPLE_PATTERN, Locale.getDefault())

    val gson: Gson by lazy {
        GsonBuilder()
                .setDateFormat(PATTERN)
//                .registerTypeAdapter(GString::class.java,GStringTypeAdapter.INSTANCE)
                .create()
    }

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }

    fun init(app: App) {
        DBManager.init(app)
    }
}