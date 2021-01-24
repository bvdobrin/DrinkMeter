package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.final_project.SettingsActivity.APP_PREFERENCES_WEIGHT_KEY;
import static com.example.final_project.SettingsActivity.WEIGHT_KEY;

public class SecondActivity extends AppCompatActivity {
    private ImageButton mSettingsButton, mStatsButton;

    private Button mPanel1AddButton;
    private EditText mPanel1EditQuantity, mPanel1EditAlcohol;
    private Button mPanel2AddButton;
    private EditText mPanel2EditQuantity, mPanel2EditAlcohol;
    private Button mPanel3AddButton;
    private EditText mPanel3EditQuantity, mPanel3EditAlcohol;
    private Button mPanel4AddButton;
    private EditText mPanel4EditQuantity, mPanel4EditAlcohol;
    private Button mPanel5AddButton;
    private EditText mPanel5EditQuantity, mPanel5EditAlcohol;
    private Button mPanel6AddButton;
    private EditText mPanel6EditQuantity, mPanel6EditAlcohol;

    public static final String APP_PREFERENCES_KEY = "com.example.final_project.preferences.APP_KEY";
    private static final String PANEL1_QUANTITY_KEY = "com.example.final_project.pref_key.PANEL1_QUANTITY_KEY";
    private static final String PANEL1_ALCOHOL_KEY = "com.example.final_project.pref_key.PANEL1_ALCOHOL_KEY";
    private static final String PANEL2_QUANTITY_KEY = "com.example.final_project.pref_key.PANEL2_QUANTITY_KEY";
    private static final String PANEL2_ALCOHOL_KEY = "com.example.final_project.pref_key.PANEL2_ALCOHOL_KEY";
    private static final String PANEL3_QUANTITY_KEY = "com.example.final_project.pref_key.PANEL3_QUANTITY_KEY";
    private static final String PANEL3_ALCOHOL_KEY = "com.example.final_project.pref_key.PANEL3_ALCOHOL_KEY";
    private static final String PANEL4_QUANTITY_KEY = "com.example.final_project.pref_key.PANEL4_QUANTITY_KEY";
    private static final String PANEL4_ALCOHOL_KEY = "com.example.final_project.pref_key.PANEL4_ALCOHOL_KEY";
    private static final String PANEL5_QUANTITY_KEY = "com.example.final_project.pref_key.PANEL5_QUANTITY_KEY";
    private static final String PANEL5_ALCOHOL_KEY = "com.example.final_project.pref_key.PANEL5_ALCOHOL_KEY";
    public static final String DRINKS_HAD_KEY = "com.example.final_project.preferences.DRINKS_HAD_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mStatsButton = findViewById(R.id.see_stats_from_add_drink);
        mSettingsButton = findViewById(R.id.settings_from_add_drink);

