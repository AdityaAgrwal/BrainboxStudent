package com.brainbox.student.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.brainbox.student.R;
import com.brainbox.student.adapter.AppInfoSlidingImageAdapter;
import com.brainbox.student.ui.button.ButtonPlus;
import com.flyco.pageindicator.indicator.FlycoPageIndicaor;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin-PC on 2/23/2016.
 */
public class AppInfoActivity extends AppCompatActivity
{
	@Bind(R.id.indicator)
	FlycoPageIndicaor indicator;
	@Bind(R.id.pager)
	ViewPager pager;
	@OnClick(R.id.btnStart) void login(){
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_info);

		populate();
	}

	private void populate(){
		ButterKnife.bind(this);


		AppInfoSlidingImageAdapter appInfoSlidingImageAdapter = new AppInfoSlidingImageAdapter(this);
		pager.setAdapter(appInfoSlidingImageAdapter);
		indicator.setViewPager(pager);

	}
}
