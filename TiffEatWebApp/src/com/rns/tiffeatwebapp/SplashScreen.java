package com.rns.tiffeatwebapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		if (!Validation.isNetworkAvailable(SplashScreen.this)) {
			Validation.showError(SplashScreen.this, "NO_INTERNET_CONNECTION");
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					final Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							finish();
						}
					}, 5000);
				}

			});

		} else {

			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					final Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							Intent i = new Intent(SplashScreen.this, MainActivity.class);
							startActivity(i);
							finish();
						}
					}, 1000);
				}

			});
		}

	}
}
