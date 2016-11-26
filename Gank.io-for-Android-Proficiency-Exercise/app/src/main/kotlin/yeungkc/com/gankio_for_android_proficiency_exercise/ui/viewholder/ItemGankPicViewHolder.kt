package yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import org.jetbrains.anko.startActivity
import yeungkc.com.gankio_for_android_proficiency_exercise.R
import yeungkc.com.gankio_for_android_proficiency_exercise.databinding.ItemGankPicBinding
import yeungkc.com.gankio_for_android_proficiency_exercise.model.DataLayer
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.AutoBean
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.activity.WebViewActivity


class ItemGankPicViewHolder(parent: ViewGroup) : BaseViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_gank_pic, parent, false)) {
    val bind: ItemGankPicBinding
    var data:GankResult? = null

    init {
        bind = ItemGankPicBinding.bind(itemView)

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

        // 初始化 hierarchy
        if (bind.iv.hierarchy.fadeDuration != 301)
            bind.iv.hierarchy = GenericDraweeHierarchyBuilder(context.resources)
                    .setFadeDuration(301)
                    .setProgressBarImage(ProgressBarDrawable())
                    .setPlaceholderImage(ColorDrawable(Color.GRAY))
                    .setFailureImage(ColorDrawable(Color.RED))
                    .build()

        bind.url = data.images!![0]
        bind.title = data.desc
        bind.who = data.who
        bind.date = data.publishedAt
        bind.dateFormat = DataLayer.simpleDateFormat

        bind.executePendingBindings()
    }

    override fun onAttached() {
        super.onAttached()
        bind.iv.controller?.animatable?.start()
    }

    override fun onDetached() {
        super.onDetached()
        bind.iv.controller?.animatable?.stop()
    }

    override fun onRecycled() {
        super.onRecycled()
        bind.iv.setImageBitmap(null)
    }
}