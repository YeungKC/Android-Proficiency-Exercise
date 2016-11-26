package yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.AutoBean

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val context: Context
        get() = itemView.context

    open fun bind(data: AutoBean) {

    }

    open fun bind() {

    }

    open fun onDetached() {
    }

    open fun onAttached() {

    }
}
