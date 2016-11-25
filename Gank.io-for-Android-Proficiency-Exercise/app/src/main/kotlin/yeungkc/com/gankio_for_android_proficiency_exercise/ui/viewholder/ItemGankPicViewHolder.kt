package yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import yeungkc.com.gankio_for_android_proficiency_exercise.R
import yeungkc.com.gankio_for_android_proficiency_exercise.databinding.ItemGankPicBinding
import yeungkc.com.gankio_for_android_proficiency_exercise.extensions.load
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

        bind.iv.load(data.images!![0])
        bind.title = data.desc
        bind.who = data.who

        bind.executePendingBindings()
    }
}