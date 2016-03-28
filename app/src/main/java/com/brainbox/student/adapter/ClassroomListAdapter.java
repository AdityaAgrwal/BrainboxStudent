package com.brainbox.student.adapter;

/**
 * Created by adityaagrawal on 17/02/16.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brainbox.student.R;
import com.brainbox.student.dto.ClassroomListDTO;
import com.brainbox.student.ui.CustomTypeFace;

import java.util.List;

public class ClassroomListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<ClassroomListDTO> classroomListDTOs;
    private Typeface typeface;


    public ClassroomListAdapter(Context context, List<ClassroomListDTO> classroomListDTOs) {
        this.context = context;
        this.classroomListDTOs = classroomListDTOs;
        typeface = CustomTypeFace.getTypeface(context);
    }

    @Override
    public int getCount() {
        return classroomListDTOs.size();
    }

    @Override
    public Object getItem(int location) {
        return classroomListDTOs.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ClassroomListDTO item = classroomListDTOs.get(position);

        if(item.getIsHeading()){
            convertView = inflater.inflate(R.layout.single_row_classroom_heading, null);
        }else{
            convertView = inflater.inflate(R.layout.single_row_classroom_value, null);
            TextView txtValue = (TextView) convertView.findViewById(R.id.txtClassroomValue);
            txtValue.setTypeface(typeface);
            txtValue.setText(item.getText());
        }


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }

}