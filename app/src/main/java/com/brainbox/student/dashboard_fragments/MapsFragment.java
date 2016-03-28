package com.brainbox.student.dashboard_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.brainbox.student.R;
import com.brainbox.student.activities.NotificationsActivity;
import com.brainbox.student.global.BrainBox;
import com.brainbox.student.ui.CustomTitle;
import com.brainbox.student.util.CheckGooglePlayServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Admin-PC on 2/19/2016.
 */
public class MapsFragment extends Fragment
{
	private View parentView;
	static final LatLng latLang = new LatLng(28.5729 , 77.3923);

	private MapFragment mapFragment;
	GoogleMap googleMap;
	@Bind(R.id.txtNoPlayServices)
	TextView txtNoPlayServices;
	@Bind(R.id.txtNoPlayServicesDisc)
	TextView txtNoPlayServicesDisc;
	@Bind(R.id.frameMap)
	FrameLayout frameMap;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		BrainBox.currentFragment = this;
		parentView = inflater.inflate(R.layout.fragment_map, container, false);

		populate();

		return parentView;
	}

	private void populate(){
		ButterKnife.bind(this, parentView);

		if(CheckGooglePlayServices.isGooglePlayServicesAvailable(getActivity()))
		{
			try
			{
				if (googleMap == null)
				{
					mapFragment = ((MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map));
					googleMap = mapFragment.getMap();
				}

				googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				googleMap.addMarker(new MarkerOptions().position(latLang).title("Brain Box"));
				moveToCurrentLocation(latLang);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}else{
			frameMap.setVisibility(View.GONE);
			txtNoPlayServicesDisc.setVisibility(View.VISIBLE);
			txtNoPlayServices.setVisibility(View.VISIBLE);
			txtNoPlayServices.setText(CustomTitle.getTitle(getActivity(), getString(R.string.common_google_play_services_notification_ticker)));
			txtNoPlayServicesDisc.setText(CustomTitle.getPlainTitle(getActivity(), getString(R.string.common_google_play_services_install_text_phone)));
		}
	}

	private void moveToCurrentLocation(LatLng currentLocation)
	{
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
		googleMap.animateCamera(CameraUpdateFactory.zoomIn());
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


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

	/*@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (this.mapFragment != null && getFragmentManager().findFragmentById(this.mapFragment.getId()) != null)
		{
			FragmentManager fm = getFragmentManager();
			Fragment fragment = (fm.findFragmentById(R.id.map));
			FragmentTransaction ft = fm.beginTransaction();
			ft.remove(fragment);
			ft.commitAllowingStateLoss();
		}
	}*/
}
