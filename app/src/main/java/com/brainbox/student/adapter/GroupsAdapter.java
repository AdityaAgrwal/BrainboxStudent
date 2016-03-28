package com.brainbox.student.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brainbox.student.R;
import com.brainbox.student.activities.PDFDisplayActivity;
import com.brainbox.student.dto.GroupItemDTO;
import com.brainbox.student.dto.LibraryItemDTO;
import com.brainbox.student.ui.CustomTypeFace;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Admin-PC on 2/19/2016.
 */
public class GroupsAdapter extends BaseAdapter
{
	private Context context;
	private LayoutInflater inflater;
	private List<GroupItemDTO> groupItemDTOs;
	private Typeface typeface;

	public GroupsAdapter(Context context, List<GroupItemDTO> groupItemDTOs)
	{
		this.context = context;
		this.groupItemDTOs = groupItemDTOs;
		typeface = CustomTypeFace.getTypeface(context);
	}

	@Override
	public int getCount()
	{
		return groupItemDTOs.size();
	}

	@Override
	public Object getItem(int location)
	{
		return groupItemDTOs.get(location);
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
			convertView = inflater.inflate(R.layout.single_row_groups_list, null);


		TextView bookName = (TextView) convertView.findViewById(R.id.txtGroupName);
		bookName.setTypeface(typeface);

		TextView disc = (TextView) convertView.findViewById(R.id.txtGroupDisc);
		disc.setTypeface(typeface);

		ImageView imgBook = (ImageView) convertView.findViewById(R.id.imgGroup);


		final GroupItemDTO item = groupItemDTOs.get(position);
		bookName.setText(item.getName());
		imgBook.setImageResource(item.getImageLink());
		disc.setText(item.getDisc());

		convertView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

			}
		});

		return convertView;
	}


}