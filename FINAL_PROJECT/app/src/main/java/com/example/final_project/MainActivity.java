package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.final_project.SettingsActivity.APP_PREFERENCES_WEIGHT_KEY;
import static com.example.final_project.SettingsActivity.WEIGHT_KEY;

public class MainActivity extends AppCompatActivity {

    private Button mAddDrinkButton;
    private Button mStatsButton;
    private Button mSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddDrinkButton = findViewById(R.id.add_drink_second_activity);
        mStatsButton = findViewById(R.id.see_stats_third_activity);
        mSettingsButton = findViewById(R.id.settings_fourth_activity);

        mAddDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "I pushed the button!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        mStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_WEIGHT_KEY, Context.MODE_PRIVATE);
                String weight = sharedPref.getString(WEIGHT_KEY, "0");
                if (weight.equals("0")) {
                    Toast.makeText(MainActivity.this, "Before calculating your blood alcohol content, we need your body weight. Please go to SETTINGS first.", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, StatsActivity.class);
                    startActivity(intent);
                }
            }
        });
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

}