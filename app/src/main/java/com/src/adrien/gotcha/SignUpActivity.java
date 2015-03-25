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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.src.adrien.gotcha.customview.ActionSheet;
import com.src.adrien.gotcha.base.BaseActivity;
import com.src.adrien.gotcha.base.Const;
import com.src.adrien.gotcha.base.Utils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;


public class SignUpActivity extends BaseActivity implements ActionSheet.ActionSheetListener {

    private Button btnProfile;
    private ImageView imgProfileView;
    private EditText edtName, edtEmail, edtPhone, edtUsername, edtPassword;

    private boolean bProfileImage;
    private Bitmap profileBitmap;

    protected static final int REQUEST_PICK_IMAGE = 101;
    protected static final int REQUEST_TAKE_IMAGE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTheme(R.style.ActionSheetStyleIOS7);

        initializeActionBar();
        initializeControls();
    }

    @Override
    protected void initializeActionBar() {
        super.initializeActionBar();
        txtTitle.setText("Sign Up");

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showAlert(SignUpActivity.this, null, "okokok");
            }
        });
    }

    private void initializeControls() {
        btnProfile = (Button) findViewById(R.id.sign_up_profile_btn);
        imgProfileView = (ImageView) findViewById(R.id.sign_up_profile_img);

        edtName = (EditText) findViewById(R.id.sign_up_name_edt);
        edtEmail = (EditText) findViewById(R.id.sign_up_email_edt);
        edtPhone = (EditText) findViewById(R.id.sign_up_phone_edt);
        edtUsername = (EditText) findViewById(R.id.sign_up_username_edt);
        edtPassword = (EditText) findViewById(R.id.sign_up_password_edt);

        TextView txtOr = (TextView) findViewById(R.id.sign_up_or_txt);
        txtOr.setTypeface(Const.DIN_LIGHT);
        Button btnTerms = (Button) findViewById(R.id.sign_up_terms_btn);
        btnTerms.setTypeface(Const.DIN_LIGHT);

        edtName.setTypeface(Const.DIN_REGULAR);
        edtEmail.setTypeface(Const.DIN_REGULAR);
        edtPhone.setTypeface(Const.DIN_REGULAR);
        edtUsername.setTypeface(Const.DIN_REGULAR);
        edtPassword.setTypeface(Const.DIN_REGULAR);

        bProfileImage = false;
    }

    public void onProfileBtn(View view) {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("Cancel")
                .setOtherButtonTitles(getResources().getString(R.string.take_photo),
                        getResources().getString(R.string.choose_photo))
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }

    public void goTerms(View view) {
        Intent intent = new Intent(this, TermsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {}

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_TAKE_IMAGE);
            }
        } else {
            Intent pickPhoto = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, REQUEST_PICK_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK) {
            Uri pickedImage = data.getData();
            choosePicture(pickedImage);
        } else if (requestCode == REQUEST_TAKE_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            profileBitmap = (Bitmap) extras.get("data");
            imgProfileView.setImageBitmap(profileBitmap);
            btnProfile.setBackgroundResource(R.drawable.select_photo_mask);
            bProfileImage = true;
        }
    }

    private void choosePicture(Uri pickedImage) {
        String[] filePath = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
        cursor.moveToFirst();
        String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

        try {
            if (imagePath != null) {
                File f = new File(imagePath);
                ExifInterface exif = new ExifInterface(f.getPath());
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                int angle = 0;
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                    angle = 90;
                } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                    angle = 180;
                } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                    angle = 270;
                }

                Matrix mat = new Matrix();
                mat.postRotate(angle);

                Bitmap bmp = Utils.decodeSampledBitmapFromResource(300, 300, imagePath);
                if (bmp == null) {
                    ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(pickedImage, "r");
                    FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                    bmp = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                    parcelFileDescriptor.close();
                }
                profileBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), mat, true);
                imgProfileView.setImageBitmap(profileBitmap);
                btnProfile.setBackgroundResource(R.drawable.select_photo_mask);
                bProfileImage = true;
            } else {
                ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(pickedImage, "r");
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                profileBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                parcelFileDescriptor.close();
                imgProfileView.setImageBitmap(profileBitmap);
                btnProfile.setBackgroundResource(R.drawable.select_photo_mask);
                bProfileImage = true;
            }
        } catch (IOException e) {
            Log.w("TAG", "-- Error in setting image");
        } catch (OutOfMemoryError oom) {
            Log.w("TAG", "-- OOM Error in setting image");
        }
        cursor.close();
    }
}
