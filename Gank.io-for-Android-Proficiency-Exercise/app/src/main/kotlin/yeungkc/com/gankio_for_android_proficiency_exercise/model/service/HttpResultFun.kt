package yeungkc.com.gankio_for_android_proficiency_exercise.model.service

import android.accounts.NetworkErrorException
import rx.functions.Func1
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.BaseResult

class HttpResultFunc<T> : Func1<BaseResult<T>, T> {
    override fun call(t: BaseResult<T>): T {
        if (t.error) throw ApiException("返回数据错误")
        return t.results
    }
}

class ApiException(message:String?= null) : NetworkErrorException(message)
