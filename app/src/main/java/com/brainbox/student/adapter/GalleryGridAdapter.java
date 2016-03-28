package com.brainbox.student.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.brainbox.student.R;
import com.brainbox.student.ui.CustomTypeFace;
import com.brainbox.student.ui.SquareImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by adityaagrawal on 20/02/16.
 */
public class GalleryGridAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] web;
    private final int[] Imageid;
    private Typeface typeface;
    private String url = "http://images.indianexpress.com/2015/08/virat-kohli-pti_m.jpg";
	private String url1 = "http://s3.india.com/wp-content/uploads/2014/04/sachin-tendulkar1.jpg";

    public GalleryGridAdapter(Context c, String[] web, int[] Imageid) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
        typeface = CustomTypeFace.getTypeface(c);
    }

    @Override
    public int getCount() {
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = inflater.inflate(R.layout.grid_album_single_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            SquareImageView imageView = (SquareImageView) grid.findViewById(R.id.grid_image);
            textView.setText(web[position]);
			if(position % 2 == 0)
				Picasso.with(mContext).load(url1).error(R.drawable.ic_launcher1).into(imageView);
            else
				Picasso.with(mContext).load(url).error(R.drawable.ic_launcher1).into(imageView);
			textView.setTypeface(typeface);
        } else {
            grid =  convertView;
        }

        return grid;
    }
}