        mPanel1AddButton = findViewById(R.id.panel1_text);
        mPanel1EditQuantity = findViewById(R.id.panel1_quantity);
        mPanel1EditAlcohol = findViewById(R.id.panel1_alcohol);
        mPanel2AddButton = findViewById(R.id.panel2_text);
        mPanel2EditQuantity = findViewById(R.id.panel2_quantity);
        mPanel2EditAlcohol = findViewById(R.id.panel2_alcohol);
        mPanel3AddButton = findViewById(R.id.panel3_text);
        mPanel3EditQuantity = findViewById(R.id.panel3_quantity);
        mPanel3EditAlcohol = findViewById(R.id.panel3_alcohol);
        mPanel4AddButton = findViewById(R.id.panel4_text);
        mPanel4EditQuantity = findViewById(R.id.panel4_quantity);
        mPanel4EditAlcohol = findViewById(R.id.panel4_alcohol);
        mPanel5AddButton = findViewById(R.id.panel5_text);
        mPanel5EditQuantity = findViewById(R.id.panel5_quantity);
        mPanel5EditAlcohol = findViewById(R.id.panel5_alcohol);
        mPanel6AddButton = findViewById(R.id.panel6_text);
        mPanel6EditQuantity = findViewById(R.id.panel6_quantity);
        mPanel6EditAlcohol = findViewById(R.id.panel6_alcohol);

        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_KEY, Context.MODE_PRIVATE);
        mPanel1EditQuantity.setText(sharedPref.getString(PANEL1_QUANTITY_KEY, "500"));
        mPanel1EditAlcohol.setText(sharedPref.getString(PANEL1_ALCOHOL_KEY, "5.0"));
        mPanel2EditQuantity.setText(sharedPref.getString(PANEL2_QUANTITY_KEY, "150"));
        mPanel2EditAlcohol.setText(sharedPref.getString(PANEL2_ALCOHOL_KEY, "14.0"));
        mPanel3EditQuantity.setText(sharedPref.getString(PANEL3_QUANTITY_KEY, "40"));
        mPanel3EditAlcohol.setText(sharedPref.getString(PANEL3_ALCOHOL_KEY, "40.0"));
        mPanel4EditQuantity.setText(sharedPref.getString(PANEL4_QUANTITY_KEY, "150"));
        mPanel4EditAlcohol.setText(sharedPref.getString(PANEL4_ALCOHOL_KEY, "13.3"));
        mPanel5EditQuantity.setText(sharedPref.getString(PANEL5_QUANTITY_KEY, "200"));
        mPanel5EditAlcohol.setText(sharedPref.getString(PANEL5_ALCOHOL_KEY, "70.0"));

        mStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_WEIGHT_KEY, Context.MODE_PRIVATE);
                String weight = sharedPref.getString(WEIGHT_KEY, "0");
                if (weight.equals("0")) {
                    Toast.makeText(SecondActivity.this, "Before calculating your blood alcohol content, we need your body weight. Please go to SETTINGS first.", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(SecondActivity.this, StatsActivity.class);
                    startActivity(intent);
                }
            }
        });

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void saveDrink1(View view) {
        String qty = (String) mPanel1EditQuantity.getText().toString();
        String alc = (String) mPanel1EditAlcohol.getText().toString();
        if (qty.equals("") || alc.equals("")) {
            Toast.makeText(SecondActivity.this,"Please enter drink quantity and alcohol.", Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString(PANEL1_QUANTITY_KEY, qty);
            prefEditor.putString(PANEL1_ALCOHOL_KEY, alc);
            prefEditor.apply();
            Toast.makeText(SecondActivity.this,
                    "Added " + qty + "ml Beer with " + alc + "% alcohol.", Toast.LENGTH_LONG).show();
            DrinksHad.getInstance().addDrink(new Drink("Beer", Integer.parseInt(qty), Float.parseFloat(alc)));
        }
    }

    public void saveDrink2(View view) {
        String qty = (String) mPanel2EditQuantity.getText().toString();
        String alc = (String) mPanel2EditAlcohol.getText().toString();
        if (qty.equals("") || alc.equals("")) {
            Toast.makeText(SecondActivity.this,"Please enter drink quantity and alcohol.", Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString(PANEL2_QUANTITY_KEY, qty);
            prefEditor.putString(PANEL2_ALCOHOL_KEY, alc);
            prefEditor.apply();
            Toast.makeText(SecondActivity.this,
                    "Added " + qty + "ml Wine with " + alc + "% alcohol.", Toast.LENGTH_LONG).show();
            DrinksHad.getInstance().addDrink(new Drink("Wine", Integer.parseInt(qty), Float.parseFloat(alc)));
        }
    }

    public void saveDrink3(View view) {
        String qty = (String) mPanel3EditQuantity.getText().toString();
        String alc = (String) mPanel3EditAlcohol.getText().toString();
        if (qty.equals("") || alc.equals("")) {
            Toast.makeText(SecondActivity.this,"Please enter drink quantity and alcohol.", Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString(PANEL3_QUANTITY_KEY, qty);
            prefEditor.putString(PANEL3_ALCOHOL_KEY, alc);
            prefEditor.apply();
            Toast.makeText(SecondActivity.this,
                    "Added " + qty + "ml Vodka/Gin with " + alc + "% alcohol.", Toast.LENGTH_LONG).show();
            DrinksHad.getInstance().addDrink(new Drink("Vodka/Gin", Integer.parseInt(qty), Float.parseFloat(alc)));
        }
    }

    public void saveDrink4(View view) {
        String qty = (String) mPanel4EditQuantity.getText().toString();
        String alc = (String) mPanel4EditAlcohol.getText().toString();
        if (qty.equals("") || alc.equals("")) {
            Toast.makeText(SecondActivity.this,"Please enter drink quantity and alcohol.", Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString(PANEL4_QUANTITY_KEY, qty);
            prefEditor.putString(PANEL4_ALCOHOL_KEY, alc);
            prefEditor.apply();
            Toast.makeText(SecondActivity.this,
                    "Added " + qty + "ml Cocktail with " + alc + "% alcohol.", Toast.LENGTH_LONG).show();
            DrinksHad.getInstance().addDrink(new Drink("Cocktail", Integer.parseInt(qty), Float.parseFloat(alc)));
        }
    }

    public void saveDrink5(View view) {
        String qty = (String) mPanel5EditQuantity.getText().toString();
        String alc = (String) mPanel5EditAlcohol.getText().toString();
        if (qty.equals("") || alc.equals("")) {
            Toast.makeText(SecondActivity.this,"Please enter drink quantity and alcohol.", Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString(PANEL5_QUANTITY_KEY, qty);
            prefEditor.putString(PANEL5_ALCOHOL_KEY, alc);
            prefEditor.apply();
            Toast.makeText(SecondActivity.this,
                    "Added " + qty + "ml Mona with " + alc + "% alcohol.", Toast.LENGTH_LONG).show();
            DrinksHad.getInstance().addDrink(new Drink("Mona", Integer.parseInt(qty), Float.parseFloat(alc)));
        }
    }

    public void saveDrink6(View view) {
        String qty = (String) mPanel6EditQuantity.getText().toString();
        String alc = (String) mPanel6EditAlcohol.getText().toString();
        if (qty.equals("") || alc.equals("")) {
            Toast.makeText(SecondActivity.this,"Please enter drink quantity and alcohol.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SecondActivity.this,
                    "Added " + qty + "ml Custom Drink with " + alc + "% alcohol.", Toast.LENGTH_LONG).show();
            DrinksHad.getInstance().addDrink(new Drink("Custom Drink", Integer.parseInt(qty), Float.parseFloat(alc)));
        }
    }
}