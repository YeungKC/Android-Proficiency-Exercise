package yeungkc.com.gankio_for_android_proficiency_exercise

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.stetho.Stetho
import yeungkc.com.gankio_for_android_proficiency_exercise.model.DataLayer

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        context = this

        DataLayer.init(this)

        Stetho.initializeWithDefaults(this)

        Fresco.initialize(this,
                OkHttpImagePipelineConfigFactory
                        .newBuilder(this, DataLayer.okHttpClient)
                        .setDownsampleEnabled(true)
                        .setBitmapsConfig(Bitmap.Config.RGB_565)
                        .build())

        initGlide(this)
    }

    companion object{
        lateinit var context: Context
        val recycledViewPool: RecyclerView.RecycledViewPool by lazy { RecyclerView.RecycledViewPool() }
    }

    private fun initGlide(app: App) {
//        Glide.get(app)
//                .register(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(DataLayer.okHttpClient))
    }
}