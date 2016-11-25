package yeungkc.com.gankio_for_android_proficiency_exercise.extensions

import rx.Subscription
import rx.subscriptions.CompositeSubscription

fun Subscription.pending(compositeSubscription: CompositeSubscription) {
    compositeSubscription.add(this)
}
