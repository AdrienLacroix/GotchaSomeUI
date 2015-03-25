/**
 * @author Hardik A. Bhalodi  
 * this demo is solution for who want to write app using Tab with fragment,enjoy !!!!
 * {@link FeatureInfo}
 *  -sticky header with back button navigation
 *  -Tab solution with back to home screen function
 *  - 
 *  
 *
 */

package com.src.adrien.gotcha.main;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.src.adrien.gotcha.R;
import com.src.adrien.gotcha.base.BaseActivity;
import com.src.adrien.gotcha.base.Const;
import com.src.adrien.gotcha.filterme.FilterMeScreen;
import com.src.adrien.gotcha.groups.GroupHomeScreen;
import com.src.adrien.gotcha.mydues.MydueScreen;

public class FragmentTabHostActivity extends BaseActivity implements
		OnTabChangeListener {

	private TabHost tabHost;
	private String currentSelectedTab;
	private HashMap<String, ArrayList<Fragment>> hMapTabs;
	final String arrTabLabel[] = { "Groups", "My Dues", "Filter Me", "Contacts", "Settings" };
    final int arrTabImage[] = { R.drawable.group_icon,
                                R.drawable.mydues_icon,
                                R.drawable.filter_me_icon,
                                R.drawable.contacts_icon,
                                R.drawable.settings_icon };

    final int arrTabSelImage[] = { R.drawable.group_icon_sel,
            R.drawable.mydues_icon_sel,
            R.drawable.filter_me_icon_sel,
            R.drawable.contacts_icon_sel,
            R.drawable.settings_icon_sel };

	/*
	 * used arrIcons when foreground icon and background image both required in tab
	 */
	/*
	 * final static int arrIcons[] = {};
	 */
	private MyTabView arrTabs[] = new MyTabView[5];

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.fragment_tab_host_activity);

		// initlizing tabs//
		hMapTabs = new HashMap<String, ArrayList<Fragment>>();
		hMapTabs.put(Const.TAB_FIRST, new ArrayList<Fragment>());
		hMapTabs.put(Const.TAB_SECOND, new ArrayList<Fragment>());
		hMapTabs.put(Const.TAB_THIRD, new ArrayList<Fragment>());
		hMapTabs.put(Const.TAB_FORTH, new ArrayList<Fragment>());
        hMapTabs.put(Const.TAB_FIFTH, new ArrayList<Fragment>());

		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setOnTabChangedListener(this);
		tabHost.setup();

		TabHost.TabSpec spec = tabHost.newTabSpec(Const.TAB_FIRST);
		tabHost.setCurrentTab(0);
		arrTabs[0] = new MyTabView(this, arrTabImage[0], arrTabLabel[0]);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(arrTabs[0]);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec(Const.TAB_SECOND);
		arrTabs[1] = new MyTabView(this, arrTabImage[1], arrTabLabel[1]);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(arrTabs[1]);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec(Const.TAB_THIRD);
		arrTabs[2] = new MyTabView(this, arrTabImage[2], arrTabLabel[2]);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(arrTabs[2]);
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec(Const.TAB_FORTH);
		arrTabs[3] = new MyTabView(this, arrTabImage[3], arrTabLabel[3]);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(android.R.id.tabcontent);
			}
		});
		spec.setIndicator(arrTabs[3]);
		tabHost.addTab(spec);

        spec = tabHost.newTabSpec(Const.TAB_FIFTH);
        arrTabs[4] = new MyTabView(this, arrTabImage[4], arrTabLabel[4]);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(android.R.id.tabcontent);
            }
        });
        spec.setIndicator(arrTabs[4]);
        tabHost.addTab(spec);

		// Listner for Tab 1//
		tabHost.getTabWidget().getChildAt(0)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (hMapTabs.size() > 0) {

							if (tabHost.getTabWidget().getChildAt(0).isSelected()) {
								if (hMapTabs.get(Const.TAB_FIRST).size() > 1) {
									resetFragment();
								}
							}
							tabHost.getTabWidget().setCurrentTab(0);
							tabHost.setCurrentTab(0);
						}
					}
				});

		/* Listner for Tab 2 */
		tabHost.getTabWidget().getChildAt(1)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (hMapTabs.size() > 0) {

							if (tabHost.getTabWidget().getChildAt(1)
									.isSelected()) {
								if (hMapTabs.get(Const.TAB_SECOND).size() > 1) {
									resetFragment();
								}
							}
							tabHost.getTabWidget().setCurrentTab(1);
							tabHost.setCurrentTab(1);
						}
					}
				});

		/* Listner for Tab 3 */
		tabHost.getTabWidget().getChildAt(2)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (hMapTabs.size() > 0) {

							if (tabHost.getTabWidget().getChildAt(2)
									.isSelected()) {
								if (hMapTabs.get(Const.TAB_THIRD).size() > 1) {
									resetFragment();
								}
							}
							tabHost.getTabWidget().setCurrentTab(2);
							tabHost.setCurrentTab(2);
						}
					}
				});
		/* Listner for Tab 4 */
		tabHost.getTabWidget().getChildAt(3)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (hMapTabs.size() > 0) {

							if (tabHost.getTabWidget().getChildAt(3)
									.isSelected()) {
								if (hMapTabs.get(Const.TAB_FORTH).size() > 1) {
									resetFragment();
								}
							}
							tabHost.getTabWidget().setCurrentTab(3);
							tabHost.setCurrentTab(3);
						}
					}
				});

        /* Listner for Tab 5 */
        tabHost.getTabWidget().getChildAt(4)
                .setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        if (hMapTabs.size() > 0) {

                            if (tabHost.getTabWidget().getChildAt(4)
                                    .isSelected()) {
                                if (hMapTabs.get(Const.TAB_FIFTH).size() > 1) {
                                    resetFragment();
                                }
                            }
                            tabHost.getTabWidget().setCurrentTab(4);
                            tabHost.setCurrentTab(4);
                        }
                    }
                });

	}

	/* Method for adding fragment */
	public void addFragments(String tabName, Fragment fragment,
			boolean animate, boolean add) {
		if (add) {
			hMapTabs.get(tabName).add(fragment);
		}
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		if (animate) {
			ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
		}
		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();
	}

	/* Method for remove fragment */

	public void removeFragment() {
		Fragment fragment = hMapTabs.get(currentSelectedTab).get(
				hMapTabs.get(currentSelectedTab).size() - 2);
		hMapTabs.get(currentSelectedTab).remove(
				hMapTabs.get(currentSelectedTab).size() - 1);
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();
	}

	// reset frgment used when clicked on same tab
	public void resetFragment() {
		Fragment fragment = hMapTabs.get(currentSelectedTab).get(0);
		hMapTabs.get(currentSelectedTab).clear();
		hMapTabs.get(currentSelectedTab).add(fragment);
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
		ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
		ft.replace(android.R.id.tabcontent, fragment);
		ft.commit();

	}

	@Override
	public void onBackPressed() {

		if (hMapTabs.get(currentSelectedTab).size() <= 1) {
			super.onBackPressed();
		} else {
			removeFragment();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (hMapTabs.get(currentSelectedTab).size() == 0) {
			return;
		}
		hMapTabs.get(currentSelectedTab)
				.get(hMapTabs.get(currentSelectedTab).size() - 1)
				.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onTabChanged(String tabName) {
		// TODO Auto-generated method stub
		currentSelectedTab = tabName;

		// make iteration for unselected tab and make normal background
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tab_label);
            tv.setTextColor(getResources().getColor(R.color.tab_bar_text_n_color));
			ImageView iv = (ImageView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tab_image);
			iv.setImageResource(arrTabImage[i]);
		}
		TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(R.id.tab_label); // for Selected Tab
		tv.setTextColor(getResources().getColor(R.color.tab_bar_text_s_color));
        ImageView iv = (ImageView) tabHost.getCurrentTabView().findViewById(R.id.tab_image);
        iv.setImageResource(arrTabSelImage[tabHost.getCurrentTab()]);

		if (hMapTabs.get(tabName).size() == 0) {
			if (tabName.equals(Const.TAB_FIRST)) {
				addFragments(tabName, new GroupHomeScreen(), false, true);
			} else if (tabName.equals(Const.TAB_SECOND)) {
				addFragments(tabName, new MydueScreen(), false, true);
			} else if (tabName.equals(Const.TAB_THIRD)) {
				addFragments(tabName, new FilterMeScreen(), false, true);
			} else if (tabName.equals(Const.TAB_FORTH)) {
				addFragments(tabName, new BaseFragment(), false, true);
			} else if (tabName.equals(Const.TAB_FIFTH)) {
                addFragments(tabName, new BaseFragment(), false, true);
            }
		} else {
			addFragments(
					tabName,
					hMapTabs.get(tabName).get(hMapTabs.get(tabName).size() - 1),
					false, false);
		}
	}

	private class MyTabView extends LinearLayout {
		public MyTabView(Context c, int drawableIdx, String label) {
			super(c);

            View view = LayoutInflater.from(c).inflate(R.layout.tab_item, null);
            TextView txtView = (TextView)view.findViewById(R.id.tab_label);
            txtView.setTextColor(c.getResources().getColor(R.color.tab_bar_text_n_color));
            txtView.setText(label);

            ImageView imgView = (ImageView) view.findViewById(R.id.tab_image);
            imgView.setImageResource(drawableIdx);
            addView(view);
		}
	}

}
