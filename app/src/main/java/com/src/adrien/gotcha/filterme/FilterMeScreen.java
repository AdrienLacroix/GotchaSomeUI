package com.src.adrien.gotcha.filterme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.src.adrien.gotcha.R;
import com.src.adrien.gotcha.adapters.FilterMeAdapter;
import com.src.adrien.gotcha.adapters.HomeGroupAdapter;
import com.src.adrien.gotcha.base.Const;
import com.src.adrien.gotcha.main.BaseFragment;

public class FilterMeScreen extends BaseFragment {

    FilterMeAdapter adapter;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.filter_me_home_screen, container, false);

        applyFonts(view);
        initializeControl(view);

		return view;
	}

    @Override
    protected void initializeActionbar() {
        super.initializeActionbar();

        txtTitle.setText("Filter Me");
    }

    private void applyFonts(View view) {
    }

    private void initializeControl(View view) {
        SwipeMenuListView lstGroup = (SwipeMenuListView) view.findViewById(R.id.my_dues_list_view);
        adapter = new FilterMeAdapter(getActivity());
        lstGroup.setAdapter(adapter);

        lstGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Click" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
