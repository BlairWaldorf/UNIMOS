package com.jefferson_santos.unimos;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    ProgressDialog progressBar;
    SwipeRefreshLayout refresh;
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

//        checking connection before start
        if (!isConnected(getApplicationContext())){
            Intent intent = new Intent(this, ErrorActivity.class);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
            refresh = findViewById(R.id.refresh);
            progressBar = new ProgressDialog(MainActivity.this);
            progressBar.setMessage("Carregando...");

//        webview
            webView = findViewById(R.id.webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setInitialScale(1);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setAppCacheEnabled(false);
            webView.setWebChromeClient(new WebChromeClient());
            webView.setClickable(true);
            webView.setFocusableInTouchMode(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setSupportMultipleWindows(true);

            webView.loadUrl("http://unimos.ddns.com.br:9000/unimos/");
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    if (!progressBar.isShowing()) {
                        progressBar.show();
                    }
                }

                public void onPageFinished(WebView view, String url) {
                    if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }
                }

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }
                }
            });

            refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    /**Set Refreshing to True**/
                    refresh.setRefreshing(true);

                    //Write Your Code to Refresh any View
//                    webView.loadUrl(webView.getUrl());
                    webView.reload();

                    /**Below Code will not execute for 2000 Millisecond (i.e 2 seconds)**/
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(MainActivity.this, "Refresh Completed", Toast.LENGTH_SHORT).show();

                            /**Set Refreshing to False**/
                            refresh.setRefreshing(false);
                        }
                    }, 2000);
                }
            });

        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if (!isConnected(getApplicationContext())){
            return;
        }else {
            refresh.getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener =
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if (webView.getScrollY() == 0)
                            refresh.setEnabled(true);
                        else
                            refresh.setEnabled(false);
                    }
                });
            }
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (!isConnected(getApplicationContext())){
            return;
        }else {
            refresh.getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
        }
    }

    /**
     * Check if there is any connectivity
     *
     * @param context
     * @return is Device Connected
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != cm) {
            NetworkInfo info = cm.getActiveNetworkInfo();

            return (info != null && info.isConnected());
        }
        return false;
    }
}
