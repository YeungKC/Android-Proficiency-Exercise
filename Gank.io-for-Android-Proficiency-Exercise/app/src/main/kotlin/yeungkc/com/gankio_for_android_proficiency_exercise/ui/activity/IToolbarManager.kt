package yeungkc.com.gankio_for_android_proficiency_exercise.ui.activity

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.Toolbar

interface IToolbarManager {

    /**
     * 使用返回箭头
     */
    fun enableHomeAsUp(up: () -> Unit) {
        getToolBar().navigationIcon = createUpDrawable()
        getToolBar().setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = DrawerArrowDrawable(getToolBar().context).apply { progress = 1f }

    fun getToolBar(): Toolbar
}