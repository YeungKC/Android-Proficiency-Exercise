package yeungkc.com.gankio_for_android_proficiency_exercise.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import yeungkc.com.gankio_for_android_proficiency_exercise.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity(), IToolbarManager {
    val binding by lazy { ActivityWebViewBinding.inflate(layoutInflater) }
    override fun getToolBar() = binding.toolbar
    companion object{
        const val TITLE = "TITLE"
        const val URL = "URL"
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        initData(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initEvent()
        initData(intent)
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        binding.webView.addProgressBar()
    }

    private fun initEvent() {
        enableHomeAsUp { finish() }
    }

    private fun initData(intent: Intent) {
        title = intent.getStringExtra(TITLE)
        binding.webView.load(intent.getStringExtra(URL))
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) binding.webView.goBack() else super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webView.destroyWebView()
    }
}