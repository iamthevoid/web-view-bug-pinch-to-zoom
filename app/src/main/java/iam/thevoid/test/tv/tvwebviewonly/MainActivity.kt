package iam.thevoid.test.tv.tvwebviewonly

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<WebView>(R.id.web_view)?.apply {
            with(settings) {
                javaScriptEnabled = true
            }

            webViewClient = WebViewClient()
        }?.also { webView ->
            webView.loadUrl("https://tradingview.com/chart/?mobileapp_new=true")
        }
        println("web view version = ${wvVersion()}")
    }

    private fun Context.wvVersion() : String {
        val pm = packageManager
        return try {
            val pi = pm.getPackageInfo("com.google.android.webview", 0)
            "name: ${pi.versionName}, code: ${pi.versionCode}"
        } catch (e: PackageManager.NameNotFoundException) {
            "Not found"
        }
    }

}