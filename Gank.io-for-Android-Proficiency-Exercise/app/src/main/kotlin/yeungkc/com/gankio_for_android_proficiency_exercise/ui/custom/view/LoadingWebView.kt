package yeungkc.com.gankio_for_android_proficiency_exercise.ui.custom.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.ProgressBar


class LoadingWebView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : WebView(context, attrs, defStyleAttr) {
    private var mProgressBar: ProgressBar? = null

    init {
        settings.javaScriptCanOpenWindowsAutomatically = true //支持通过Javascript打开新窗口
        settings.javaScriptEnabled = true //设置WebView属性，能够执行Javascript脚本
        settings.useWideViewPort = true //将图片调整到适合webview的大小
        settings.loadWithOverviewMode = true // 缩放至屏幕的大小
        settings.domStorageEnabled = true //设置是否启用了DOM Storage API

        setWebViewClient(object : WebViewClient() {
            /**
             * 点击链接不调用外部浏览器
             */
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                loadUrl(request.url.toString())
                return true
            }
        })
    }


    fun load(url: String) {
        super.loadUrl(url)
    }

    fun addProgressBar() {
        mProgressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)

        mProgressBar!!.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 8, 0f)
        addView(mProgressBar)

        setWebChromeClient(WebChromeClient())
    }

    inner class WebChromeClient : android.webkit.WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress == 100) {
                mProgressBar!!.visibility = View.GONE
            } else {
                if (mProgressBar!!.visibility == View.GONE)
                    mProgressBar!!.visibility = View.VISIBLE
                mProgressBar!!.progress = newProgress
            }
            super.onProgressChanged(view, newProgress)
        }
    }

    fun destroyWebView() {
        clearCache(true)
        clearHistory()
    }
}