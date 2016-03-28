package com.brainbox.student.dashboard_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brainbox.student.R;
import com.brainbox.student.global.BrainBox;
import com.brainbox.student.ui.NonSlidingViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adityaagrawal on 18/02/16.
 */
public class DashboardFragment extends Fragment
{

	/* @Bind(R.id.toolbar)
	 Toolbar toolbar;*/
	@Bind(R.id.viewpager)
	NonSlidingViewPager viewPager;
	@Bind(R.id.tab_layout)
	TabLayout tabLayout;
	private View parentView;
	String[] titles;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		parentView = inflater.inflate(R.layout.fragment_dashboard, container, false);
		titles = getResources().getStringArray(R.array.dashbaord);
		ButterKnife.bind(this, parentView);
		populate();
		BrainBox.currentFragment = this;
		return parentView;
	}

	private void populate()
	{

		setupViewPager(viewPager);
		viewPager.setPagingEnabled(false);
		tabLayout.setupWithViewPager(viewPager);

		viewPager.setOffscreenPageLimit(5);
		setupTabIcons();

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
				((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(titles[position]);
			}

			@Override
			public void onPageSelected(int position)
			{

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

			}
		});
	}


	private void setupViewPager(ViewPager viewPager)
	{
		ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
		adapter.addFragment(new NewsFeedFragment(), "News Feed");
		adapter.addFragment(new ELibraryFragment(), "E - Library");
		adapter.addFragment(new ClassroomFragment(), "Classroom");
		adapter.addFragment(new GalleryFragment(), "Gallery");
		adapter.addFragment(new TimelineFragment(), "Timeline");
		viewPager.setAdapter(adapter);
	}

	private void setupTabIcons()
	{
		tabLayout.getTabAt(0).setIcon(R.mipmap.ic_news_feed);
		tabLayout.getTabAt(1).setIcon(R.mipmap.ic_library);
		tabLayout.getTabAt(2).setIcon(R.mipmap.ic_classroom);
		tabLayout.getTabAt(3).setIcon(R.mipmap.ic_gallery);
		tabLayout.getTabAt(4).setIcon(R.mipmap.ic_profile);
	}

	class ViewPagerAdapter extends FragmentPagerAdapter
	{
		private final List<Fragment> mFragmentList = new ArrayList<>();
		private final List<String> mFragmentTitleList = new ArrayList<>();

		public ViewPagerAdapter(FragmentManager manager)
		{
			super(manager);
		}

		@Override
		public Fragment getItem(int position)
		{
			return mFragmentList.get(position);
		}

		@Override
		public int getCount()
		{
			return mFragmentList.size();
		}

		public void addFragment(Fragment fragment, String title)
		{
			mFragmentList.add(fragment);
			mFragmentTitleList.add(title);
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			return null;
		}


	}

/*
	@Override
    public void onResume() {
        super.onResume();
        populate();
    }*/

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);
		BrainBox.currentFragment = this;
	}
}
