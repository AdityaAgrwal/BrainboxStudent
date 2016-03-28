package com.brainbox.student.provider;

import android.content.Context;
import android.content.pm.PackageInfo;

/**
 * Created by Admin-PC on 2/23/2016.
 */
public class AppVersion
{
	public static String getVersionName(Context context)throws Exception{
		PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		String version = pInfo.versionName;
		return version;
	}
}
