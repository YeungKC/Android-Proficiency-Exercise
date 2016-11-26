package yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.view.PagerAdapter
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.view.SimpleDraweeView
import yeungkc.com.gankio_for_android_proficiency_exercise.extensions.load
import java.util.*

class PicAdapter() : PagerAdapter() {
     var dataSet: List<String> = ArrayList()
    set(value) {
        field = value

        // 每次设置数据将多余的 view 清除，避免缓存越来越大
        if (imageViews.size() > 2) {
            for (i in 2..imageViews.size() - 1) {
                val simpleDraweeView = imageViews[i]
                simpleDraweeView.controller?.animatable?.stop()
                simpleDraweeView.setImageBitmap(null)

                imageViews.remove(i)
            }
        }
    }
    val imageViews = SparseArray<SimpleDraweeView>()

    override fun isViewFromObject(view: View?, `object`: Any?) = view == `object`
    override fun getCount() = dataSet.size

    override fun destroyItem(container: ViewGroup, position: Int, imageView: Any) {
        if (imageView is SimpleDraweeView) {
            container.removeView(imageView)
            imageView.controller?.animatable?.stop()
            imageView.setImageBitmap(null)
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = getImageView(container, position)
        // 初始化 hierarchy
        if (imageView.hierarchy.fadeDuration != 301)
            imageView.hierarchy = GenericDraweeHierarchyBuilder(container.resources)
                    .setFadeDuration(301)
                    .setProgressBarImage(ProgressBarDrawable())
                    .setPlaceholderImage(ColorDrawable(Color.GRAY))
                    .setFailureImage(ColorDrawable(Color.RED))
                    .build()
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