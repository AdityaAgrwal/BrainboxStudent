package com.brainbox.student.dashboard_fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.brainbox.student.dto.FeedItem;
import com.brainbox.student.global.AppController;
import com.brainbox.student.global.BrainBox;
import com.brainbox.student.ui.CustomTitle;
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
 * Created by adityaagrawal on 17/02/16.
 */
public class NewsFeedFragment extends Fragment{
    private View parentView;
    @Bind(R.id.listNewsFeed)
    ParallaxListView listNewsFeed;
    private static final String TAG = MainActivity.class.getSimpleName();
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "http://api.androidhive.info/feed/feed.json";
    @Bind(R.id.loadingView)
    View loadingView;
	@Bind(R.id.emptyView)
	View emptyView;
	@Bind(R.id.errorView)
	View errorView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView =  inflater.inflate(R.layout.fragment_news_feed, container, false);
        ButterKnife.bind(this, parentView);
        BrainBox.currentFragment = this;

    //    setHasOptionsMenu(true);
        populate();
        return parentView;
    }

    private void populate() {

		errorView.setVisibility(View.GONE);
		listNewsFeed.setVisibility(View.GONE);
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
        int displayHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();

        TextView v = new TextView(getActivity());
        v.setText("Welcome !\nAditya Agrawal");
        v.setGravity(Gravity.CENTER);
        v.setTextSize(30);
        v.setTextColor(Color.WHITE);
        v.setHeight(displayHeight / 3);
        v.setBackgroundResource(R.drawable.school);

		if(listNewsFeed.getHeaderViewsCount() == 0)
        	listNewsFeed.addParallaxedHeaderView(v);

        feedItems = new ArrayList<>();
        listAdapter = new FeedListAdapter(getActivity(), feedItems);
        listNewsFeed.setAdapter(listAdapter);
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
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
					handleException();
                }
            });

            AppController.getInstance().addToRequestQueue(jsonReq);
        }
    }




	private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                String image = feedObj.isNull("image") ? null : feedObj.getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                String feedUrl = feedObj.isNull("url") ? null : feedObj.getString("url");
                item.setUrl(feedUrl);

                feedItems.add(item);
            }
			listAdapter.notifyDataSetChanged();
			listNewsFeed.setVisibility(View.VISIBLE);
			loadingView.setVisibility(View.GONE);

			if(feedItems.size() == 0){
				listNewsFeed.setVisibility(View.GONE);
				loadingView.setVisibility(View.GONE);
				errorView.setVisibility(View.GONE);
				emptyView.setVisibility(View.VISIBLE);
			}else{
				errorView.setVisibility(View.GONE);
				listNewsFeed.setVisibility(View.VISIBLE);
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
		if(feedItems.size() == 0){
			errorView.setVisibility(View.VISIBLE);
			listNewsFeed.setVisibility(View.GONE);
			loadingView.setVisibility(View.GONE);
			emptyView.setVisibility(View.GONE);
			Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
		}
	}


}
