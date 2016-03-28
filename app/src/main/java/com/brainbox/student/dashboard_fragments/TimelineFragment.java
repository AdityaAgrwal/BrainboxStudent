package com.brainbox.student.dashboard_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.brainbox.student.MainActivity;
import com.brainbox.student.R;
import com.brainbox.student.adapter.FeedListAdapter;
import com.brainbox.student.adapter.TimelineListAdapter;
import com.brainbox.student.dto.FeedItem;
import com.brainbox.student.dto.TimelineItemDTO;
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
 * Created by Admin-PC on 2/20/2016.
 */
public class TimelineFragment extends Fragment
{

	private View parentView;
	@Bind(R.id.listTimeline)
	ParallaxListView listTimeline;
	private static final String TAG = TimelineFragment.class.getSimpleName();
	private TimelineListAdapter listAdapter;
	private List<TimelineItemDTO> timelineItems;
	private String URL_FEED = "http://104.236.79.35/api/Schools/demo";
	@Bind(R.id.loadingView)
	View loadingView;
	@Bind(R.id.emptyView)
	View emptyView;
	@Bind(R.id.errorView)
	View errorView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		parentView = inflater.inflate(R.layout.fragment_timeline, container, false);
		ButterKnife.bind(this, parentView);
		BrainBox.currentFragment = this;
		populate();
		return parentView;
	}


	private void populate(){
		errorView.setVisibility(View.GONE);
		listTimeline.setVisibility(View.GONE);
		loadingView.setVisibility(View.VISIBLE);
		emptyView.setVisibility(View.GONE);

		errorView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				populate();
			}
		});


		timelineItems = new ArrayList<>();
		listAdapter = new TimelineListAdapter(getActivity(), timelineItems);
		listTimeline.setAdapter(listAdapter);
		Cache cache = AppController.getInstance().getRequestQueue().getCache();
		Cache.Entry entry = cache.get(URL_FEED);
		if (entry != null) {
			try {
				String data = new String(entry.data, "UTF-8");
				try {
					parseJsonFeed(new JSONObject(data));
				} catch (JSONException e) {
					e.printStackTrace();
					handleException();
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				handleException();
			}

		} else {
			JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, URL_FEED, null, new Response.Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject response) {
					VolleyLog.d(TAG, "Response: " + response.toString());
					if (response != null) {
						parseJsonFeed(response);
					}
				}
			}, new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					VolleyLog.d(TAG, "Error: " + error.getMessage() + error.toString());
					error.printStackTrace();
					handleException();
				}
			});

			AppController.getInstance().addToRequestQueue(jsonReq);
		}
	}


	private void parseJsonFeed(JSONObject response) {
		try {
			response = response.getJSONObject("res");
			JSONArray feedArray = response.getJSONArray("feed");

			for (int i = 0; i < feedArray.length(); i++) {
				JSONObject feedObj = (JSONObject) feedArray.get(i);

				TimelineItemDTO item = new TimelineItemDTO();
				item.setName(feedObj.getString("name"));
				item.setImage(feedObj.getString("image"));
				item.setDisc(feedObj.getString("disc"));
				item.setProfilePic(feedObj.getString("profilePic"));
				timelineItems.add(item);
			}

			listAdapter.notifyDataSetChanged();

			listTimeline.setVisibility(View.VISIBLE);
			loadingView.setVisibility(View.GONE);

			if(timelineItems.size() == 0){
				listTimeline.setVisibility(View.GONE);
				loadingView.setVisibility(View.GONE);
				errorView.setVisibility(View.GONE);
				emptyView.setVisibility(View.VISIBLE);
			}else{
				errorView.setVisibility(View.GONE);
				listTimeline.setVisibility(View.VISIBLE);
				loadingView.setVisibility(View.GONE);
				emptyView.setVisibility(View.GONE);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			handleException();
		}
	}
	private void handleException()
	{
		if(timelineItems.size() == 0){
			errorView.setVisibility(View.VISIBLE);
			listTimeline.setVisibility(View.GONE);
			loadingView.setVisibility(View.GONE);
			emptyView.setVisibility(View.GONE);
			Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
		}
	}
}
