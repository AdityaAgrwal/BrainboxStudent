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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;import android.widget.Toast;

import com.brainbox.student.R;
import com.brainbox.student.adapter.SearchSchoolListAdapter;
import com.brainbox.student.adapter.SearchSchoolSuggestionAdapter;
import com.brainbox.student.dashboard.SearchSchoolList;
import com.brainbox.student.dto.SchoolListItemDTO;
import com.brainbox.student.network.SchoolSuggestion;
import com.brainbox.student.ui.CustomTitle;
import com.brainbox.student.ui.SnackBar;
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
    private SearchSchoolSuggestionAdapter simpleCursorAdapter;

    private SearchView searchView = null;
   // private SimpleCursorAdapter simpleCursorAdapter;


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
//		simpleCursorAdapter = new SearchSchoolSuggestionAdapter(this, R.layout.single_row_search_school_suggestion, null, columns, null, -1000);

        simpleCursorAdapter = new SearchSchoolSuggestionAdapter(this);
		toolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

		SearchSchoolListAdapter searchSchoolListAdapter = new SearchSchoolListAdapter(this, schoolListItemDTOs);
		listSearchSchools.setAdapter(searchSchoolListAdapter);
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
                if(query.length() > 0)
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

}
