package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

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
            JSONObject name = sandwichJson.getJSONObject("name");

            // Extract the mainName String
            mainName = name.getString("mainName");

            // Extract the alsoKnownAs array of Strings
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            if (alsoKnownAsArray.length() > 0) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsArray.getString(i));
                }
            }

            // Extract the placeOfOrigin String
            placeOfOrigin = sandwichJson.getString("placeOfOrigin");

            // Extract the description String
            description = sandwichJson.getString("description");

            // Extract image url String
            image = sandwichJson.getString("image");

            // Extract the ingredients array of Strings
            JSONArray ingredientsArray = sandwichJson.getJSONArray("ingredients");
            if (ingredientsArray.length() > 0) {
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    ingredients.add(ingredientsArray.getString(i));
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
