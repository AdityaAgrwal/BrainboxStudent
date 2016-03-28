package com.brainbox.student.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.telecom.Call;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by Admin-PC on 2/21/2016.
 */
public class CheckGooglePlayServices
{
	public static boolean isGooglePlayServicesAvailable(Context context){
		GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
		int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context);
		return resultCode == ConnectionResult.SUCCESS;
	}
}
