
package com.friday;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FridayComunication {
    private static JSONObject jsonObject;
    private static JSONParser parser;

    public static JSONObject readJsonFile() {
        parser = new JSONParser();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(".\\src\\com\\friday\\FridayComunication.json"));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}