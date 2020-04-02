package models.display_states;

import models.Snippet;
import java.util.List;

public interface DisplayState {

    List<Snippet> getDisplayData();

    List<Snippet> getSearchDisplayData(String searchStr);
}
