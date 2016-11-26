package yeungkc.com.gankio_for_android_proficiency_exercise.extensions

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter.ArrayAutoBindAdapter
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter.LoadingAdapter
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter.LoadingAdapter.Companion.LOADING_MORE_TYPE

fun RecyclerView.setOnLoadMore(more: (Int) -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy < 0) return

            val lastVisibleItemPosition = recyclerView.getLastVisibleItemPosition()

            val itemCount = recyclerView.adapter.itemCount - 1

            if (itemCount != lastVisibleItemPosition) return

            if (recyclerView.isParentIsRefreshing()) return

            if (!recyclerView.isAdapterIsLoadMoreType()) return

            if (!recyclerView.isAdapterCanLoadMore()) return

            more(lastVisibleItemPosition)
        }
    })
}

fun RecyclerView.isAdapterCanLoadMore(): Boolean {
    var b = false
    val adapter = adapter
    if (adapter is ArrayAutoBindAdapter) {
        b = adapter.isCanLoading()
    }
    return b
}

fun RecyclerView.isAdapterIsLoadMoreType(): Boolean {
    var b = false

    val adapter = adapter
    if (adapter is LoadingAdapter) {
        b = adapter.loadMoreMsgType == LOADING_MORE_TYPE
    }
    return b
}

fun RecyclerView.isParentIsRefreshing(): Boolean {
    var isRefreshing = false
    val parent = parent

    if (parent is SwipeRefreshLayout) isRefreshing = parent.isRefreshing

    return isRefreshing
}

fun RecyclerView.getLastVisibleItemPosition(): Int {
    return layoutManager.getLastVisibleItemPosition()
}

fun RecyclerView.LayoutManager.getLastVisibleItemPosition(): Int {
    var lastVisibleItemPosition = -2

    when (this) {
        is LinearLayoutManager -> lastVisibleItemPosition = findLastVisibleItemPosition()

        is StaggeredGridLayoutManager -> {
            val findLastVisibleItemPositions = findLastVisibleItemPositions(null)

            findLastVisibleItemPositions
                    .asSequence()
                    .filter { it >= lastVisibleItemPosition }
                    .forEach { lastVisibleItemPosition = it }
        }
    }

    return lastVisibleItemPosition
}

