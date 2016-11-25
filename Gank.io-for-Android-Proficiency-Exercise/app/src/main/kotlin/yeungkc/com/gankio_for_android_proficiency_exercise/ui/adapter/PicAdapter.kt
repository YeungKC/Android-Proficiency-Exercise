package yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter

import android.support.v4.view.PagerAdapter
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class PicAdapter() : PagerAdapter() {
    lateinit var dataSet: List<String>
    val imageViews = SparseArray<ImageView>()

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

//        Glide.with(container.context)
//                .load(CustomImageSizeModelGankStudio(dataSet[position]))
//                .asGif()
//                .placeholder(ColorDrawable(Color.BLUE))
//                .error(ColorDrawable(Color.RED))
//                .centerCrop()
//                .into(imageView)

        return imageView
    }

    private fun getImageView(container: ViewGroup, position: Int) :ImageView{
        var imageView = imageViews[position]
        if (imageViews.get(position) == null) {
            imageView = ImageView(container.context)
            imageViews.append(position, imageView)
        }
        return imageView
    }
}