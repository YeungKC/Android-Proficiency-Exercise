package yeungkc.com.gankio_for_android_proficiency_exercise.model

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GString
import java.io.IOException


class GStringTypeAdapter : TypeAdapter<GString>() {
    companion object{
        val INSTANCE = GStringTypeAdapter()
    }

    @Throws(IOException::class)
    override fun read(exIn: JsonReader): GString {
        val gString = GString()
        gString.value = exIn.nextString()
        return gString
    }

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: GString) {
        out.value(value.value)
    }
}