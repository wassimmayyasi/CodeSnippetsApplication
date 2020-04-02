package models.display_states;

import models.Snippet;
import models.SnippetSupplier;

import java.util.List;

public class DisplayFavouritesState implements DisplayState {
    private SnippetSupplier snippetSupplier;

    public DisplayFavouritesState() {
        snippetSupplier = new SnippetSupplier();
    }


    @Override
    public List<Snippet> getDisplayData() {
        return snippetSupplier.getFavouriteSnippets();
    }


    @Override
    public List<Snippet> getSearchDisplayData(String searchStr) {
        return snippetSupplier.getSearchResultsFavourites(searchStr);
    }
}
