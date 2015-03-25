package com.src.adrien.gotcha.mydues;

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
import com.src.adrien.gotcha.adapters.HomeGroupAdapter;
import com.src.adrien.gotcha.base.Const;
import com.src.adrien.gotcha.main.BaseFragment;

public class MydueScreen extends BaseFragment {

    HomeGroupAdapter adapter;
    boolean bOpenMenu;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mydues_home_screen, container, false);

        bOpenMenu = false;
        applyFonts(view);
        initializeControl(view);

		return view;
	}

    @Override
    protected void initializeActionbar() {
        super.initializeActionbar();
        btnBack.setVisibility(View.VISIBLE);
        btnRight.setVisibility(View.VISIBLE);
        layoutRight.setVisibility(View.GONE);

        txtTitle.setText("My Dues");
        btnRight.setText("Fines paid");
    }

    private void applyFonts(View view) {
        TextView txtView = (TextView) view.findViewById(R.id.my_dues_hint_txt);
        txtView.setTypeface(Const.DIN_LIGHT);
    }

    private void initializeControl(View view) {
        SwipeMenuListView lstGroup = (SwipeMenuListView) view.findViewById(R.id.my_dues_list_view);
        adapter = new HomeGroupAdapter(getActivity());
        lstGroup.setAdapter(adapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
                // set item background
                openItem.setBackground(R.color.more_button_bg_color);
                // set item width
                openItem.setWidth(getActivity().getResources().getDimensionPixelSize(R.dimen.list_more_button_width));
                // set item title
                openItem.setIcon(R.drawable.more_icon);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
                // set item background
                deleteItem.setBackground(R.color.trash_button_bg_color);
                // set item width
                deleteItem.setWidth(getActivity().getResources().getDimensionPixelSize(R.dimen.list_more_button_width));
                // set a icon
                deleteItem.setIcon(R.drawable.trash_icon);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        lstGroup.setMenuCreator(creator);

        // step 2. listener item click event
        lstGroup.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                }
                return false;
            }
        });

        lstGroup.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {}

            @Override
            public void onSwipeEnd(int position) {
                if (position != -1)
                    bOpenMenu = true;
                else
                    bOpenMenu = false;
            }
        });

        lstGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (bOpenMenu) {
                    bOpenMenu = false;
                    return;
                }

                Toast.makeText(getActivity(), "Click" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
