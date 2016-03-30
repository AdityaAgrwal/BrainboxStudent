package com.brainbox.student.network;

import android.content.Context;
import android.database.MatrixCursor;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.brainbox.student.activities.DashboardActivity;
import com.brainbox.student.adapter.SearchSchoolSuggestionAdapter;
import com.brainbox.student.constants.NetworkContants;
import com.brainbox.student.dto.ErrorDTO;
import com.brainbox.student.dto.SearchSchoolDTO;
import com.brainbox.student.dto.SessionDTO;
import com.brainbox.student.global.AppController;
import com.brainbox.student.ui.Dialog;
import com.brainbox.student.ui.GetProgressBar;
import com.brainbox.student.ui.SnackBar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * Created by smpx-imac1 on 28/03/16.
 */
public class SchoolSuggestion implements NetworkContants{
    boolean success = true;

    public boolean search(final Context context, String query , final MatrixCursor cursor, final SearchSchoolSuggestionAdapter simpleCursorAdapter) throws Exception {

        String url = IP + SEARCH_SCHOOL;
        url = url.replaceFirst("QUERY", URLEncoder.encode(query , "UTF-8"));
        final Gson gson = new Gson();
        JSONObject params = new JSONObject();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("response");
                    JSONArray names = jsonObject.getJSONArray("name");
                    JSONArray locations = jsonObject.getJSONArray("location");

                    for (int i = 0 ; i < names.length() ; i++){
                        SearchSchoolDTO searchSchoolDTO = gson.fromJson(names.get(i).toString(), SearchSchoolDTO.class);
                        cursor.addRow(new Object[] {i , searchSchoolDTO.getName() , searchSchoolDTO.getLocation() , searchSchoolDTO.getId()});
                    }
                    for (int i = 0 ; i < locations.length() ; i++){
                        SearchSchoolDTO searchSchoolDTO = gson.fromJson(locations.get(i).toString(), SearchSchoolDTO.class);
                        cursor.addRow(new Object[] {i , searchSchoolDTO.getName() , searchSchoolDTO.getLocation() , searchSchoolDTO.getId()});
                    }
                    simpleCursorAdapter.changeCursor(cursor);
                } catch (Exception e) {
                    success = false;
                    e.printStackTrace();
                    SnackBar.showSimple(context, e.toString());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError || error instanceof NetworkError) {
                    success = false;
                    error.printStackTrace();
                    SnackBar.showSimple(context, error.toString());
                    return;
                }

                try {
                    success = false;
                    String errorString = new String(error.networkResponse.data);
                    JSONObject jsonObject = new JSONObject(errorString);
                    ErrorDTO errorDTO = gson.fromJson(jsonObject.getString("error"), ErrorDTO.class);
                    SnackBar.showSimple(context, errorDTO.getMessage());
                } catch (Exception e) {
                    success = false;
                    e.printStackTrace();
                    SnackBar.showSimple(context, e.getMessage());
                }

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, AppController.JSON_OBJECT_REQUEST);
        return !success;
    }
}
