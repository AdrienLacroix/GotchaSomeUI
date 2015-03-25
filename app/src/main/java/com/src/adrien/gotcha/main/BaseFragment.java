package com.src.adrien.gotcha.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.src.adrien.gotcha.R;
import com.src.adrien.gotcha.base.Const;
import com.src.adrien.gotcha.customview.SquareLayout;

public class BaseFragment extends Fragment {

	protected FragmentTabHostActivity fragmentTabActivity;

    protected TextView txtTitle;
    protected Button btnBack, btnRight, btnRightImage;
    protected SquareLayout layoutRight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		fragmentTabActivity =  (FragmentTabHostActivity) this.getActivity();
        initializeActionbar();
	}
	

	public boolean onBackPressed() {
		return false;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
	}

    protected void initializeActionbar() {

        ActionBar actionBar = fragmentTabActivity.getSupportActionBar();
        View actionbarView = actionBar.getCustomView();

        txtTitle = (TextView) actionbarView.findViewById(R.id.actionbar_title_txt);
        txtTitle.setTypeface(Const.DIN_BOLD);
        txtTitle.setText("");

        btnBack = (Button) actionbarView.findViewById(R.id.actionbar_back_btn);
        btnBack.setVisibility(View.GONE);
        btnRight = (Button) actionbarView.findViewById(R.id.actionbar_right_btn);
        btnRight.setTypeface(Const.DIN_MEDIUM);
        btnRight.setVisibility(View.GONE);

        layoutRight = (SquareLayout) actionbarView.findViewById(R.id.actionbar_right_layout);
        layoutRight.setVisibility(View.GONE);
        btnRightImage = (Button) actionbarView.findViewById(R.id.actionbar_right_img_btn);
    }
}