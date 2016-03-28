package com.brainbox.student.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brainbox.student.R;
import com.brainbox.student.ui.CustomTypeFace;

/**
 * Created by smpx-imac1 on 28/03/16.
 */
public class SearchSchoolSuggestionAdapter extends SimpleCursorAdapter {

    private Context context=null;
    private Typeface typeface;

    public SearchSchoolSuggestionAdapter(Context context) {
        super(context, R.layout.single_row_search_school_suggestion, null, new String[] {"schoolName"}, new int[] {android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        this.context=context;
        typeface = CustomTypeFace.getTypeface(context);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView txtSchoolName=(TextView)view.findViewById(R.id.txtSchoolName);
        txtSchoolName.setTypeface(typeface);
      //  txtSchoolName.setText(cursor.getString(cursor.getColumnIndex("schoolName")));
        txtSchoolName.setText(cursor.getString(cursor.getColumnIndex("schoolName")) + "    ( " + cursor.getString(cursor.getColumnIndex("location")) + " )");

    }
}