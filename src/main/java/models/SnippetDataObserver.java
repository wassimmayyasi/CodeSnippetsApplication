package models;

import java.io.IOException;

public interface SnippetDataObserver {

    // callback function
    void contentsChanged() throws IOException;
}
