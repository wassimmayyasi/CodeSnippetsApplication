package models;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SnippetManager {

    private static final SnippetManager INSTANCE = new SnippetManager();

    private JSONObject jsonObj;
    private SnippetConverter snippetConverter;
    private List<SnippetDataObserver> jsonObjObservers;


    private SnippetManager() {
        try {
            jsonObjObservers = new ArrayList<>();
            snippetConverter = new SnippetConverter();
            initializeJsonObject();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    // global (singleton) instance of this class
    public static SnippetManager instance(){
        return  INSTANCE;
    }


    // This parses the json file into a JSONObject and sets jsonObj
    private void initializeJsonObject()  throws IOException, ParseException {
        String jsonData = SnippetIO.read(SnippetIO.JSON_FILE_PATH);
        jsonObj = (JSONObject) new JSONParser().parse(jsonData);
    }


    //TODO: mention clone() used to return shallow copy of the original jsonObject to ensure that the
    // original is not modified by external classes
    JSONObject getJsonObj() {
        return (JSONObject) jsonObj.clone();
    }


    public void addSnippet(Snippet snippet, String code) throws ParseException, IOException {
        JsonPath.parse(jsonObj).add("$.snippets",
                snippetConverter.snippetToJsonObj(snippet));

        SnippetIO.write(snippet.getPath(), code);
        registerChange();
    }


    public void editSnippet(Snippet snippet, String code) throws ParseException, IOException {
        SnippetIO.write(snippet.getPath(), code);
        JSONObject newSnippet = snippetConverter.snippetToJsonObj(snippet);

        String jsonPathEdit = "$.snippets[?(@.date == '" +
                snippet.getDate() + "')]";

        JsonPath.parse(jsonObj).set(jsonPathEdit, newSnippet);
        registerChange();
    }


    // Switches the state of isFavourite to its opposite
    public void toggleFavouriteSnippet(Snippet snippet) throws IOException, ParseException {
        Boolean isFavourite = snippet.getFavourite();
        snippet.seIsFavourite(!isFavourite);

        // Call edit to register change in snippet
        editSnippet(snippet, snippet.getCode());
    }


    public void deleteSnippet(Snippet snippet) throws IOException {
        SnippetIO.deleteFile(snippet.getPath());

        String jsonPathDelete = "$.snippets[?(@.date == '" +
                snippet.getDate() + "')]";

        JsonPath.parse(jsonObj).delete(jsonPathDelete);
        registerChange();
    }


    private void registerChange() throws IOException {
        SnippetIO.write(SnippetIO.JSON_FILE_PATH, jsonObj.toJSONString());
        notifyObservers();
    }


    private void notifyObservers() throws IOException {
        for(SnippetDataObserver observer : jsonObjObservers)
            observer.contentsChanged();
    }


    public void addObserver(SnippetDataObserver observer) {
        jsonObjObservers.add(observer);
    }


    public void removeObserver(SnippetDataObserver observer) {
        jsonObjObservers.remove(observer);
    }


}
