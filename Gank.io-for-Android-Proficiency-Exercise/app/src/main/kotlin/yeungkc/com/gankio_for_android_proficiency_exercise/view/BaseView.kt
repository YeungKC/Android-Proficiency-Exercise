package yeungkc.com.gankio_for_android_proficiency_exercise.view

interface BaseView<in T> {
    fun onLoading()
    fun onError(error: Throwable)
    fun setData(data:T)
}