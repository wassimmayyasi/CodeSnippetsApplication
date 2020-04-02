package models;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;


class SnippetSearcher {

    static final String FAVOURITES_CONSTRAINTS = "&& @.isFavourite == true";
    private SnippetConverter snippetConverter;
    private SnippetManager snippetManager;

    SnippetSearcher() {
        snippetConverter = new SnippetConverter();
        snippetManager = SnippetManager.instance();
    }

    List<Snippet> getSearchResults(String searchStr, String additionalConstraint) {

        String regexStr = "/^.*" + searchStr + ".*$/i";

        String searchPath ="$.snippets[?((@.name =~ " + regexStr +
                " || @.description =~ " + regexStr +
                " || @.programmingLanguage =~ " + regexStr + " ) "+
                additionalConstraint + ")]";

        // Get all json objects that match the search path
        ArrayList<JSONObject> searchSnippetsJson =
                JsonPath.read(snippetManager.getJsonObj(), searchPath);


        performTagSearch(searchStr, additionalConstraint, searchSnippetsJson);

        return snippetConverter.jsonObjectListToSnippetList(searchSnippetsJson);
    }


    private void performTagSearch(String searchStr, String additionalConstraint,
                                  List<JSONObject> otherSearchResults) {
        // Get all existing tags
        ArrayList<String> allTags = JsonPath.read(snippetManager.
                getJsonObj(), "snippets[*].tags[*]");

        allTags.forEach(tag -> {
            // Collect all jsonObjects that have a tag matching current tag
            if (tag.matches("(.*)" + searchStr + "(.*)")){

                ArrayList<JSONObject> matchingSnippetObjects =
                        JsonPath.read(snippetManager.getJsonObj(),
                                "$.snippets[?('" + tag + "' in @.tags " + additionalConstraint + ")]");

                // If a matching object isn't already in matchingSnippetObjects add it
                matchingSnippetObjects.forEach(o -> {
                    if(!otherSearchResults.contains(o)) {
                        otherSearchResults.add(o);
                    }
                });
            }
        });
    }


}
