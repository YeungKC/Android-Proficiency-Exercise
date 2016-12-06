package yeungkc.com.gankio_for_android_proficiency_exercise.model.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import rx.schedulers.Schedulers
import yeungkc.com.gankio_for_android_proficiency_exercise.BuildConfig
import yeungkc.com.gankio_for_android_proficiency_exercise.model.DataLayer
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.BaseResult
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult

class GankService {
    companion object {
        val api: APIs by lazy {
            Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(DataLayer.gson))
                    .addCallAdapterFactory(
                            RxJavaCallAdapterFactory
                                    .createWithScheduler(Schedulers.io())
                    )
                    .client(DataLayer.okHttpClient)
                    .build()
                    .create(APIs::class.java)
        }
    }

    interface APIs {
        @GET("data/{categorical}/{count}/{page}")
        fun categoricalData(
                @Path("categorical") categorical: String,
                @Path("page") page: Int,
                @Path("count") count: Int): Observable<BaseResult<List<GankResult>>>
    }
}
