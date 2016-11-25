package yeungkc.com.gankio_for_android_proficiency_exercise.extensions

import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView

fun SimpleDraweeView.load(url: String) {
    post {
        val uri: Uri = Uri.parse("$url?imageView2/0/w/$width/h/$height")
        val controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build()
        this.controller = controller
    }
}
