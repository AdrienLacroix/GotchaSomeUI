package com.src.adrien.gotcha.base;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.src.adrien.gotcha.R;
import com.src.adrien.gotcha.customview.SquareLayout;

/**
 * Created by Adrien on 12/20/2014.
 */
public class BaseActivity extends ActionBarActivity {

    protected TextView txtTitle;
    protected Button btnBack, btnRight, btnRightImage;
    protected SquareLayout layoutRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.drawable.status_bar_bg);
        getSupportActionBar().setBackgroundDrawable(
                getResources().getDrawable(R.drawable.action_bar_bg));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.gotcha_action_bar);
    }

    /**
     * initialize action bar
     */
    protected void initializeActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        View actionbarView = actionBar.getCustomView();

        txtTitle = (TextView) actionbarView.findViewById(R.id.actionbar_title_txt);
        txtTitle.setTypeface(Const.DIN_BOLD);

        btnBack = (Button) actionbarView.findViewById(R.id.actionbar_back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnRight = (Button) actionbarView.findViewById(R.id.actionbar_right_btn);
        btnRight.setTypeface(Const.DIN_MEDIUM);

        layoutRight = (SquareLayout) actionbarView.findViewById(R.id.actionbar_right_layout);
        layoutRight.setVisibility(View.GONE);
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
