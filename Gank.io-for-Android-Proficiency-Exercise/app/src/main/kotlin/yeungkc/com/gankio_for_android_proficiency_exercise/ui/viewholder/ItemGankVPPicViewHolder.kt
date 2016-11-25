package yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import yeungkc.com.gankio_for_android_proficiency_exercise.R
import yeungkc.com.gankio_for_android_proficiency_exercise.databinding.ItemGankVpPicBinding
import yeungkc.com.gankio_for_android_proficiency_exercise.model.DataLayer
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.AutoBean
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter.PicAdapter

class ItemGankVpPicViewHolder(parent: ViewGroup) : BaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gank_vp_pic, parent, false)) {
    val bind: ItemGankVpPicBinding
    val picAdapter:PicAdapter

    init {
        bind = ItemGankVpPicBinding.bind(itemView)
        picAdapter = PicAdapter()

//        bind.root.setOnClickListener {
//
//        }
    }

    override fun bind(data: AutoBean) {
        super.bind(data)
        if (data !is GankResult) return

        picAdapter.dataSet = data.images!!
        bind.viewPager.adapter = picAdapter
        bind.indicator.setViewPager(bind.viewPager)

        bind.title = data.desc
        bind.who = data.who
        bind.date = DataLayer.simpleDateFormat.format(data.publishedAt)

        bind.executePendingBindings()
    }
}