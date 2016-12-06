package yeungkc.com.gankio_for_android_proficiency_exercise.model.db

import android.content.ContentValues
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import java.util.*

object GankDataMapper :IDataMapper<GankResult> {
    val gson = Gson()

    override fun convertToDomain(result: GankResult): ContentValues =
            ContentValues().apply {
                put(GankTable.ID, result._id)
                put(GankTable.DESC, result.desc)
                put(GankTable.PUBLISHED_AT, result.publishedAt.time)
                put(GankTable.TYPE, result.type)
                put(GankTable.URL, result.url)
                put(GankTable.WHO, result.who)
                val images = result.images
                if (images != null && images.isNotEmpty()) {
                    val toJson: String = gson.toJson(images)
                    put(GankTable.IMAGES, toJson)
                }
            }

    override fun convertFromDomain(columns: Map<String, Any?>): GankResult =
            GankResult().apply {
                _id = columns[GankTable.ID] as String
                desc = columns[GankTable.DESC] as String
                publishedAt = Date(columns[GankTable.PUBLISHED_AT] as Long)
                type = columns[GankTable.TYPE] as String
                url = columns[GankTable.URL] as String
                columns[GankTable.WHO]?.let {
                    who = it as String
                }
                columns[GankTable.IMAGES]?.let {
                    images = gson.fromJson(
                            it as String, object : TypeToken<List<String>>() {}.type
                    )
                }
            }
}