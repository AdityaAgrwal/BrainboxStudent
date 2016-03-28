package com.brainbox.student.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.brainbox.student.R;
import com.brainbox.student.adapter.ProfileRecyclerAdapter;
import com.brainbox.student.adapter.SchoolDetailsRecyclerAdapter;
import com.brainbox.student.dto.SchoolListItemDTO;
import com.brainbox.student.models.ProfileModel;
import com.brainbox.student.ui.CustomTitle;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin-PC on 2/24/2016.
 */
public class SchoolDetailsActivity extends AppCompatActivity
{
	private SchoolListItemDTO schoolListItemDTO;
	@Bind(R.id.collapsing_toolbar)
	CollapsingToolbarLayout collapsingToolbar;
	@Bind(R.id.header)
	ImageView header;
	@Bind(R.id.scrollableview)
	RecyclerView recyclerView;
	int mutedColor = R.attr.colorPrimary;
	SchoolDetailsRecyclerAdapter schoolDetailsRecyclerAdapter;
	@Bind(R.id.anim_toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school_details);
		schoolListItemDTO = new Gson().fromJson(getIntent().getExtras().getString("school"), SchoolListItemDTO.class);

		ButterKnife.bind(this);
		populate();
	}

	private void populate()
	{
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onBackPressed();

			}
		});

		collapsingToolbar.setTitle(CustomTitle.getTitle(this, schoolListItemDTO.getName()));
		Picasso.with(this).load(schoolListItemDTO.getImage()).error(R.drawable.ic_launcher).into(header, new Callback()
		{
			@Override
			public void onSuccess()
			{
				Bitmap bitmap = ((BitmapDrawable) header.getDrawable()).getBitmap();
				Palette.from(bitmap).generate(new Palette.PaletteAsyncListener()
				{
					@SuppressWarnings("ResourceType")
					@Override
					public void onGenerated(Palette palette)
					{

						mutedColor = palette.getMutedColor(R.color.primary);
						collapsingToolbar.setContentScrimColor(mutedColor);
						collapsingToolbar.setStatusBarScrimColor(R.color.primary);
					}
				});
			}

			@Override
			public void onError()
			{

			}
		});
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);

		Palette.from(bitmap).generate(new Palette.PaletteAsyncListener()
		{
			@SuppressWarnings("ResourceType")
			@Override
			public void onGenerated(Palette palette)
			{

				mutedColor = palette.getMutedColor(R.color.primary);
				collapsingToolbar.setContentScrimColor(mutedColor);
				collapsingToolbar.setStatusBarScrimColor(R.color.primary);
			}
		});

		recyclerView.setHasFixedSize(true);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(linearLayoutManager);

		List<String> listData = new ArrayList<String>();
		List<String> listValues = new ArrayList<String>();

		listData.add("Admission Dates");
		listValues.add("11 Mar , 2016  -  1 July , 2016");

		listData.add("Admission Form");
		listValues.add("Tap here to download admission form.");

		listData.add("Pay for Admission");
		listValues.add("Tap here to pay for admission form.");

		listData.add("Tour");
		listValues.add("Take a special virtual tour of the school.");

		listData.add("About Us");
		listValues.add(schoolListItemDTO.getAboutUs());



		String[] data =  new String[listData.size()];
		listData.toArray(data);

		String[] values =  new String[listValues.size()];
		listValues.toArray(values);

		if (schoolDetailsRecyclerAdapter == null)
		{
			schoolDetailsRecyclerAdapter = new SchoolDetailsRecyclerAdapter(this, data, values);
			recyclerView.setAdapter(schoolDetailsRecyclerAdapter);
		}

	}
}
