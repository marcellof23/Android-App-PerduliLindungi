package com.example.perludilindungi.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebViewClient
import android.os.Bundle
import com.example.perludilindungi.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.webview_news.*


class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_news)

        val url = intent.getStringExtra("URL_NAME")

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webpage.webViewClient = WebViewClient()

        // this will load the url of the website
        webpage.loadUrl(url.toString())

        // this will enable the javascript settings
        webpage.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        webpage.settings.setSupportZoom(true)
    }

    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webpage.canGoBack())
            webpage.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }
}
