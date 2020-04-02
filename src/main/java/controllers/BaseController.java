package controllers;

import com.jfoenix.controls.JFXComboBox;
import controllers.utils.DialogOpener;
import javafx.scene.control.*;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import models.*;
import models.SnippetDataObserver;
import models.display_states.DisplayAllState;
import models.display_states.DisplayFavouritesState;
import models.display_states.DisplayState;
import models.snippet_comparators.*;

import java.io.IOException;
import java.util.*;


public class BaseController implements SnippetDataObserver {
    @FXML private JFXListView snippetsLV; // Displays snippets according to current state
    @FXML private TextField searchBarTF;
    @FXML private JFXComboBox<String> sortingCbx;

    private DisplayState currentState;
    private DisplayState displayAllState;
    private DisplayState displayFavouritesState;


    public void initialize() {
        SnippetManager.instance().addObserver(this);
        snippetsLV.setCellFactory(s -> new DisplayEntryController());

        // Initializes sorting box with choices and sets the default
        sortingCbx.getItems().addAll(Arrays.asList("Date Added", "Name", "Language"));
        sortingCbx.setValue(sortingCbx.getItems().get(0));

        displayAllState = new DisplayAllState();
        displayFavouritesState = new DisplayFavouritesState();
        currentState = displayAllState;

        loadDisplayData();
    }


    @FXML
    // onAction function of "Add" button
    private void handleAddSnippet() throws IOException {
        new DialogOpener().openAddDialog();
    }


    @FXML
    // onAction function of "Favourites" button
    private void handleDisplayFavourites() {
        currentState = displayFavouritesState;
        searchBarTF.clear();
        loadDisplayData();
    }


    @FXML
    // onAction function of "All" button
    private void handleDisplayAll() {
        currentState = displayAllState;
        searchBarTF.clear();
        loadDisplayData();
    }
    

    @Override
    // this function is called after add, edit and delete to refresh the display
    public void contentsChanged() {
        loadDisplayData();
    }


    @FXML
    // loads snippets onto the list view
    // Also onAction function for the sortingCbx
    private void loadDisplayData() {
        List<Snippet> displaySnippets;

        // if user didn't type search query, load full collection
        if(searchBarTF.getText().isEmpty()) {
            displaySnippets = currentState.getDisplayData();
        }

        else { // If user typed search query, load search results
            displaySnippets = currentState.
                    getSearchDisplayData(searchBarTF.getText());
        }


        // Sort display snippets on chosen setting user and display them
        sortDisplaySnippets(displaySnippets);
        snippetsLV.getItems().setAll(displaySnippets);
    }


    private void sortDisplaySnippets(List<Snippet> displaySnippets) {
        String choice = sortingCbx.getValue();

        switch(choice) {
            case "Date Added":
                displaySnippets.
                        sort(new SnippetsSortByDate());
                break;
            case "Name":
                displaySnippets.
                        sort(new SnippetsSortByName());
                break;
            case "Language":
                displaySnippets.
                        sort(new SnippetsSortByLanguage());
                break;

        }

    }


}
