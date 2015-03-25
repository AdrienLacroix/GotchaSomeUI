package com.src.adrien.gotcha.groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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

public class GroupHomeScreen extends BaseFragment {

    HomeGroupAdapter adapter;
    boolean bOpenMenu;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.group_home_screen, container, false);

        bOpenMenu = false;
        applyFonts(view);
        initializeControl(view);

		return view;
	}

    @Override
    protected void initializeActionbar() {
        super.initializeActionbar();
        btnBack.setVisibility(View.INVISIBLE);
        btnRight.setVisibility(View.GONE);
        layoutRight.setVisibility(View.VISIBLE);
        btnRightImage.setVisibility(View.VISIBLE);

        txtTitle.setText("Welcome Grant!");

        btnRightImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTabActivity.addFragments(Const.TAB_FIRST, new GroupCreateScreen(), true, true);
            }
        });
    }

    private void applyFonts(View view) {
        TextView txtView = (TextView) view.findViewById(R.id.group_home_hint_txt);
        txtView.setTypeface(Const.DIN_LIGHT);
    }

    private void initializeControl(View view) {
        ImageView imgHelp = (ImageView) view.findViewById(R.id.group_home_help_img);
        imgHelp.setVisibility(View.GONE);

        SwipeMenuListView lstGroup = (SwipeMenuListView) view.findViewById(R.id.listView);
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
