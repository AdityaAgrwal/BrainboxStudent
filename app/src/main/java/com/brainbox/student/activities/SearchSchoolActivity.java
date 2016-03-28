package com.brainbox.student.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.brainbox.student.R;
import com.brainbox.student.adapter.LibraryListAdapter;
import com.brainbox.student.adapter.SearchSchoolListAdapter;
import com.brainbox.student.dashboard.SearchSchoolList;
import com.brainbox.student.dto.SchoolListItemDTO;
import com.brainbox.student.ui.CustomTitle;
import com.nirhart.parallaxscroll.views.ParallaxListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin-PC on 2/24/2016.
 */
public class SearchSchoolActivity extends AppCompatActivity
{
	@Bind(R.id.toolbar)
	Toolbar toolbar;
	@Bind(R.id.listSearchSchools)
	ParallaxListView listSearchSchools;
	private List<SchoolListItemDTO> schoolListItemDTOs;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_school);
		ButterKnife.bind(this);

		populate();
	}

	private void populate(){

		schoolListItemDTOs = SearchSchoolList.getSchoolList();

		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle(CustomTitle.getTitle(this, getResources().getString(R.string.schools)));


		toolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onBackPressed();
			}
		});

		SearchSchoolListAdapter searchSchoolListAdapter = new SearchSchoolListAdapter(this, schoolListItemDTOs);
		listSearchSchools.setAdapter(searchSchoolListAdapter);
	}
}
