package com.brainbox.student.dashboard_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brainbox.student.R;
import com.brainbox.student.adapter.ClassroomListAdapter;
import com.brainbox.student.dashboard.ClassroomList;
import com.brainbox.student.dto.ClassroomListDTO;
import com.brainbox.student.global.BrainBox;
import com.nirhart.parallaxscroll.views.ParallaxListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adityaagrawal on 18/02/16.
 */
public class ClassroomFragment extends Fragment{
    private View parentView;
    @Bind(R.id.listClassroom)
    ParallaxListView listClassroom;
    List<ClassroomListDTO> classroomListDTOs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView =  inflater.inflate(R.layout.fragment_classroom, container, false);
        ButterKnife.bind(this, parentView);
        BrainBox.currentFragment = this;
        populate();
        return parentView;
    }


    private void populate(){

       /* TextView v = new TextView(getActivity());
        v.setText("Classroom");
        v.setGravity(Gravity.CENTER);
        v.setTextSize(30);
        v.setTextColor(Color.WHITE);
        v.setHeight(350);
        v.setBackgroundResource(R.drawable.classroom);
*/
        classroomListDTOs = ClassroomList.getClassroomList();

     //   listClassroom.addParallaxedHeaderView(v);

        ClassroomListAdapter classroomListAdapter = new ClassroomListAdapter(getActivity(), classroomListDTOs);
        listClassroom.setAdapter(classroomListAdapter);
    }
}
