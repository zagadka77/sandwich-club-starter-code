package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String mainName;
        List<String> alsoKnownAs = null;
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = null;

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject name = sandwichJson.getJSONObject("name");

            // Set mainName
            mainName = name.getString("mainName");

            // Set alsoKnownAs
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsArray.getString(i));
            }

            // Set placeOfOrigin
            placeOfOrigin = sandwichJson.getString("placeOfOrigin");

            // Set description
            description = sandwichJson.getString("description");

            // Set image url
            image = sandwichJson.getString("image");

            // Set ingredients
            JSONArray ingredientsArray = name.getJSONArray("ingredients");
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
