package yeungkc.com.gankio_for_android_proficiency_exercise.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BasePageFragment : Fragment() {

    protected var isViewInitiated: Boolean = false
    protected var isVisibleToUser: Boolean = false
    protected var isDataInitiated: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        prepareInitData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        prepareInitData()
    }

    abstract fun initData()

    fun prepareInitData() {
        if (isVisibleToUser && isViewInitiated && !isDataInitiated) {
            initData()
            isDataInitiated = true
        }
    }
}
