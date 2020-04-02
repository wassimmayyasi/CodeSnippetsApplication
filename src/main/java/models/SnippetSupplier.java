package models;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;

//TODO: add comments explaining the purpose of this class
public class SnippetSupplier {
    private SnippetManager snippetManager;
    private SnippetSearcher snippetSearcher;
    private SnippetConverter snippetConverter;


    public SnippetSupplier() {
       snippetManager = SnippetManager.instance();
       snippetSearcher = new SnippetSearcher();
       snippetConverter = new SnippetConverter();
    }


    public List<Snippet> getAllSnippets() {
        ArrayList<JSONObject> allSnippetsJson =
                JsonPath.read(snippetManager.getJsonObj(), "$.snippets");

        return snippetConverter.jsonObjectListToSnippetList(allSnippetsJson);
    }


    public List<Snippet> getFavouriteSnippets() {
        ArrayList<JSONObject> allSnippetsJson = JsonPath.read(snippetManager.
                        getJsonObj(), "$.snippets[?(@.isFavourite == true)]");

        return snippetConverter.jsonObjectListToSnippetList(allSnippetsJson);
    }


    public List<Snippet> getSearchResultsFavourites(String searchStr) {
        return snippetSearcher.getSearchResults(searchStr, SnippetSearcher.FAVOURITES_CONSTRAINTS);
    }


    public List<Snippet> getSearchResultsAll(String searchStr) {
        return snippetSearcher.getSearchResults(searchStr, " ");
    }


}
