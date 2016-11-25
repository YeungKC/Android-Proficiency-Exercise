package yeungkc.com.gankio_for_android_proficiency_exercise.model.repo

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import yeungkc.com.gankio_for_android_proficiency_exercise.extensions.spacingText
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import yeungkc.com.gankio_for_android_proficiency_exercise.model.service.GankService
import yeungkc.com.gankio_for_android_proficiency_exercise.model.service.HttpResultFunc

class GankRemoteSource {
    fun requestContent(type: String, page: Int, limit: Int): Observable<List<GankResult>> {
        return GankService.api.categoricalData(type, page, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(HttpResultFunc<List<GankResult>>())
                .flatMap { Observable.from(it) }
                .map {
                    it.desc = it.desc?.spacingText()?.trim()
                    it
                }
                .toList()
    }
}

