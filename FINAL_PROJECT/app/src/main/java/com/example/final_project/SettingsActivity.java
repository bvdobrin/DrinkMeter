package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.example.final_project.SecondActivity.APP_PREFERENCES_KEY;

public class SettingsActivity extends AppCompatActivity {

    private Button mResetButton;
    private ImageButton mResetButtonInfo, mSaveWeight;
    private EditText mEditWeight;

    public static final String APP_PREFERENCES_WEIGHT_KEY = "com.example.final_project.preferences.APP_WEIGHT_KEY";
    public static final String WEIGHT_KEY = "com.example.final_project.pref_key.WEIGHT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mResetButton = findViewById(R.id.reset);
        mResetButtonInfo = findViewById(R.id.reset_info);
        mSaveWeight = findViewById(R.id.save_weight);
        mEditWeight = findViewById(R.id.body_weight);

        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_WEIGHT_KEY, Context.MODE_PRIVATE);
        String weight = sharedPref.getString(WEIGHT_KEY, "0");
        if (!weight.equals("0")) {
            mEditWeight.setText(weight);
        }

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_KEY, Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.clear();
                prefEditor.apply();
                Toast.makeText(SettingsActivity.this,
                        "Drink preferences have been reset to default.", Toast.LENGTH_LONG).show();
            }
        });

        mResetButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsActivity.this,
                        "Resets drink autofill values (Qty & Alc) to default.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveWeight(View view) {
        String bodyWeight = (String) mEditWeight.getText().toString();
        if (bodyWeight.equals("")) {
            Toast.makeText(SettingsActivity.this,"Please enter your body weight and save setting.", Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_WEIGHT_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString(WEIGHT_KEY, bodyWeight);
            prefEditor.apply();
            Toast.makeText(SettingsActivity.this, "Saved body weight.", Toast.LENGTH_SHORT).show();
        }
    }
}