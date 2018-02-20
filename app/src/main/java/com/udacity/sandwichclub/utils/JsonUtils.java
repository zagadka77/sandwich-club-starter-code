package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    // JSON constants
    private final static String NAME_JSON = "name";
    private final static String MAIN_NAME_JSON = "mainName";
    private final static String ALSO_KNOWN_JSON = "alsoKnownAs";
    private final static String ORIGIN_JSON = "placeOfOrigin";
    private final static String DESCRIPTION_JSON = "description";
    private final static String IMAGE_JSON = "image";
    private final static String INGREDIENTS_JSON = "ingredients";


    public static Sandwich parseSandwichJson(String json) {
        String mainName;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = new ArrayList<>();

        try {
            // Create a new JSONObject from the string passed as parameter
            JSONObject sandwichJson = new JSONObject(json);

            // Extract the name object
            JSONObject name = sandwichJson.getJSONObject(NAME_JSON);

            // Extract the mainName String
            mainName = name.optString(MAIN_NAME_JSON);

            // Extract the alsoKnownAs array of Strings
            JSONArray alsoKnownAsArray = name.getJSONArray(ALSO_KNOWN_JSON);
            if (alsoKnownAsArray.length() > 0) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsArray.optString(i));
                }
            }

            // Extract the placeOfOrigin String
            placeOfOrigin = sandwichJson.optString(ORIGIN_JSON);

            // Extract the description String
            description = sandwichJson.optString(DESCRIPTION_JSON);

            // Extract image url String
            image = sandwichJson.optString(IMAGE_JSON);
            // If the image url String is empty we change it to a non-empty string so that Picasso
            // loads the error_placeholder instead of the loading_placeholder
            if (TextUtils.isEmpty(image)) {
                image = "error";
            }

            // Extract the ingredients array of Strings
            JSONArray ingredientsArray = sandwichJson.getJSONArray(INGREDIENTS_JSON);
            if (ingredientsArray.length() > 0) {
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    ingredients.add(ingredientsArray.optString(i));
                }
            }

            // Return a new Sandwich object with the just extracted properties
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // If there were problems, return a null object
        return null;
    }
}
