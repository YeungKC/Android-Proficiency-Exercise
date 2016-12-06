package yeungkc.com.gankio_for_android_proficiency_exercise.contract

import android.content.Context
import rx.android.schedulers.AndroidSchedulers
import yeungkc.com.gankio_for_android_proficiency_exercise.contract.presenter.BasePresenter
import yeungkc.com.gankio_for_android_proficiency_exercise.extensions.isNetworkAvailable
import yeungkc.com.gankio_for_android_proficiency_exercise.extensions.pending
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import yeungkc.com.gankio_for_android_proficiency_exercise.model.repo.GankRepo
import yeungkc.com.gankio_for_android_proficiency_exercise.view.BasePageView
import yeungkc.com.gankio_for_android_proficiency_exercise.view.NetworkView

interface GankView : BasePageView<List<GankResult>>, NetworkView

class GankPresenter(val categorical: String) : BasePresenter<List<GankResult>, GankView>() {
    companion object {
        const val DEFAULT_COUNT = 20
    }

    val gankRepo by lazy { GankRepo() }

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

        gankRepo.requestContent(type, page + 1, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(remoteSubscriber)
                .pending(pendingSubscriptions)
    }

    fun getContext(context: Context) {
        if (context.isNetworkAvailable()) {
            getRemoteContent()
        } else {
            getDataContent()
        }
    }

    private fun getDataContent() {
        gankRepo.getDataContent(categorical)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    v?.onNetworkUnavailable()
                    v?.isNoData = true
                    v?.setData(it)
                }
    }
}