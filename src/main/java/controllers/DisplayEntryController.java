package controllers;

import controllers.utils.DialogOpener;
import models.Snippet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.SnippetManager;
import org.json.simple.parser.ParseException;
import java.io.IOException;


public class DisplayEntryController extends ListCell<Snippet> {
    @FXML private Label nameLbl;
    @FXML private Label descriptionLbl;
    @FXML private Label languageLbl;
    @FXML private VBox cellBase;

    private SnippetManager snippetManager;


    public void initialize()  {
        snippetManager = SnippetManager.instance();
    }


    @Override
    protected void updateItem(Snippet snippet, boolean empty) {
        super.updateItem(snippet, empty);

        if(empty || snippet == null) {
            setGraphic(null);

        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().
                        getResource("/views/DisplayEntryView.fxml"));

                loader.setController(this);
                loader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }

            nameLbl.setText(snippet.getName());
            descriptionLbl.setText(snippet.getDescription());
            languageLbl.setText("Language: " + snippet.getProgrammingLanguage());

            setGraphic(cellBase);
        }
    }


    @FXML
    private void handleViewSnippet() throws IOException {
        new DialogOpener().openFullSnippetDialog(this.getItem());
    }


    @FXML
    private void handleDeleteSnippet() throws IOException {
        if(DialogOpener.userHasConfirmed(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete?")) {
            snippetManager.deleteSnippet(this.getItem());
        }
    }


    @FXML
    private void handleFavouritingSnippet() throws IOException, ParseException {
        snippetManager.toggleFavouriteSnippet(this.getItem());
    }

}
