package yeungkc.com.gankio_for_android_proficiency_exercise.ui.bind.adapter

import android.databinding.BindingAdapter
import android.net.Uri
import android.widget.TextView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import java.text.DateFormat
import java.util.*

@BindingAdapter("app:date", "app:dateFormat")
fun setDateText(textView: TextView, date: Date, dateFormat: DateFormat) {
    textView.text = dateFormat.format(date)
}

@BindingAdapter("app:imageUrl")
fun loadImage(simpleDraweeView: SimpleDraweeView, url: String) {
    simpleDraweeView.run {
        post {
            val uri: Uri = Uri.parse("$url?imageView2/0/w/$width/h/$height")
            val controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build()
            this.controller = controller
        }
    }
}

