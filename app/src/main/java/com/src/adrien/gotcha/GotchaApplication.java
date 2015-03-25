package com.src.adrien.gotcha;

import android.app.Application;
import android.graphics.Typeface;

import com.src.adrien.gotcha.base.Const;

/**
 * Created by Adrien on 1/8/2015.
 */
public class GotchaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        createCustomFonts();
    }

    private void createCustomFonts() {
        Const.DIN_BOLD = Typeface.createFromAsset(getAssets(), "DINNextRoundedLTPro-Bold.otf");
        Const.DIN_LIGHT = Typeface.createFromAsset(getAssets(), "DINNextRoundedLTPro-Light.otf");
        Const.DIN_MEDIUM = Typeface.createFromAsset(getAssets(), "DINNextRoundedLTPro-Medium.otf");
        Const.DIN_REGULAR = Typeface.createFromAsset(getAssets(), "DINNextRoundedLTPro-Regular.otf");
    }
}
