package yeungkc.com.gankio_for_android_proficiency_exercise.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.isNetworkAvailable(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo: NetworkInfo? = manager.activeNetworkInfo
    return activeNetworkInfo?.isAvailable ?: false
}