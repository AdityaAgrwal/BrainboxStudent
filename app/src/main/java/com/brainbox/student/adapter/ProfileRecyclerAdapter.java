package com.brainbox.student.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brainbox.student.R;
import com.brainbox.student.ui.CustomTypeFace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin-PC on 2/20/2016.
 */
public class ProfileRecyclerAdapter extends RecyclerView.Adapter<ProfileRecyclerAdapter.VersionViewHolder> {
	// List<String> versionModels;
	Boolean isHomeList = false;

	public static List<String> homeActivitiesList = new ArrayList<String>();
	public static List<String> homeActivitiesSubList = new ArrayList<String>();
	Context context;
	OnItemClickListener clickListener;
	private Typeface typeface;
	private String[] values, data;

	public void setHomeActivitiesList(Context context) {
		String[] listArray = context.getResources().getStringArray(R.array.home_activities);
		String[] subTitleArray = context.getResources().getStringArray(R.array.home_activities_subtitle);
		for (int i = 0; i < listArray.length; ++i) {
			homeActivitiesList.add(listArray[i]);
			homeActivitiesSubList.add(subTitleArray[i]);
		}
		typeface = CustomTypeFace.getTypeface(context);
	}

	public ProfileRecyclerAdapter(Context context) {
		isHomeList = true;
		this.context = context;
		setHomeActivitiesList(context);
		// typeface = CustomTypeFace.getTypeface(context);
	}


	public ProfileRecyclerAdapter(Context context, String[] data, String[] values) {
		isHomeList = false;
		this.context = context;
		this.values = values;
		this.data = data;
		// this.versionModels = versionModels;
//		typeface = CustomTypeFace.getTypeface(context);
	}

	@Override
	public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerlist_item, viewGroup, false);
		VersionViewHolder viewHolder = new VersionViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
			versionViewHolder.title.setText(data[i]);
			versionViewHolder.subTitle.setText(values[i]);
	}

	@Override
	public int getItemCount() {
			return data.length;
	}


	class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		CardView cardItemLayout;
		TextView title;
		TextView subTitle;

		public VersionViewHolder(View itemView) {
			super(itemView);

			cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
			title = (TextView) itemView.findViewById(R.id.listitem_name);
			subTitle = (TextView) itemView.findViewById(R.id.listitem_subname);
			title.setTypeface(typeface);
			subTitle.setTypeface(typeface);
		}

		@Override
		public void onClick(View v) {
			clickListener.onItemClick(v, getPosition());
		}
	}

	public interface OnItemClickListener {
		public void onItemClick(View view, int position);
	}

	public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
		this.clickListener = itemClickListener;
	}

}