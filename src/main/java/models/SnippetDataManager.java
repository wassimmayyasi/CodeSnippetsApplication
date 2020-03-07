package models;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SnippetDataManager {

    private static final String SNIPPETS_JSON_FILE = "snippets.json";
    private static final String SNIPPETS_PATH = "$.snippets";

    private static JSONParser parser;
    private static JSONObject jsonObj;
    private static Gson gson;

    static {
        gson = new Gson();
        parser = new JSONParser();

        try {
            initializeJsonObject();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void initializeJsonObject()  throws IOException, ParseException {
        if(new File(SNIPPETS_JSON_FILE).exists()) {
            jsonObj = (JSONObject) parser.parse(new FileReader(SNIPPETS_JSON_FILE));
        } else {
            jsonObj = new JSONObject();
            jsonObj.put("snippets", new JSONArray());
        }
    }

    private static void updateFile() {
        try (FileWriter file = new FileWriter(SNIPPETS_JSON_FILE)) {
            file.write(jsonObj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addSnippet(Snippet snippet) throws ParseException {
        JsonPath.parse(jsonObj).add(SNIPPETS_PATH, snippetToJsonObj(snippet));
        updateFile();
    }

    public static List<Snippet> getAllSnippets(){
        ArrayList<JSONObject> allSnippetsJson = JsonPath.read(jsonObj, SNIPPETS_PATH);

        List<Snippet> allSnippets = new ArrayList<>();
        for (JSONObject jsonObj : allSnippetsJson) {
            allSnippets.add(jsonObjToSnippet(jsonObj));
        }

        return allSnippets;
    }

    private static Snippet jsonObjToSnippet(JSONObject jsonObj){
        return gson.fromJson(jsonObj.toJSONString(), Snippet.class);
    }

    private static JSONObject snippetToJsonObj(Snippet snippet) throws ParseException {
        return (JSONObject) parser.parse(gson.toJson(snippet));
    }

}
