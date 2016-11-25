package yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.fragment.GankFragment

class MainAdapter(val titles: Array<out String>, supportFragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(supportFragmentManager) {
    
    override fun getItem(position: Int) = GankFragment.newInstance(titles[position])
    override fun getCount(): Int = titles.size
    override fun getPageTitle(position: Int) = titles[position]
}