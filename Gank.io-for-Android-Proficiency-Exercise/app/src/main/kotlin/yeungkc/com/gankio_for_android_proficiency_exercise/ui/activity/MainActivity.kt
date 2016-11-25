package yeungkc.com.gankio_for_android_proficiency_exercise.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import yeungkc.com.gankio_for_android_proficiency_exercise.R
import yeungkc.com.gankio_for_android_proficiency_exercise.databinding.ActivityMainBinding
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter.MainAdapter

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val titles = resources.getStringArray(R.array.titles)
        binding.viewPager.offscreenPageLimit = titles.size
        binding.viewPager.adapter = MainAdapter(
                titles,
                supportFragmentManager
        )

        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}