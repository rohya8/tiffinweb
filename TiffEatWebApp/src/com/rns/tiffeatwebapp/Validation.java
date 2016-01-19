package com.rns.tiffeatwebapp;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Validation {

	public static void showError(Context context, String title) {
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.networkconnection);
		dialog.setTitle(title);
		dialog.setCancelable(false);
		Button dialogButton = (Button) dialog.findViewById(R.id.check_network_button);
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});

		dialog.show();

	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager obj = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo objInfo = obj.getActiveNetworkInfo();
		return objInfo != null && objInfo.isConnected();
	}

}