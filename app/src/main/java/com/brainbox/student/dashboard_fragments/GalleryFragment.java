package com.brainbox.student.dashboard_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.brainbox.student.R;
import com.brainbox.student.adapter.GalleryGridAdapter;
import com.brainbox.student.global.BrainBox;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin-PC on 2/20/2016.
 */
public class GalleryFragment extends Fragment
{
	private View parentView;
	@Bind(R.id.gridAlbum)
	GridView gridAlbum;
	String[] web = {
			"New Year",
			"Valentines",
			"Birthday",
			"Sports",
			"Cricket",
			"Movie",
			"Quora",
			"Inauguration",
			"Start",
			"WordPress",
			"Youtube",
			"Stumbleupon",
			"SoundCloud",
			"Reddit",
			"Blogger"

	} ;
	int[] imageId = {
			R.drawable.image1,
			R.drawable.image2,
			R.drawable.image3,
			R.drawable.image1,
			R.drawable.image2,
			R.drawable.image3,
			R.drawable.image1,
			R.drawable.image2,
			R.drawable.image3,
			R.drawable.image1,
			R.drawable.image2,
			R.drawable.image3,
			R.drawable.image1,
			R.drawable.image2,
			R.drawable.image3

	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_gallery, container, false);
		ButterKnife.bind(this, parentView);
		BrainBox.currentFragment = this;

		populate();
		return parentView;
	}



	private void populate(){
		ButterKnife.bind(this, parentView);
		GalleryGridAdapter adapter = new GalleryGridAdapter(getActivity(), web, imageId);
		gridAlbum.setAdapter(adapter);

	}


}
