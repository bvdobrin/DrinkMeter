package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.final_project.SettingsActivity.APP_PREFERENCES_WEIGHT_KEY;
import static com.example.final_project.SettingsActivity.WEIGHT_KEY;

public class StatsActivity extends AppCompatActivity {

    private TextView mBACtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        mBACtext = findViewById(R.id.bac_text);

        ArrayList<Drink> drinksList = DrinksHad.getInstance().getDrinksList();
        int drinksListSize = drinksList.size();
        int i, quantity;
        float alcohol, totalAlcohol = 0, bac;

        //Calculate how many mL of alcohol consumed
        for (i=0; i<drinksListSize; i++) {
            quantity = drinksList.get(i).getQuantity();
            alcohol = drinksList.get(i).getAlcohol() / 100;
            totalAlcohol += (float)quantity * alcohol;
        }

        //Calculate the Blood Alcohol Content
        SharedPreferences sharedPref = getSharedPreferences(APP_PREFERENCES_WEIGHT_KEY, Context.MODE_PRIVATE);
        String weightString = sharedPref.getString(WEIGHT_KEY, "0");
        float weight = Float.parseFloat(weightString);
        // 0.789 comes from alcohol density (789 kg/m3)
        bac = (float) ((0.789 * totalAlcohol)/(0.68 * weight * 1000)) * (float)105.5; // g/dL

