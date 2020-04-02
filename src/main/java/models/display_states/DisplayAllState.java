package models.display_states;

import models.Snippet;
import models.SnippetSupplier;

import java.util.List;

public class DisplayAllState implements DisplayState {
    private SnippetSupplier snippetSupplier;

    public DisplayAllState() {
        snippetSupplier = new SnippetSupplier();
    }

    @Override
    public List<Snippet> getDisplayData() {
        return snippetSupplier.getAllSnippets();
    }

    @Override
    public List<Snippet> getSearchDisplayData(String searchStr) {
        return snippetSupplier.getSearchResultsAll(searchStr);
    }
}
