package com.brainbox.student.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.brainbox.student.R;
import com.brainbox.student.global.BrainBox;

/**
 * Created by Admin-PC on 2/22/2016.
 */
public class SplashActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Thread t = new Thread() {
			public void run() {
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					if(BrainBox.isLogin(SplashActivity.this)){
						Intent i = new Intent(SplashActivity.this, DashboardActivity.class);
						startActivity(i);
						finish();
					}else
					{
						Intent i = new Intent(SplashActivity.this, AppInfoActivity.class);
						startActivity(i);
						finish();
					}
				}

			}
		};
		t.start();
	}

	@Override
	public void onBackPressed() {

	}
}
