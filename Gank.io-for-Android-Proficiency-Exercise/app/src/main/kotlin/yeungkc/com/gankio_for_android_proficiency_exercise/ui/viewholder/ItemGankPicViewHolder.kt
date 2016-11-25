package yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import com.facebook.drawee.backends.pipeline.Fresco
import yeungkc.com.gankio_for_android_proficiency_exercise.R
import yeungkc.com.gankio_for_android_proficiency_exercise.databinding.ItemGankPicBinding
import yeungkc.com.gankio_for_android_proficiency_exercise.model.DataLayer
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.AutoBean
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult





class ItemGankPicViewHolder(parent: ViewGroup) : BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gank_pic, parent, false)) {
    val bind: ItemGankPicBinding

    init {
        bind = ItemGankPicBinding.bind(itemView)

//        bind.root.setOnClickListener {
//
//        }
    }

    override fun bind(data: AutoBean) {
        super.bind(data)
        if (data !is GankResult) return


        val uri: Uri = Uri.parse(data.images!![0])
        val controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build()
        bind.iv.controller = controller

        bind.title = data.desc
        bind.who = data.who
        bind.date = DataLayer.simpleDateFormat.format(data.publishedAt)

        bind.executePendingBindings()
    }
}