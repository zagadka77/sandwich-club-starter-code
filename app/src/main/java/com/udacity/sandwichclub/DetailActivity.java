package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        // Get all TextViews from activity_detail.xml
        TextView alsoKnownAsLabelTV = findViewById(R.id.also_known_label_tv);
        TextView alsoKnownAsTV = findViewById(R.id.also_known_tv);
        TextView originLabelTV = findViewById(R.id.origin_label_tv);
        TextView originTV = findViewById(R.id.origin_tv);
        TextView descriptionLabelTV = findViewById(R.id.description_label_tv);
        TextView descriptionTV = findViewById(R.id.description_tv);
        TextView ingredientsLabelTV = findViewById(R.id.ingredients_label_tv);
        TextView ingredientsTV = findViewById(R.id.ingredients_tv);

        // Set the alsoKnownAsTV TextView with data from the alsoKnownAs Sandwich property
        List<String> alsoKnown = sandwich.getAlsoKnownAs();
        if (alsoKnown.size() != 0) {
            for (int i = 0; i < alsoKnown.size(); i++) {
                alsoKnownAsTV.append(alsoKnown.get(i));
                if (i < alsoKnown.size() - 1) {
                    alsoKnownAsTV.append(", ");
                }
            }
        } else {
            // If this property was empty, print "n/a"
            alsoKnownAsTV.setText(getResources().getString(R.string.no_string));
        }

        // Set the originTV TextView with data from the placeOfOrigin Sandwich property
        String originString = sandwich.getPlaceOfOrigin();
        if (!originString.equals("")) {
            originTV.setText(originString);
        } else {
            // If this property was empty, print "n/a"
            originTV.setText(getResources().getString(R.string.no_string));
        }

        // Set the ingredientsTV TextView with data from the ingredients Sandwich property
        List<String> ingredients = sandwich.getIngredients();
        if (ingredients.size() != 0) {
            for (int i = 0; i < ingredients.size(); i++) {
                ingredientsTV.append(ingredients.get(i));
                if (i < ingredients.size() - 1) {
                    ingredientsTV.append(", ");
                }
            }
        } else {
            // If this property was empty, print "n/a"
            ingredientsTV.setText(getResources().getString(R.string.no_string));
        }

        // Set the descriptionTV TextView with data from the description Sandwich property
        String description = sandwich.getDescription();
        if (!description.equals("")) {
            descriptionTV.setText(description);
        } else {
            // If this property was empty, print "n/a"
            descriptionTV.setText(getResources().getString(R.string.no_string));
        }
    }
}
