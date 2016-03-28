package com.brainbox.student.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.brainbox.student.R;
import com.brainbox.student.adapter.ProfileRecyclerAdapter;
import com.brainbox.student.models.ProfileModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin-PC on 2/19/2016.
 */
public class ProfileActivity extends AppCompatActivity
{
	@Bind(R.id.collapsing_toolbar)
	CollapsingToolbarLayout collapsingToolbar;
	@Bind(R.id.header)
	ImageView header;
	@Bind(R.id.scrollableview)
	RecyclerView recyclerView;
	int mutedColor = R.attr.colorPrimary;
	ProfileRecyclerAdapter profileRecyclerAdapter;
	@Bind(R.id.anim_toolbar)
	Toolbar toolbar;

	private static final int CAMERA_REQUEST = 1888;

	@OnClick(R.id.btnPickImage)
	void pickImage()
	{
		new MaterialDialog.Builder(this)
				.title("Pick Image")
				.items(R.array.pick_image)
				.itemsCallback(new MaterialDialog.ListCallback()
				{
					@Override
					public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text)
					{
						if (which == 0)
						{
							Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
							startActivityForResult(cameraIntent, CAMERA_REQUEST);
						} else
						{
							Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
							startActivityForResult(pickPhoto, 1);
						}
					}
				})
				.show();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
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

		collapsingToolbar.setTitle("Gouri Agrawal");
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.header);

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

		//	List<String> listData = new ArrayList<String>();

		if (profileRecyclerAdapter == null)
		{
			profileRecyclerAdapter = new ProfileRecyclerAdapter(this, ProfileModel.data, ProfileModel.values);
			recyclerView.setAdapter(profileRecyclerAdapter);
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
	{
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
		switch (requestCode)
		{
			case CAMERA_REQUEST:
				if (resultCode == RESULT_OK)
				{
					Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
					header.setImageBitmap(photo);
				}

				break;
			case 1:
				if (resultCode == RESULT_OK)
				{
					Uri selectedImage = imageReturnedIntent.getData();
					header.setImageURI(selectedImage);
				}
				break;
		}
	}
}
