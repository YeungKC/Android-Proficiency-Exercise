package yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import org.jetbrains.anko.startActivity
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import yeungkc.com.gankio_for_android_proficiency_exercise.R
import yeungkc.com.gankio_for_android_proficiency_exercise.databinding.ItemGankVpPicBinding
import yeungkc.com.gankio_for_android_proficiency_exercise.model.DataLayer
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.AutoBean
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.activity.WebViewActivity
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter.PicAdapter
import java.util.concurrent.TimeUnit

class ItemGankVpPicViewHolder(parent: ViewGroup) : BaseViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_gank_vp_pic, parent, false)) {
    companion object {
        const val INITIAL_DELAY: Long = 3
        const val PERIOD: Long = 3
    }

    val bind: ItemGankVpPicBinding
    val picAdapter: PicAdapter
    var subscribe: Subscription? = null
    var data:GankResult? = null

    init {
        bind = ItemGankVpPicBinding.bind(itemView)
        picAdapter = PicAdapter()

        bind.viewPager.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->
                    start()
                else ->
                    stop()
            }
            return@setOnTouchListener false
        }

        bind.tvBackground.setOnClickListener {
            data?.run {
                context.startActivity<WebViewActivity>(
                        WebViewActivity.TITLE to desc,
                        WebViewActivity.URL to url
                )
            }
        }
    }

    override fun bind(data: AutoBean) {
        super.bind(data)
        if (data !is GankResult) return
        this.data = data

        picAdapter.dataSet = data.images!!
        bind.viewPager.adapter = picAdapter

        bind.indicator.count = picAdapter.count
        bind.indicator.setViewPager(bind.viewPager)
        bind.indicator.selection = bind.viewPager.currentItem

        bind.title = data.desc
        bind.who = data.who
        bind.date = data.publishedAt
        bind.dateFormat = DataLayer.simpleDateFormat

        bind.executePendingBindings()
    }

    override fun onAttached() {
        super.onAttached()
        start()
    }

    override fun onDetached() {
        super.onDetached()
        stop()
    }

    override fun onRecycled() {
        super.onRecycled()
        picAdapter.onRecycled()
    }

    /**
     * 开始轮播
     */
    fun start() {
        if (subscribe?.isUnsubscribed ?: true)
            subscribe = Observable.interval(INITIAL_DELAY, PERIOD, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        var currentItem = bind.viewPager.currentItem
                        currentItem++
                        bind.viewPager.currentItem =
                                if (currentItem == bind.viewPager.adapter.count) 0 else currentItem
                    }

    }

    /**
     * 停止轮播
     */
    fun stop() {
        subscribe?.unsubscribe()
    }
}