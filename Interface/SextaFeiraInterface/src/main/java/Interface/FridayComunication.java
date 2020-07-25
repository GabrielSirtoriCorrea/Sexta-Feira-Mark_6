package Interface;

public class FridayComunication {
    private static JSONObject jsonObject;
    private static JSONParser parser;

    private static JSONObject readJsonFile() {
        parser = new JSONParser();

        try {
            jsonObject = (JSONObject) parser.parse(new FileReader("E:\\Sexta-Feira-Mark_6\\Interface\\SextaFeiraInterface\\src\\main\\FridayComunication.json"));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}