        TextView bacView = new TextView(this);
        bacView.setId(2222+2);
        bacView.setText(String.format(java.util.Locale.US,"%.3f", bac));
        //Change color of BAC from green to red based on BAC value
        if (bac < 0.03) {
            bacView.setTextColor(Color.parseColor("#009933"));
        } else if (bac < 0.06) {
            bacView.setTextColor(Color.parseColor("#e6e600"));
        } else if (bac < 0.1) {
            bacView.setTextColor(Color.parseColor("#e6ac00"));
        } else if (bac < 0.2) {
            bacView.setTextColor(Color.parseColor("#ff3300"));
        } else if (bac < 0.3) {
            bacView.setTextColor(Color.parseColor("#b30000"));
        } else if (bac < 0.4) {
            bacView.setTextColor(Color.parseColor("#990099"));
        } else if (bac < 0.5) {
            bacView.setTextColor(Color.parseColor("#0000cc"));
        } else {
            bacView.setTextColor(Color.parseColor("#000080"));
        }
        bacView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 38);
        RelativeLayout.LayoutParams bacViewParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bacViewParams.addRule(RelativeLayout.BELOW, R.id.bac_text);
        bacViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bacView.setLayoutParams(bacViewParams);

        TextView bacPercent = new TextView(this);
        bacPercent.setText(new String("% (g/dL)"));
        bacPercent.setTextColor(Color.parseColor("#000000"));
        bacPercent.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        RelativeLayout.LayoutParams bacPercentParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bacPercentParams.addRule(RelativeLayout.BELOW, R.id.bac_text);
        bacPercentParams.addRule(RelativeLayout.RIGHT_OF, 2224);
        bacPercent.setLayoutParams(bacPercentParams);

        Context context = getApplicationContext();
        float factor = context.getResources().getDisplayMetrics().density;
        float pixels =  7 * factor;
        bacPercent.setPadding((int) (2 * factor), (int) (pixels * factor), 0, 0);

        RelativeLayout relativeLayout = findViewById(R.id.stats_layout);
        RelativeLayout leftLayout = findViewById(R.id.behavior_column);
        RelativeLayout rightLayout = findViewById(R.id.impairment_column);
        relativeLayout.addView(bacView);
        relativeLayout.addView(bacPercent);
        setContentView(relativeLayout);

        //Display the list of drinks had
        for (i=1; i<=drinksListSize; i++) {
            TextView drinkDescription = new TextView(this);
            ImageView image = new ImageView(this);
            String drinkType = drinksList.get(i-1).getType();
            if (drinkType.equals("Beer")) {
                image.setImageResource(R.drawable.beer);
            } else if (drinkType.equals("Wine")) {
                image.setImageResource(R.drawable.wine);
            } else if (drinkType.equals("Vodka/Gin")) {
                image.setImageResource(R.drawable.vodka);
            } else if (drinkType.equals("Cocktail")) {
                image.setImageResource(R.drawable.cocktail);
            } else if (drinkType.equals("Mona")) {
                image.setImageResource(R.drawable.mona);
            } else if (drinkType.equals("Custom Drink")) {
                image.setImageResource(R.drawable.potion);
            }
            String drinkDescriptionText = drinkType + ", " +
                    drinksList.get(i - 1).getQuantity() + " ml, " +
                    drinksList.get(i - 1).getAlcohol() + "%";
            drinkDescription.setText(drinkDescriptionText);
            drinkDescription.setTextColor(Color.parseColor("#000000"));

            image.setAdjustViewBounds(true);
            image.setId(1000+i);
            RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageParams.addRule(RelativeLayout.ALIGN_START, R.id.bac_text);

            RelativeLayout.LayoutParams drinkDescriptionParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            drinkDescriptionParams.addRule(RelativeLayout.RIGHT_OF, 1000+i);

            if (i == 1) {
                imageParams.addRule(RelativeLayout.BELOW, 2224);
                drinkDescriptionParams.addRule(RelativeLayout.BELOW, 2224);
            } else {
                imageParams.addRule(RelativeLayout.BELOW, 999+i);
                drinkDescriptionParams.addRule(RelativeLayout.BELOW, 999+i);
            }

            pixels =  18 * factor;
            imageParams.width = (int)(pixels * factor);
            imageParams.height = (int)(pixels * factor);
            //top padding for drink images
            pixels =  2 * factor;
            image.setPadding(0, (int) (pixels * factor), 0, 0);
            //top and left padding for drink description text
            pixels =  8 * factor;
            drinkDescription.setPadding((int) (pixels * factor)/2, (int) (pixels * factor), 0, 0);

            image.setLayoutParams(imageParams);
            drinkDescription.setLayoutParams(drinkDescriptionParams);

            if (i == drinksListSize) {
                //Headers
                RelativeLayout.LayoutParams leftLayoutParams = (RelativeLayout.LayoutParams) leftLayout.getLayoutParams();
                leftLayoutParams.addRule(RelativeLayout.BELOW, 1000+drinksListSize);
                leftLayout.setLayoutParams(leftLayoutParams);

                RelativeLayout.LayoutParams rightLayoutParams = (RelativeLayout.LayoutParams) rightLayout.getLayoutParams();
                rightLayoutParams.addRule(RelativeLayout.BELOW, 1000+drinksListSize);
                rightLayout.setLayoutParams(rightLayoutParams);

                //TextView that is a header for the "Behavior" column
                TextView behaviorHeader = new TextView(this);
                behaviorHeader.setId(3000+1);
                behaviorHeader.setText(new String("Behavior"));
                behaviorHeader.setTextColor(Color.parseColor("#000000"));
                behaviorHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                RelativeLayout.LayoutParams behaviorHeaderParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                behaviorHeaderParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                behaviorHeader.setLayoutParams(behaviorHeaderParams);
                pixels =  4 * factor;
                behaviorHeader.setPadding(0, (int) (pixels * factor), 0, 0);
                leftLayout.addView(behaviorHeader);

                //TextView that is a header for the "Impairment" column
                TextView impairmentHeader = new TextView(this);
                impairmentHeader.setId(3000+2);
                impairmentHeader.setText(new String("Impairment"));
                impairmentHeader.setTextColor(Color.parseColor("#000000"));
                impairmentHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                RelativeLayout.LayoutParams impairmentHeaderParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                impairmentHeaderParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                impairmentHeader.setLayoutParams(impairmentHeaderParams);
                pixels =  4 * factor;
                impairmentHeader.setPadding(0, (int) (pixels * factor), 0, 0);
                rightLayout.addView(impairmentHeader);

                //Behavior table contents
                TextView behavior = new TextView(this);
                behavior.setTextColor(Color.parseColor("#000000"));
                RelativeLayout.LayoutParams behaviorParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                behaviorParams.addRule(RelativeLayout.BELOW, 3001);
                behavior.setLayoutParams(behaviorParams);

                //Impairment table contents
                TextView impairment = new TextView(this);
                impairment.setTextColor(Color.parseColor("#000000"));
                RelativeLayout.LayoutParams impairmentParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                impairmentParams.addRule(RelativeLayout.BELOW, 3002);
                impairment.setLayoutParams(impairmentParams);

                if (bac < 0.03) {
                    behavior.setText(new String("- May appear normal"));
                    impairment.setText(new String("- Subtle effects detected with special tests"));
                } else if (bac < 0.06) {
                    behavior.setText(new String("- Decreased social inhibition\n" +
                            "- Joyousness\n" +
                            "- Mild euphoria\n" +
                            "- Relaxation\n" +
                            "- Increased verbosity"));
                    impairment.setText(new String("- Decreased attentional control"));
                } else if (bac < 0.1) {
                    behavior.setText(new String("- Alcohol flush reaction\n" +
                            "- Reduced affect display\n" +
                            "- Disinhibition\n" +
                            "- Euphoria\n" +
                            "- Extraversion\n" +
                            "- Increased pain tolerance"));
                    impairment.setText(new String("- Depth perception\n" +
                            "- Glare recovery\n" +
                            "- Peripheral vision\n" +
                            "- Reasoning"));
                } else if (bac < 0.2) {
                    behavior.setText(new String("- Analgesia\n" +
                            "- Ataxia\n" +
                            "- Boisterousness\n" +
                            "- Over-expressed emotions\n" +
                            "- Possibility of nausea and vomiting\n" +
                            "- Spins"));
                    impairment.setText(new String("- Gross motor skill\n" +
                            "- Motor planning\n" +
                            "- Reflexes\n" +
                            "- Slurred speech\n" +
                            "- Staggering\n" +
                            "- Temporary erectile dysfunction"));
                } else if (bac < 0.3) {
                    behavior.setText(new String("- Anger or sadness\n" +
                            "- Anterograde amnesia\n" +
                            "- Impaired sensations\n" +
                            "- Inhibited sexual desire\n" +
                            "- Mood swings\n" +
                            "- Nausea\n" +
                            "- Partial loss of understanding\n" +
                            "- Possibility of stupor\n" +
                            "- Vomiting"));
                    impairment.setText(new String("- Amnesia (memory blackout)\n" +
                            "- Unconsciousness\n" +
                            "- Severe physical disability"));
                } else if (bac < 0.4) {
                    behavior.setText(new String("- Central nervous system depression\n" +
                            "- Lapses in and out of consciousness\n" +
                            "- Loss of understanding\n" +
                            "- Low possibility of death\n" +
                            "- Pulmonary aspiration\n" +
                            "- Stupor"));
                    impairment.setText(new String("- Dysequilibrium\n" +
                            "- Breathing\n" +
                            "- Resting heart rate\n" +
                            "- Urinary incontinence"));
                } else if (bac < 0.5) {
                    behavior.setText(new String("- Coma\n" +
                            "- Possibility of death\n" +
                            "- Severe central nervous system depression"));
                    impairment.setText(new String("- Respiratory failure\n" +
                            "- Heart rate\n" +
                            "- Positional alcohol nystagmus"));
                } else {
                    behavior.setText(new String("- High possibility of death"));
                }
                leftLayout.addView(behavior);
                rightLayout.addView(impairment);
            }

            relativeLayout.addView(image);
            relativeLayout.addView(drinkDescription);
            setContentView(relativeLayout);
        }
    }
}