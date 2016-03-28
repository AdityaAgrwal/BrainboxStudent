package com.brainbox.student.dashboard_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.brainbox.student.R;
import com.brainbox.student.adapter.LibraryListAdapter;
import com.brainbox.student.dto.LibraryItemDTO;
import com.brainbox.student.global.BrainBox;
import com.nirhart.parallaxscroll.views.ParallaxListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by adityaagrawal on 18/02/16.
 */
public class ELibraryFragment extends Fragment
{

	private View parentView;
	@Bind(R.id.listLibrary)
	ParallaxListView listLibrary;
	@Bind(R.id.loadingView)
	View loadingView;
	List<LibraryItemDTO> libraryItemDTOs;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		parentView = inflater.inflate(R.layout.fragment_library, container, false);
		//  setHasOptionsMenu(true);
		ButterKnife.bind(this, parentView);
		BrainBox.currentFragment = this;
		populate();
		return parentView;
	}

	private void populate()
	{

		loadingView.setVisibility(View.GONE);
		libraryItemDTOs = new ArrayList<>();

		LibraryItemDTO libraryItemDTO = new LibraryItemDTO();
		libraryItemDTO.setName("New Vishal's Plant Pathology\nBy - K.S. Hooda");
		libraryItemDTO.setImageLink(R.drawable.book);
		libraryItemDTO.setLink("http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf");

		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);
		libraryItemDTOs.add(libraryItemDTO);

/*
		TextView v = new TextView(getActivity());
        v.setText("E - Library");
        v.setGravity(Gravity.CENTER);
        v.setTextSize(30);
        v.setTextColor(Color.WHITE);
        v.setHeight(350);
        v.setBackgroundResource(R.drawable.school);


        listLibrary.addParallaxedHeaderView(v);*/

		LibraryListAdapter libraryListAdapter = new LibraryListAdapter(getActivity(), libraryItemDTOs);
		listLibrary.setAdapter(libraryListAdapter);
	}

   /* @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pdf_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == R.id.action_notifications){

		} else if(item.getItemId() == R.id.action_search){

		}
		return super.onOptionsItemSelected(item);
	}*/
}
