package com.brainbox.student.dashboard_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.brainbox.student.R;
import com.brainbox.student.activities.NotificationsActivity;
import com.brainbox.student.adapter.SettingsListAdapter;
import com.brainbox.student.dashboard.ClassroomList;
import com.brainbox.student.dashboard.SettingsList;
import com.brainbox.student.dto.ClassroomListDTO;
import com.brainbox.student.dto.SettingsListDTO;
import com.brainbox.student.global.BrainBox;
import com.nirhart.parallaxscroll.views.ParallaxListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adityaagrawal on 19/02/16.
 */
public class SettingsFragment extends Fragment {

    private View parentView;
    @Bind(R.id.listSettings)
    ParallaxListView listSettings;
    List<SettingsListDTO> settingsListDTOs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView =  inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, parentView);
        BrainBox.currentFragment = this;

        BrainBox.currentFragment = this;
        populate();
        return parentView;
    }

    private void populate(){
        settingsListDTOs = SettingsList.getSettingsList();

        SettingsListAdapter settingsListAdapter = new SettingsListAdapter(getActivity(), settingsListDTOs);
        listSettings.setAdapter(settingsListAdapter);
    }


}
