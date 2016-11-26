package yeungkc.com.gankio_for_android_proficiency_exercise.extensions

import com.facebook.drawee.view.SimpleDraweeView
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.bind.adapter.loadImage

fun SimpleDraweeView.load(url: String) {
    loadImage(this, url)
}
