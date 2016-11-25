package yeungkc.com.gankio_for_android_proficiency_exercise.model

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import yeungkc.com.gankio_for_android_proficiency_exercise.App
import java.text.SimpleDateFormat
import java.util.*


object DataLayer {
    private const val PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    private const val SIMPLE_PATTERN = "yyyy-MM-dd"
    val simpleDateFormat = SimpleDateFormat(SIMPLE_PATTERN, Locale.getDefault())

    val gson: Gson by lazy {
        GsonBuilder()
                .setDateFormat(PATTERN)
                .create()
    }

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }

    fun init(app: App) {
    }
}