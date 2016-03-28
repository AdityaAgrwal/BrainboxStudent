package com.brainbox.student.dashboard_fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.brainbox.student.R;
import com.brainbox.student.activities.NotificationsActivity;
import com.brainbox.student.global.BrainBox;
import com.brainbox.student.ui.button.ButtonPlus;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * Created by Admin-PC on 2/19/2016.
 */
public class FeedbackFragment extends Fragment
{
	private View parentView;
	@Bind(R.id.spinnerFeedbackSubject)
	MaterialSpinner materialSpinner;
	@Bind(R.id.btnSubmitFeedback)
	ButtonPlus btnSubmitFeedback;
	private String[] subjects;




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		parentView = inflater.inflate(R.layout.fragment_feedback, container, false);
	//	setHasOptionsMenu(true);
		BrainBox.currentFragment = this;
		populate();
		return parentView;
	}

	private void populate(){
		ButterKnife.bind(this, parentView);
		subjects = getActivity().getResources().getStringArray(R.array.reasons_absent);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			btnSubmitFeedback.setBackgroundResource(R.drawable.abc_alpha_btn_background_ripple);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, subjects);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		materialSpinner.setAdapter(adapter);
	}

/*	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_dashboard, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == R.id.action_notifications){
			Intent intent = new Intent(getActivity(), NotificationsActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}*/
}
