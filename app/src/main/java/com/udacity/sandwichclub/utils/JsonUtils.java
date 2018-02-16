package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String SW_NAME = "name";
    private static final String SW_MAIN_NAME = "mainName";
    private static final String SW_ALSO_KNOWN = "alsoKnownAs";
    private static final String SW_ORIGIN_PLACE = "placeOfOrigin";
    private static final String SW_DESCRIPTION = "description";
    private static final String SW_IMAGE = "image";
    private static final String SW_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        String mainName;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = new ArrayList<>();

        try {
            JSONObject sandwich = new JSONObject(json);
            JSONObject name = sandwich.getJSONObject(SW_NAME);
            mainName = name.getString(SW_MAIN_NAME);

            JSONArray alsoKnownArray = name.getJSONArray(SW_ALSO_KNOWN);
            if (alsoKnownArray != null) {
                int length = alsoKnownArray.length();
                for (int i=0; i<length; i++) {
                    alsoKnownAs.add(alsoKnownArray.get(i).toString());
                }
            }

            placeOfOrigin = sandwich.getString(SW_ORIGIN_PLACE);
            description = sandwich.getString(SW_DESCRIPTION);
            image = sandwich.getString(SW_IMAGE);

            JSONArray ingredientsArray = sandwich.getJSONArray(SW_INGREDIENTS);
            if (ingredientsArray != null) {
                int length = ingredientsArray.length();
                for (int i=0; i<length; i++) {
                    ingredients.add(ingredientsArray.get(i).toString());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

    }
}
