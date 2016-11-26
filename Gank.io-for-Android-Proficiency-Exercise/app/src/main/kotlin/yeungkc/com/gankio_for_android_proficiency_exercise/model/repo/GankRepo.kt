package yeungkc.com.gankio_for_android_proficiency_exercise.model.repo

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult

class GankRepo(private val gankRemoteSource: GankRemoteSource = GankRemoteSource(),
               private val gankDataSource: GankDataSource= GankDataSource()) {

    fun requestContent(type: String, page: Int, limit: Int): Observable<List<GankResult>> =
            gankRemoteSource.requestContent(type, page, limit)
                    .observeOn(Schedulers.io())
                    .doOnNext { gankDataSource.save(it) }
                    .observeOn(AndroidSchedulers.mainThread())

    fun getDataContent(categorical: String) = gankDataSource.getDataContent(categorical)
}