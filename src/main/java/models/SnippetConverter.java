package models;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

class SnippetConverter {
    private Gson gson;

    SnippetConverter() {
        gson = new Gson();
    }


    JSONObject snippetToJsonObj(Snippet snippet) throws ParseException {
        return (JSONObject) new JSONParser().parse(gson.toJson(snippet));
    }


    private Snippet jsonObjToSnippet(JSONObject jsonObj) {
        return gson.fromJson(jsonObj.toJSONString(), Snippet.class);
    }


    List<Snippet> jsonObjectListToSnippetList(List<JSONObject> jsonObjectList) {
        List<Snippet> snippetList = new ArrayList<>();
        jsonObjectList.forEach(o -> snippetList.add(jsonObjToSnippet(o)));
        return snippetList;
    }

}
