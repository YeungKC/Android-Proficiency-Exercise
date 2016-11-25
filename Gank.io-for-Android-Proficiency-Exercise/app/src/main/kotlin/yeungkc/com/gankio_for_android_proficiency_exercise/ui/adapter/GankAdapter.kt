package yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter

import android.view.ViewGroup
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder.BaseViewHolder
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder.ItemGankPicViewHolder
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder.ItemGankViewHolder
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder.ItemGankVpPicViewHolder

class GankAdapter :LoadingAdapter() {
    init {
        setHasStableIds(true)
    }
    companion object{
        const val GANK_TYPE = 1
        const val GANK_PIC_TYPE = 2
        const val GANK_VP_PIC_TYPE = 3
    }

    override fun onCreateExViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            GANK_VP_PIC_TYPE -> return ItemGankVpPicViewHolder(parent)
            GANK_PIC_TYPE -> return ItemGankPicViewHolder(parent)
            else -> return ItemGankViewHolder(parent)
        }
    }

    override fun getExItemViewType(position: Int): Int {
        return when (get(position).itemType) {
            GANK_VP_PIC_TYPE -> GANK_VP_PIC_TYPE
            GANK_PIC_TYPE -> GANK_PIC_TYPE
            else -> GANK_TYPE
        }
    }

    override fun getExItemId(position: Int): Long {
        return get(position).itemId
    }
}