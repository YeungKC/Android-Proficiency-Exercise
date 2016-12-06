package yeungkc.com.gankio_for_android_proficiency_exercise.model.repo

import rx.Observable
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult

class GankRepo(private val gankRemoteSource: GankRemoteSource = GankRemoteSource(),
               private val gankDataSource: GankDataSource = GankDataSource()) {

    fun requestContent(type: String, page: Int, limit: Int): Observable<List<GankResult>> =
            gankRemoteSource.requestContent(type, page, limit)
                    .doOnNext { gankDataSource.save(it) }

    fun getDataContent(categorical: String) = gankDataSource.getDataContent(categorical)
}