package com.brainbox.student.dashboard_fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.brainbox.student.MainActivity;
import com.brainbox.student.R;
import com.brainbox.student.activities.NotificationsActivity;
import com.brainbox.student.adapter.FeedListAdapter;
import com.brainbox.student.adapter.GroupsAdapter;
import com.brainbox.student.dto.FeedItem;
import com.brainbox.student.dto.GroupItemDTO;
import com.brainbox.student.dto.LibraryItemDTO;
import com.brainbox.student.global.AppController;
import com.brainbox.student.global.BrainBox;
import com.nirhart.parallaxscroll.views.ParallaxListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin-PC on 2/19/2016.
 */
public class EventsFragment extends Fragment
{
	private View parentView;
	@Bind(R.id.listEvents)
	ParallaxListView listEvents;
	private static final String TAG = MainActivity.class.getSimpleName();
	private GroupsAdapter groupsAdapter;
	private List<GroupItemDTO> groupItemDTOs;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		parentView =  inflater.inflate(R.layout.fragment_events, container, false);
	//	setHasOptionsMenu(true);
		ButterKnife.bind(this, parentView);
		BrainBox.currentFragment = this;

		populate();
		return parentView;
	}

	private void populate() {
		groupItemDTOs = new ArrayList<>();

		GroupItemDTO groupItemDTO = new GroupItemDTO();
		groupItemDTO.setName("Start UP Meet");
		groupItemDTO.setImageLink(R.drawable.startups);
		groupItemDTO.setDisc("New Delhi , India - 110017");

		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);
		groupItemDTOs.add(groupItemDTO);

		/*TextView v = new TextView(getActivity());
		v.setText("Welcome !\nAditya Agrawal");
		v.setGravity(Gravity.CENTER);
		v.setTextSize(30);
		v.setTextColor(Color.WHITE);
		v.setHeight(350);
		v.setBackgroundResource(R.drawable.school);

		listNewsFeed.addParallaxedHeaderView(v);*/

		groupsAdapter  = new GroupsAdapter(getActivity(), groupItemDTOs);
		listEvents.setAdapter(groupsAdapter);

	}

	/*@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_dashboard, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == R.id.action_notifications){
			Intent intent = new Intent(getActivity(), NotificationsActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}*/
}
