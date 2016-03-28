package com.brainbox.student.dashboard_fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brainbox.student.R;
import com.brainbox.student.global.BrainBox;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalenderFragment extends Fragment {
    private View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_calender, container, false);
        BrainBox.currentFragment = this;
        return parentView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BrainBox.currentFragment = this;
    }

}
