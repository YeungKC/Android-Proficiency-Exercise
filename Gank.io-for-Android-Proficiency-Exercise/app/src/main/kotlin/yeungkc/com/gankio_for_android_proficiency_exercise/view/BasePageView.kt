package yeungkc.com.gankio_for_android_proficiency_exercise.view

interface BasePageView<in T> : BaseView<T> {
    var requestPage:Int
    var currentPage:Int
    var isNoData:Boolean
}