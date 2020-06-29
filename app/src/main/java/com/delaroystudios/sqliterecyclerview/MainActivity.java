package com.delaroystudios.sqliterecyclerview;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import android.view.View;

import android.widget.Toast;


import com.delaroystudios.sqliterecyclerview.helper.InputValidation;
import com.delaroystudios.sqliterecyclerview.model.Beneficiary;

import com.delaroystudios.sqliterecyclerview.sql.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = MainActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutBeneficiaryName;
    private TextInputLayout textInputLayoutBeneficiaryEmail;
    private TextInputLayout textInputLayoutBeneficiaryAddress;
    private TextInputLayout textInputLayoutBeneficiaryCountry;
    private TextInputLayout textInputLayoutBeneficiaryAge;
    private TextInputLayout textInputLayoutBeneficiaryGender;
    private TextInputLayout textInputLayoutBeneficiaryPulserate;
    private TextInputLayout textInputLayoutBeneficiaryWeight;
    private TextInputLayout textInputLayoutBeneficiaryHeight;
    private TextInputLayout textInputLayoutBeneficiaryBloodGrp;
    private TextInputLayout textInputLayoutBeneficiaryId;

    private TextInputEditText textInputEditTextBeneficiaryName;
    private TextInputEditText textInputEditTextBeneficiaryEmail;
    private TextInputEditText textInputEditTextBeneficiaryAddress;
    private TextInputEditText textInputEditTextBeneficiaryCountry;
    private TextInputEditText textInputEditTextBeneficiaryAge;
    private TextInputEditText textInputEditTextBeneficiaryGender;
    private TextInputEditText textInputEditTextBeneficiaryPulserate;
    private TextInputEditText textInputEditTextBeneficiaryWeight;
    private TextInputEditText textInputEditTextBeneficiaryHeight;
    private TextInputEditText textInputEditTextBeneficiaryBloodGrp;
    private TextInputEditText textInputEditTextBeneficiaryId;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewBenefList;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Beneficiary beneficiary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        initObjects();
        initListeners();
    }

    //Initialize Views
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutBeneficiaryName = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryName);
        textInputLayoutBeneficiaryEmail = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryEmail);
        textInputLayoutBeneficiaryAddress = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryAddress);
        textInputLayoutBeneficiaryCountry = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryCountry);
        textInputLayoutBeneficiaryAge = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryAge);
        textInputLayoutBeneficiaryGender = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryGender);
        textInputLayoutBeneficiaryPulserate = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryPulserate);
        textInputLayoutBeneficiaryWeight = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryWeight);
        textInputLayoutBeneficiaryHeight = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryHeight);
        textInputLayoutBeneficiaryBloodGrp = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryBloodGrp);
        textInputLayoutBeneficiaryId = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryId);

        textInputEditTextBeneficiaryName = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryName);
        textInputEditTextBeneficiaryEmail = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryEmail);
        textInputEditTextBeneficiaryAddress = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryAddress);
        textInputEditTextBeneficiaryCountry = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryCountry);
        textInputEditTextBeneficiaryAge = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryAge);
        textInputEditTextBeneficiaryGender = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryGender);
        textInputEditTextBeneficiaryPulserate = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryPulserate);
        textInputEditTextBeneficiaryWeight = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryWeight);
        textInputEditTextBeneficiaryHeight = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryHeight);
        textInputEditTextBeneficiaryBloodGrp = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryBloodgrp);
        textInputEditTextBeneficiaryId = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryId);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewBenefList = (AppCompatTextView) findViewById(R.id.appCompatTextViewBenefList);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewBenefList.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        beneficiary = new Beneficiary();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewBenefList:
                Intent accountsIntent = new Intent(activity, BeneficiaryListActivity.class);
                accountsIntent.putExtra("ID", textInputEditTextBeneficiaryId.getText().toString().trim());
                accountsIntent.putExtra("NAME", textInputEditTextBeneficiaryName.getText().toString().trim());
                accountsIntent.putExtra("EMAIL", textInputEditTextBeneficiaryEmail.getText().toString().trim());
                accountsIntent.putExtra("ADDRESS", textInputEditTextBeneficiaryAddress.getText().toString().trim());
                accountsIntent.putExtra("COUNTRY", textInputEditTextBeneficiaryCountry.getText().toString().trim());
                accountsIntent.putExtra("AGE", textInputEditTextBeneficiaryAge.getText().toString().trim());
                accountsIntent.putExtra("GENDER", textInputEditTextBeneficiaryGender.getText().toString().trim());
                accountsIntent.putExtra("PULSERATE", textInputEditTextBeneficiaryPulserate.getText().toString().trim());
                accountsIntent.putExtra("WEIGHT", textInputEditTextBeneficiaryWeight.getText().toString().trim());
                accountsIntent.putExtra("HEIGHT", textInputEditTextBeneficiaryHeight.getText().toString().trim());
                accountsIntent.putExtra("BLOODGRP", textInputEditTextBeneficiaryBloodGrp.getText().toString().trim());
                emptyInputEditText();
                startActivity(accountsIntent);
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextBeneficiaryName, textInputLayoutBeneficiaryName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextBeneficiaryEmail, textInputLayoutBeneficiaryEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextBeneficiaryEmail, textInputLayoutBeneficiaryEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextBeneficiaryEmail.getText().toString().trim())) {

            beneficiary.setId(Integer.parseInt(textInputEditTextBeneficiaryId.getText().toString().trim()));
            beneficiary.setName(textInputEditTextBeneficiaryName.getText().toString().trim());
            beneficiary.setEmail(textInputEditTextBeneficiaryEmail.getText().toString().trim());
            beneficiary.setAddress(textInputEditTextBeneficiaryAddress.getText().toString().trim());
            beneficiary.setCountry(textInputEditTextBeneficiaryCountry.getText().toString().trim());
            beneficiary.setAge(textInputEditTextBeneficiaryAge.getText().toString().trim());
            beneficiary.setGender(textInputEditTextBeneficiaryGender.getText().toString().trim());
            beneficiary.setPulserate(textInputEditTextBeneficiaryPulserate.getText().toString().trim());
            beneficiary.setWeight(textInputEditTextBeneficiaryWeight.getText().toString().trim());
            beneficiary.setHeight(textInputEditTextBeneficiaryHeight.getText().toString().trim());
            beneficiary.setBloodgrp(textInputEditTextBeneficiaryBloodGrp.getText().toString().trim());


            databaseHelper.addBeneficiary(beneficiary);

            // Snack Bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(activity, BeneficiaryListActivity.class);
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT)
                    .show();
            accountsIntent.putExtra("ID", textInputEditTextBeneficiaryId.getText().toString().trim());
            accountsIntent.putExtra("NAME", textInputEditTextBeneficiaryName.getText().toString().trim());
            accountsIntent.putExtra("EMAIL", textInputEditTextBeneficiaryEmail.getText().toString().trim());
            accountsIntent.putExtra("ADDRESS", textInputEditTextBeneficiaryAddress.getText().toString().trim());
            accountsIntent.putExtra("COUNTRY", textInputEditTextBeneficiaryCountry.getText().toString().trim());
            accountsIntent.putExtra("AGE", textInputEditTextBeneficiaryAge.getText().toString().trim());
            accountsIntent.putExtra("GENDER", textInputEditTextBeneficiaryGender.getText().toString().trim());
            accountsIntent.putExtra("PULSERATE", textInputEditTextBeneficiaryPulserate.getText().toString().trim());
            accountsIntent.putExtra("WEIGHT", textInputEditTextBeneficiaryWeight.getText().toString().trim());
            accountsIntent.putExtra("HEIGHT", textInputEditTextBeneficiaryHeight.getText().toString().trim());
            accountsIntent.putExtra("BLOODGRP", textInputEditTextBeneficiaryBloodGrp.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    private void emptyInputEditText() {
        textInputEditTextBeneficiaryName.setText(null);
        textInputEditTextBeneficiaryEmail.setText(null);
        textInputEditTextBeneficiaryAddress.setText(null);
        textInputEditTextBeneficiaryCountry.setText(null);
        textInputEditTextBeneficiaryAge.setText(null);
        textInputEditTextBeneficiaryGender.setText(null);
        textInputEditTextBeneficiaryPulserate.setText(null);
        textInputEditTextBeneficiaryWeight.setText(null);
        textInputEditTextBeneficiaryHeight.setText(null);
        textInputEditTextBeneficiaryBloodGrp.setText(null);
    }
}