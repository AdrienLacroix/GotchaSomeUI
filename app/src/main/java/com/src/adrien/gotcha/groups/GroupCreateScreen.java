package com.src.adrien.gotcha.groups;

import android.app.Activity;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.src.adrien.gotcha.R;
import com.src.adrien.gotcha.customview.ActionSheet;
import com.src.adrien.gotcha.base.Const;
import com.src.adrien.gotcha.base.Utils;
import com.src.adrien.gotcha.main.BaseFragment;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class GroupCreateScreen extends BaseFragment implements ActionSheet.ActionSheetListener {

    View rootView;
    EditText edtGroupName;
    ImageView imgGroupView;

    boolean bGroupImage;
    Bitmap groupBitmap;

    protected static final int REQUEST_PICK_IMAGE = 101;
    protected static final int REQUEST_TAKE_IMAGE = 102;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.group_create_screen, container, false);

        getActivity().setTheme(R.style.ActionSheetStyleIOS7);
        applyFonts();
        addEventListener();

        bGroupImage = false;

		return rootView;
	}

    @Override
    protected void initializeActionbar() {
        super.initializeActionbar();
        btnBack.setVisibility(View.VISIBLE);
        btnRight.setVisibility(View.VISIBLE);
        layoutRight.setVisibility(View.GONE);

        txtTitle.setText("Create Group");
        btnRight.setText("Next");

        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTabActivity.onBackPressed();
            }
        });

        btnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTabActivity.addFragments(Const.TAB_FIRST, new GroupInviteScreen(), true, true);
            }
        });
    }

    private void applyFonts() {
        edtGroupName = (EditText) rootView.findViewById(R.id.create_group_name_edt);
        edtGroupName.setTypeface(Const.DIN_REGULAR);

        TextView txtView = (TextView) rootView.findViewById(R.id.create_group_fine_txt);
        txtView.setTypeface(Const.DIN_LIGHT);
        txtView = (TextView) rootView.findViewById(R.id.create_group_fine_desc_txt);
        txtView.setTypeface(Const.DIN_LIGHT);
        txtView = (TextView) rootView.findViewById(R.id.create_group_nomination_txt);
        txtView.setTypeface(Const.DIN_LIGHT);
        txtView = (TextView) rootView.findViewById(R.id.create_group_nomination_desc_txt);
        txtView.setTypeface(Const.DIN_LIGHT);
    }

    private void addEventListener() {
        imgGroupView = (ImageView) rootView.findViewById(R.id.create_group_image);
        Button btnPhoto = (Button) rootView.findViewById(R.id.create_group_image_btn);
        btnPhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionSheet.createBuilder(getActivity(), getChildFragmentManager())
                        .setCancelButtonTitle("Cancel")
                        .setOtherButtonTitles(getResources().getString(R.string.take_photo),
                                getResources().getString(R.string.choose_photo))
                        .setCancelableOnTouchOutside(true)
                        .setListener(GroupCreateScreen.this).show();
            }
        });
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {}

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        if (index == 0) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                getActivity().startActivityForResult(takePictureIntent, REQUEST_TAKE_IMAGE);
            }
        } else {
            Intent pickPhoto = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            getActivity().startActivityForResult(pickPhoto, REQUEST_PICK_IMAGE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri pickedImage = data.getData();
            choosePicture(pickedImage);
        } else if (requestCode == REQUEST_TAKE_IMAGE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            groupBitmap = (Bitmap) extras.get("data");
            imgGroupView.setImageBitmap(groupBitmap);
            bGroupImage = true;
        }
    }

    private void choosePicture(Uri pickedImage) {
        String[] filePath = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(pickedImage, filePath, null, null, null);
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
                    ParcelFileDescriptor parcelFileDescriptor =
                            getActivity().getContentResolver().openFileDescriptor(pickedImage, "r");
                    FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                    bmp = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                    parcelFileDescriptor.close();
                }
                groupBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), mat, true);
                imgGroupView.setImageBitmap(groupBitmap);
                bGroupImage = true;
            } else {
                ParcelFileDescriptor parcelFileDescriptor =
                        getActivity().getContentResolver().openFileDescriptor(pickedImage, "r");
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                groupBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                parcelFileDescriptor.close();
                imgGroupView.setImageBitmap(groupBitmap);
                bGroupImage = true;
            }
        } catch (IOException e) {
            Log.w("TAG", "-- Error in setting image");
        } catch (OutOfMemoryError oom) {
            Log.w("TAG", "-- OOM Error in setting image");
        }
        cursor.close();
    }
}
