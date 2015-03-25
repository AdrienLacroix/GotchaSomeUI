package com.src.adrien.gotcha;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.src.adrien.gotcha.base.BaseActivity;
import com.src.adrien.gotcha.base.Const;
import com.src.adrien.gotcha.base.Utils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;


public class TermsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        initializeActionBar();

        TextView txtTerms = (TextView) findViewById(R.id.terms_txt);
        txtTerms.setTypeface(Const.DIN_REGULAR);
    }

    @Override
    protected void initializeActionBar() {
        super.initializeActionBar();
        txtTitle.setText("Terms & Privacy");
        btnRight.setVisibility(View.INVISIBLE);
    }
}
