package com.src.adrien.gotcha.groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.src.adrien.gotcha.R;
import com.src.adrien.gotcha.base.Const;
import com.src.adrien.gotcha.main.BaseFragment;

public class GroupInviteScreen extends BaseFragment {

    View rootView;
    EditText edtSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.group_invite_screen, container, false);

        applyFonts();

		return rootView;
	}

    @Override
    protected void initializeActionbar() {
        super.initializeActionbar();
        btnBack.setVisibility(View.VISIBLE);
        btnRight.setVisibility(View.VISIBLE);
        layoutRight.setVisibility(View.GONE);

        txtTitle.setText("Invite members");
        btnRight.setText("Done");

        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTabActivity.onBackPressed();
            }
        });

        btnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTabActivity.resetFragment();
            }
        });
    }

    private void applyFonts() {
        edtSearch = (EditText) rootView.findViewById(R.id.add_members_search_edt);
        edtSearch.setTypeface(Const.DIN_REGULAR);

        TextView txtView = (TextView) rootView.findViewById(R.id.add_members_category_txt);
        txtView.setTypeface(Const.DIN_LIGHT);
        txtView = (TextView) rootView.findViewById(R.id.add_members_desc_txt);
        txtView.setTypeface(Const.DIN_LIGHT);
    }
}
