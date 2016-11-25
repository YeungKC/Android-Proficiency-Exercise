package yeungkc.com.gankio_for_android_proficiency_exercise.extensions


object Pangu {
    val instance by lazy { ws.vinta.pangu.Pangu() }
}

fun String?.spacingText(): String? = this?.let { Pangu.instance.spacingText(it) }
