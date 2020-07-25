package Interface;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configurations {
    private static JSONObject jsonObject;
    private static JSONObject jsonInterfaceObject;
    private static JSONParser parser;

    public static String getConfig(String Config) {
        jsonInterfaceObject = readJsonFile();
        return jsonInterfaceObject.get(Config).toString();
    }

    private static JSONObject readJsonFile() {
        parser = new JSONParser();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("E:\\Sexta-Feira-Mark_6\\Configurations.json"));
            jsonInterfaceObject = (JSONObject) jsonObject.get("InterfaceConfigs");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return jsonInterfaceObject;
    }
}