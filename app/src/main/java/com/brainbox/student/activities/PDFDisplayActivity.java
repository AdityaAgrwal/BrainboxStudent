package com.brainbox.student.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.brainbox.student.R;
import com.brainbox.student.dto.LibraryItemDTO;
import com.brainbox.student.ui.CustomTitle;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PDFDisplayActivity extends AppCompatActivity
{

	@Bind(R.id.toolbar)
	Toolbar toolbar;
	@Bind(R.id.webView)
	WebView webView;
	LibraryItemDTO libraryItemDTO;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdfdisplay);

		libraryItemDTO = new Gson().fromJson(getIntent().getExtras().getString("item"), LibraryItemDTO.class);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(CustomTitle.getTitle(this, libraryItemDTO.getName()));
		webView.setWebViewClient(new MyWebViewClient());
		webView.getSettings().setJavaScriptEnabled(true); // enable javascript

		webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + libraryItemDTO.getLink());


		toolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onBackPressed();

			}
		});
	}

	private class MyWebViewClient extends WebViewClient
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			view.loadUrl(url);
			return false;
		}

	}


}
