package com.src.adrien.gotcha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.src.adrien.gotcha.base.BaseActivity;
import com.src.adrien.gotcha.base.ClickSpan;
import com.src.adrien.gotcha.base.Const;
import com.src.adrien.gotcha.base.Utils;
import com.src.adrien.gotcha.main.FragmentTabHostActivity;


public class SignInActivity extends BaseActivity {

    private EditText edtUsername, edtPassword;
    private TextView txtForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initializeActionBar();
        initializeControls();
    }

    @Override
    protected void initializeActionBar() {
        super.initializeActionBar();
        txtTitle.setText("Sign In");

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignIn();
            }
        });
    }

    private void initializeControls() {
        edtUsername = (EditText) findViewById(R.id.sign_in_username_edt);
        edtPassword = (EditText) findViewById(R.id.sign_in_password_edt);

        txtForgot = (TextView) findViewById(R.id.sign_in_forgot_txt);
        TextView txtOr = (TextView) findViewById(R.id.sign_in_or_txt);

        TextView txtTerms = (TextView) findViewById(R.id.sign_in_terms_txt);
        txtTerms.setText("By tapping on the \"Next\" button you agree to our Terms of Service and Privacy Policy.");
        Utils.clickify(txtTerms, "Terms of Service and Privacy Policy",
                new ClickSpan.OnClickListener() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent(SignInActivity.this, TermsActivity.class);
                        startActivity(intent);
                    }
                });

        edtUsername.setTypeface(Const.DIN_REGULAR);
        edtPassword.setTypeface(Const.DIN_REGULAR);
        txtForgot.setTypeface(Const.DIN_LIGHT);
        txtOr.setTypeface(Const.DIN_LIGHT);
        txtTerms.setTypeface(Const.DIN_LIGHT);
    }

    private void onSignIn() {
        Intent intent = new Intent(this, FragmentTabHostActivity.class);
        startActivity(intent);
    }
}
