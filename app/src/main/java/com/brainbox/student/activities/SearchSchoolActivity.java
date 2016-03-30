package com.brainbox.student.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.brainbox.student.R;
import com.brainbox.student.adapter.SearchSchoolListAdapter;
import com.brainbox.student.adapter.SearchSchoolSuggestionAdapter;
import com.brainbox.student.adapter.TimelineListAdapter;
import com.brainbox.student.constants.NetworkContants;
import com.brainbox.student.dashboard.SearchSchoolList;
import com.brainbox.student.dto.SchoolListItemDTO;
import com.brainbox.student.dto.SearchSchoolDTO;
import com.brainbox.student.dto.TimelineItemDTO;
import com.brainbox.student.global.AppController;
import com.brainbox.student.network.SchoolSuggestion;
import com.brainbox.student.ui.CustomTitle;
import com.brainbox.student.ui.SnackBar;
import com.google.gson.Gson;
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
 * Created by Admin-PC on 2/24/2016.
 */
public class SearchSchoolActivity extends AppCompatActivity implements NetworkContants , AbsListView.OnScrollListener
{
	@Bind(R.id.toolbar)
	Toolbar toolbar;
	@Bind(R.id.listSearchSchools)
	ParallaxListView listSearchSchools;
	private List<SearchSchoolDTO> schoolListItemDTOs;
    private SearchSchoolSuggestionAdapter simpleCursorAdapter;
    private static final String TAG = SearchSchoolActivity.class.getSimpleName();
    private SearchView searchView = null;
    @Bind(R.id.loadingView)
    View loadingView;
    @Bind(R.id.emptyView)
    View emptyView;
    @Bind(R.id.errorView)
    View errorView;
    private SearchSchoolListAdapter searchSchoolListAdapter;
    private String URL_FEED = "http://www.brainboxapp.com:3000/api/School/find?skip=SKIP&limit=LIMIT";
    private Gson gson = new Gson();
    private int preLast, skip = 0;
    private boolean isDataLoaded = false;
    private View footerView;


    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_school);
		ButterKnife.bind(this);

		populate();
	}

	private void populate() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.footer_view , null);

        listSearchSchools.setOnScrollListener(this);
        errorView.setVisibility(View.GONE);
        listSearchSchools.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populate();
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(CustomTitle.getTitle(this, getResources().getString(R.string.schools)));
//		simpleCursorAdapter = new SearchSchoolSuggestionAdapter(this, R.layout.single_row_search_school_suggestion, null, columns, null, -1000);

        simpleCursorAdapter = new SearchSchoolSuggestionAdapter(this);
		toolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        schoolListItemDTOs = new ArrayList<>();
        searchSchoolListAdapter = new SearchSchoolListAdapter(this, schoolListItemDTOs);
        listSearchSchools.setAdapter(searchSchoolListAdapter);
        loadData();
    }

    private void loadData() {

        String url = URL_FEED;
        url = url.replaceFirst("SKIP", String.valueOf(skip));
        url = url.replaceFirst("LIMIT", String.valueOf(3));
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

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


    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_school, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);


        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }


        searchView.setSuggestionsAdapter(simpleCursorAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0)
                    return populateAdapter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                populateAdapter(s);
                return false;
            }
        });

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
                String schoolName = cursor.getString(cursor.getColumnIndex("schoolName"));
                searchView.setQuery(schoolName, false);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
                String schoolName = cursor.getString(cursor.getColumnIndex("schoolName"));
                searchView.setQuery(schoolName, false);
                searchView.clearFocus();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private boolean populateAdapter(String query) {
        final MatrixCursor c = new MatrixCursor(new String[]{ BaseColumns._ID, "schoolName" , "location" , "id"});
        try {
            SchoolSuggestion schoolSuggestion = new SchoolSuggestion();
            return schoolSuggestion.search(SearchSchoolActivity.this , query , c, simpleCursorAdapter);
        } catch (Exception e) {
            e.printStackTrace();
            SnackBar.showSimple(SearchSchoolActivity.this, e.toString());
            return true;
        }
    }

    private void handleException()
    {
        if(schoolListItemDTOs.size() == 0){
            toolbar.setVisibility(View.VISIBLE);
            errorView.setVisibility(View.VISIBLE);
            listSearchSchools.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void parseJsonFeed(JSONObject response) {


        try {
            JSONArray feedArray = response.getJSONArray("response");

            if (feedArray.length() == 0) {
                searchSchoolListAdapter.notifyDataSetChanged();
                if (!isDataLoaded) {
                   SnackBar.showSimple(this , "No School Found");
                }

            }
            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                SearchSchoolDTO item = gson.fromJson(feedObj.toString() , SearchSchoolDTO.class);
                schoolListItemDTOs.add(item);
            }

            searchSchoolListAdapter .notifyDataSetChanged();

            listSearchSchools.requestLayout();
            skip = skip + feedArray.length();

            isDataLoaded = true;

            listSearchSchools.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.GONE);

            if(schoolListItemDTOs.size() == 0){
                listSearchSchools.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                errorView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            }else{
                errorView.setVisibility(View.GONE);
                listSearchSchools.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(!isDataLoaded)
                handleException();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        final int lastItem = firstVisibleItem + visibleItemCount;
        if (lastItem == totalItemCount) {
            if (preLast != lastItem) {
                SnackBar.success(this , getString(R.string.loading));
                loadData();
                preLast = lastItem;
            }
        }
    }
}
