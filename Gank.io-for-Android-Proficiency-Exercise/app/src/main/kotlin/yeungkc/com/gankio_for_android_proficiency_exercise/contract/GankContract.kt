package yeungkc.com.gankio_for_android_proficiency_exercise.contract

import yeungkc.com.gankio_for_android_proficiency_exercise.contract.presenter.BasePresenter
import yeungkc.com.gankio_for_android_proficiency_exercise.extensions.pending
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import yeungkc.com.gankio_for_android_proficiency_exercise.model.repo.GankRemoteSource
import yeungkc.com.gankio_for_android_proficiency_exercise.view.BasePageView

interface GankView : BasePageView<List<GankResult>>

class GankPresenter(val categorical: String) : BasePresenter<List<GankResult>, GankView>() {
    companion object {
        const val DEFAULT_COUNT = 20
    }

    val gankRemoteSource: GankRemoteSource by lazy { GankRemoteSource() }

    fun getRemoteContent(page: Int = 0, type: String = categorical, limit: Int = DEFAULT_COUNT) {
        remoteSubscriber = object : BasePresenter.RemoteSubscriber<List<GankResult>>(v) {
            override fun onStart() {
                v?.requestPage = page
                super.onStart()
            }

            override fun onNext(t: List<GankResult>) {
                v?.currentPage = page
                v?.isNoData = t.size != limit

                v?.setData(t)
            }
        }

        gankRemoteSource.requestContent(type, page + 1, limit)
                .subscribe(remoteSubscriber)
                .pending(pendingSubscriptions)
    }
}