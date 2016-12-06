package yeungkc.com.gankio_for_android_proficiency_exercise.model.db

import android.content.ContentValues

interface IDataMapper<T> {
    open fun convertToDomain(t: T): ContentValues
    open fun convertFromDomain(columns: Map<String, Any?>): T
}