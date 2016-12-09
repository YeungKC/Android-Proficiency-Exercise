package yeungkc.com.gankio_for_android_proficiency_exercise.contract.presenter

import rx.Subscriber
import rx.subscriptions.CompositeSubscription
import yeungkc.com.gankio_for_android_proficiency_exercise.view.BaseView

abstract class BasePresenter<T, V : BaseView<*>>() {
    var v: V? = null

    val pendingSubscriptions = CompositeSubscription()

    open fun bind(v: V) {
        this.v = v
    }

    /**
     * 清除 view，取消请求
     */
    open fun unBind(isFinishing: Boolean) {
        v = null
        if (isFinishing) pendingSubscriptions.clear()
    }

    open var remoteSubscriber: RemoteSubscriber<T, BaseView<T>>? = null

    open fun isRemoteLoading(): Boolean = !(remoteSubscriber?.isUnsubscribed ?: true)

    open fun cancelRemoteLoading() = remoteSubscriber?.unsubscribe()

    abstract class RemoteSubscriber<T, out V : BaseView<T>>(val view: V?) : Subscriber<T>() {
        override fun onStart() {
            view?.onLoading()
        }

        override fun onCompleted() {
        }

        override fun onError(e: Throwable) {
            view?.onError(e)
        }
    }
}