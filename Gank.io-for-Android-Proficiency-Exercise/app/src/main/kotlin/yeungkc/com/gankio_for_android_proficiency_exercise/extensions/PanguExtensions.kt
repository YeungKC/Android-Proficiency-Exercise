package yeungkc.com.gankio_for_android_proficiency_exercise.extensions


object Pangu {
    val instance by lazy { ws.vinta.pangu.Pangu() }
}

/**
 * 为什么返回来的数据中英文之间没有空格呢？？？这个方法就是为了处理这个。
 */
fun String.spacingText(): String = Pangu.instance.spacingText(this)