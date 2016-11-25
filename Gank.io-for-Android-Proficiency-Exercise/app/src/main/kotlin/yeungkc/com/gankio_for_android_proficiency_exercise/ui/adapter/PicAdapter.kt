package yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter

import android.support.v4.view.PagerAdapter
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import yeungkc.com.gankio_for_android_proficiency_exercise.extensions.load

class PicAdapter() : PagerAdapter() {
    lateinit var dataSet: List<String>
    val imageViews = SparseArray<SimpleDraweeView>()

    override fun isViewFromObject(view: View?, `object`: Any?) = view == `object`
    override fun getCount() = dataSet.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` is View) {
            container.removeView(`object`)
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = getImageView(container, position)
        container.addView(imageView)
        imageView.load(dataSet[position])
        return imageView
    }

    private fun getImageView(container: ViewGroup, position: Int) : SimpleDraweeView {
        var imageView = imageViews[position]
        if (imageViews.get(position) == null) {
            imageView = SimpleDraweeView(container.context)
            imageViews.append(position, imageView)
        }
        return imageView
    }
}