package com.brainbox.student.adapter;

/**
 * Created by adityaagrawal on 17/02/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.brainbox.student.R;
import com.brainbox.student.activities.SchoolDetailsActivity;
import com.brainbox.student.dto.FeedItem;
import com.brainbox.student.dto.SchoolListItemDTO;
import com.brainbox.student.global.AppController;
import com.brainbox.student.ui.CustomTypeFace;
import com.brainbox.student.ui.volley.FeedImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class SearchSchoolListAdapter extends BaseAdapter
{
	private Context context;
	private LayoutInflater inflater;
	private List<SchoolListItemDTO> schoolItems;
	private Typeface typeface, boldTypeface;
	private int width = 0;
	private ViewHolder holder;
	private Picasso picasso;

	public SearchSchoolListAdapter(Context context, List<SchoolListItemDTO> schoolItems)
	{
		this.context = context;
		this.schoolItems = schoolItems;
		typeface = CustomTypeFace.getTypeface(context);
		boldTypeface = CustomTypeFace.getBoldTypeface(context);
		DisplayMetrics metrics = new DisplayMetrics();
		((AppCompatActivity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		width = metrics.widthPixels;
		picasso = Picasso.with(context);
	}

	@Override
	public int getCount()
	{
		return schoolItems.size();
	}

	@Override
	public Object getItem(int location)
	{
		return schoolItems.get(location);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		if (inflater == null)
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.single_row_search_schools, null);
			holder = new ViewHolder();
			holder.txtSchoolName = (TextView) convertView.findViewById(R.id.txtSchoolName);
			holder.txtSchoolAddress = (TextView) convertView.findViewById(R.id.txtSchoolAddress);
			holder.imgSchool = (ImageView) convertView.findViewById(R.id.imgSchool);
			convertView.setTag(holder);

		}else{
			holder = (ViewHolder) convertView.getTag();
		}


		final SchoolListItemDTO item = schoolItems.get(position);

		holder.imgSchool.getLayoutParams().height = width / 2;
		holder.imgSchool.getLayoutParams().width = width;
		holder.imgSchool.setScaleType(ImageView.ScaleType.FIT_XY);

		holder.imgSchool.requestLayout();
		holder.txtSchoolName.setTypeface(boldTypeface);
		holder.txtSchoolAddress.setTypeface(typeface);
		holder.txtSchoolName.setText(item.getName());
		holder.txtSchoolAddress.setText(item.getAddress());
		picasso.load(item.getImage()).error(R.drawable.ic_launcher).into(holder.imgSchool, new Callback()
		{
			@Override
			public void onSuccess()
			{
				holder.imgSchool.requestLayout();
			}

			@Override
			public void onError()
			{

			}
		});

		convertView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Bundle bundle = new Bundle();
				bundle.putString("school", new Gson().toJson(item));
				Intent intent = new Intent(context, SchoolDetailsActivity.class);
				intent.putExtras(bundle);
				context.startActivity(intent);
			}
		});
		return convertView;
	}

	class ViewHolder
	{
		private TextView txtSchoolName;
		private TextView txtSchoolAddress;
		private ImageView imgSchool;
	}

}