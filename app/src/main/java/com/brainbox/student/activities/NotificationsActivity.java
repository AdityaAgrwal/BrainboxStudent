package com.brainbox.student.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.brainbox.student.R;
import com.brainbox.student.ui.CustomTitle;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity
{
	@Bind(R.id.txtNoNotifications)
	TextView txtNoNotifications;
	@Bind(R.id.txtNoNotificationsDisc)
	TextView txtNoNotificationsDisc;
	@Bind(R.id.toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);

		populate();
	}

	private void populate(){
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(CustomTitle.getTitle(this, getString(R.string.notifications)));
		toolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onBackPressed();

			}
		});

		txtNoNotifications.setText(CustomTitle.getTitle(this, getString(R.string.no_notifications)));
		txtNoNotificationsDisc.setText(CustomTitle.getPlainTitle(this, getString(R.string.no_notifications_disc)));
	}

}
