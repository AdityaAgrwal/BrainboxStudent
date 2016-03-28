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
import android.widget.TextView;

import com.brainbox.student.R;
import com.brainbox.student.activities.NotificationsActivity;
import com.brainbox.student.global.BrainBox;
import com.brainbox.student.ui.CustomTitle;
import com.brainbox.student.ui.button.ButtonPlus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin-PC on 2/19/2016.
 */
public class LostNFoundFragment extends Fragment
{
	private View parentView;
	@Bind(R.id.btnRefresh)
	ButtonPlus btnRefresh;
	@Bind(R.id.txtNoLostnFound)
	TextView txtNoLostnFound;
	@Bind(R.id.txtNoLostnFoundDisc)
	TextView txtNoLostnFoundDisc;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		parentView = inflater.inflate(R.layout.fragment_lost_n_found, container, false);
		BrainBox.currentFragment = this;
	//	setHasOptionsMenu(true);
		populate();
		return parentView;
	}

	private void populate(){
		ButterKnife.bind(this, parentView);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			btnRefresh.setBackgroundResource(R.drawable.abc_alpha_btn_background_ripple);
		}

		txtNoLostnFound.setText(CustomTitle.getTitle(getActivity(), getString(R.string.no_lost_n_found)));
		txtNoLostnFoundDisc.setText(CustomTitle.getPlainTitle(getActivity(), getString(R.string.no_lost_n_found_disc)));
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
