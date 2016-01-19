package com.rns.tiffeatwebapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {

	private WebView webView;
	private int count;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (!Validation.isNetworkAvailable(MainActivity.this)) {
			Validation.showError(MainActivity.this, "No Internet connection");

		} else {
			webView = (WebView) findViewById(R.id.tiffeat_webview);
			webView.clearCache(true);
			webView.clearHistory();
			webView.getSettings().setJavaScriptEnabled(true);     
			webView.getSettings().setLoadWithOverviewMode(true);
			webView.getSettings().setUseWideViewPort(true); 

			webView.loadUrl("http://tiffeat.com/home.htm");

			WebViewClient webViewClient = new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					view.loadUrl(url);
					return true;

				}

				@Override
				public void onLoadResource(WebView view, String url) {
					if (!Validation.isNetworkAvailable(MainActivity.this)) {
						Validation.showError(MainActivity.this, "No Internet connection");
					}
				}
			};



			if (Build.VERSION.SDK_INT >= 11){
				webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
			}

			webView.getSettings().setRenderPriority(RenderPriority.HIGH);
			webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

			webView.setWebViewClient(webViewClient);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if(event.getAction() == KeyEvent.ACTION_DOWN){
			switch(keyCode)
			{
			case KeyEvent.KEYCODE_BACK:
				if(webView.canGoBack() == true){
					webView.goBack();
				}else{
					count++;
					if(count==1)
					{
						Toast.makeText(MainActivity.this, "Press one more time to Exit", Toast.LENGTH_SHORT).show();
					}
					else if(count==2)
						finish();
				}
				return true;
			}

		}
		return super.onKeyDown(keyCode, event);
	}

}