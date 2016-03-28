package com.brainbox.student.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.brainbox.student.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin-PC on 2/23/2016.
 */
public class AboutUsActivity extends AppCompatActivity
{

	@Bind(R.id.toolbar)
	Toolbar toolbar;
	@Bind(R.id.webView)
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_policies);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("About Us");

		webView.getSettings().setJavaScriptEnabled(true); // enable javascript

		final Activity activity = this;

		webView.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
			}
		});

		webView.loadUrl("file:///android_asset/about_us.html");


		toolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();

			}
		});
	}
}

