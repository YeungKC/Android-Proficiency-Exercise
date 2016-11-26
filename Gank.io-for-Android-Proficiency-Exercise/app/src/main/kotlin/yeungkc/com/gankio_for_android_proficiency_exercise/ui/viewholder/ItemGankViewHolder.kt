package yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import org.jetbrains.anko.startActivity
import yeungkc.com.gankio_for_android_proficiency_exercise.R
import yeungkc.com.gankio_for_android_proficiency_exercise.databinding.ItemGankBinding
import yeungkc.com.gankio_for_android_proficiency_exercise.model.DataLayer
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.AutoBean
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.activity.WebViewActivity

class ItemGankViewHolder(parent: ViewGroup) :BaseViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_gank, parent, false)) {
    val bind: ItemGankBinding
    var data:GankResult? = null

    init {
        bind = ItemGankBinding.bind(itemView)

        bind.root.setOnClickListener {
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

        bind.title = data.desc
        bind.who = data.who
        bind.date = data.publishedAt
        bind.dateFormat= DataLayer.simpleDateFormat

        bind.executePendingBindings()
    }
}