package com.src.adrien.gotcha;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.src.adrien.gotcha.base.Utils;
import com.viewpagerindicator.CirclePageIndicator;


public class SplashActivity extends Activity {

    private static final int SPLASH_VIEW_COUNT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);

        tintManager.setStatusBarTintResource(R.drawable.status_bar_bg);

        SplashAdapter adapter = new SplashAdapter();

        ViewPager pager = (ViewPager) findViewById(R.id.splash_pager);
        pager.setAdapter(adapter);

        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.splash_indicator);
        indicator.setViewPager(pager);
        indicator.setFillColor(Color.parseColor("#ffec1b3c"));
        indicator.setPageColor(Color.parseColor("#ffec1b3c"));
        indicator.setStrokeColor(Color.WHITE);
        indicator.setStrokeWidth(5);
        indicator.setRadius(15);
    }

    public void onLogin(View v) {
        Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void onSignUp(View v) {
        Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);
        startActivity(intent);
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

    private class SplashAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem (ViewGroup container, int position)
        {
            LinearLayout.LayoutParams vParam = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            ImageView pageView = new ImageView(SplashActivity.this);
            pageView.setLayoutParams(vParam);
            pageView.setAdjustViewBounds(true);
            Bitmap bmp = Utils.decodeSampledBitmapFromResource(400, 700,
                    SplashActivity.this, splash_images[position]);
            pageView.setImageBitmap(bmp);

            ((ViewPager) container).addView(pageView, 0);
            return pageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public int getCount() {
            return SPLASH_VIEW_COUNT;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }
    }

    private int[] splash_images = new int[]{
            R.drawable.splash1,
            R.drawable.splash2,
            R.drawable.splash3,
            R.drawable.splash4
    };
}
