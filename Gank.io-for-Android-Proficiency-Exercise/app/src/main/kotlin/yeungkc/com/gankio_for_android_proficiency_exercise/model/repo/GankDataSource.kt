package yeungkc.com.gankio_for_android_proficiency_exercise.model.repo

import rx.Observable
import rx.schedulers.Schedulers
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import yeungkc.com.gankio_for_android_proficiency_exercise.model.db.GankDb

class GankDataSource {
    fun save(dataSet: List<GankResult>) {
        GankDb.instance.saveGank(dataSet)
    }

    fun getDataContent(categorical: String): Observable<List<GankResult>> =
            Observable.create<List<GankResult>> {
                it.onStart()
                it.onNext(GankDb.instance.getDataSet(categorical))
                it.onCompleted()
            }
                    .subscribeOn(Schedulers.io())
}