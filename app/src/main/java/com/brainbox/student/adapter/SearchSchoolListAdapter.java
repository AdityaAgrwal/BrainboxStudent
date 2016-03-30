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
import com.brainbox.student.dto.SearchSchoolDTO;
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
	private List<SearchSchoolDTO> schoolItems;
	private Typeface typeface, boldTypeface;
	private int width = 0;
	private ViewHolder holder;
	private Picasso picasso;

	public SearchSchoolListAdapter(Context context, List<SearchSchoolDTO> schoolItems)
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
		View view = inflater.inflate(R.layout.single_row_search_schools, null);
		//holder. = new ViewHolder();
		TextView txtSchoolName = (TextView) view.findViewById(R.id.txtSchoolName);
		TextView txtSchoolAddress = (TextView) view.findViewById(R.id.txtSchoolAddress);
		final ImageView imgSchool = (ImageView) view.findViewById(R.id.imgSchool);
//		convertView.setTag(holder);

		final SearchSchoolDTO item = schoolItems.get(position);

		imgSchool.getLayoutParams().height = width / 2;
		imgSchool.getLayoutParams().width = width;
		imgSchool.setScaleType(ImageView.ScaleType.FIT_XY);

		 imgSchool.requestLayout();
		 txtSchoolName.setTypeface(boldTypeface);
		 txtSchoolAddress.setTypeface(typeface);
		 txtSchoolName.setText(item.getName());
		 txtSchoolAddress.setText(item.getAddress());
		 if(item.getImageLink() != null) {
			picasso.load(item.getImageLink()).error(R.drawable.ic_launcher).into(imgSchool, new Callback() {
				@Override
				public void onSuccess() {
					 imgSchool.requestLayout();
				}

				@Override
				public void onError() {

				}
			});
		}
		view.setOnClickListener(new View.OnClickListener()
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
		return view;
	}

	class ViewHolder
	{
		private TextView txtSchoolName;
		private TextView txtSchoolAddress;
		private ImageView imgSchool;
	}

}