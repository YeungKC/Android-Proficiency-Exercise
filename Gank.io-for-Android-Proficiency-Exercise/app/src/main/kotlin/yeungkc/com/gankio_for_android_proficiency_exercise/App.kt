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

        Stetho.initializeWithDefaults(this)

        Fresco.initialize(this,
                OkHttpImagePipelineConfigFactory
                        .newBuilder(this, DataLayer.okHttpClient)
                        .setDownsampleEnabled(true)
                        .setBitmapsConfig(Bitmap.Config.RGB_565)
                        .build())
    }

    companion object{
        lateinit var context: Context
        // 所有 RecyclerView 使用统一缓存
        val recycledViewPool: RecyclerView.RecycledViewPool by lazy { RecyclerView.RecycledViewPool() }
    }
}