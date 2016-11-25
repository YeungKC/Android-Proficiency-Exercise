package yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.AutoBean
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.viewholder.BaseViewHolder
import java.util.*

abstract class ArrayAdapter(capacity: Int = 0) : RecyclerView.Adapter<BaseViewHolder>() {
    var dataSets: List<AutoBean>

    init {
        dataSets = ArrayList<AutoBean>(capacity)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(get(position))
    }

    override fun getItemCount(): Int {
        return size
    }

    val size: Int
        get() = dataSets.size

    fun get(index: Int): AutoBean {
        return dataSets[index]
    }

    var oldSize = size
    fun isCanLoading() = size == oldSize

    open fun replaceWith(dataSets: List<AutoBean>, onLoaded: () -> Unit = {}) {
        if (!isCanLoading()) throw RuntimeException("now adapter can't loading")

        val newDataSets = dataSets
        val oldDataSets = this.dataSets

        this.dataSets = newDataSets

        if (newDataSets.isEmpty() || oldDataSets.isEmpty()) {
            DiffUtil.calculateDiff(DiffCallback(newDataSets, oldDataSets)).dispatchUpdatesTo(this)
            oldSize = size
            onLoaded()
        } else {
            Observable.create<DiffUtil.DiffResult> {
                it.onStart()
                it.onNext(DiffUtil.calculateDiff(DiffCallback(newDataSets, oldDataSets)))
                it.onCompleted()
            }
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        it.dispatchUpdatesTo(this)
                        oldSize = size
                        onLoaded()
                    }
        }
    }

    open fun addDataSets(dataSets: List<AutoBean>,onLoaded: () -> Unit) {
        replaceWith(this.dataSets + dataSets, onLoaded)
    }

    fun isHaveDataSets(): Boolean = dataSets.isNotEmpty()
}