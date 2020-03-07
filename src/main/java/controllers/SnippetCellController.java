package controllers;

import models.Snippet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class SnippetCellController extends ListCell<Snippet> {
    @FXML private Label nameLbl, descriptionLbl;
    @FXML private VBox cellBase;

    @Override
    protected void updateItem(Snippet snippet, boolean empty) {
        super.updateItem(snippet, empty);

        if(empty || snippet == null) {
            setGraphic(null);
        }else {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/views/snippetCellView.fxml"));

                loader.setController(this);
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            nameLbl.setText(snippet.getName());
            descriptionLbl.setText(snippet.getDescription());

            setGraphic(cellBase);
        }
    }

    @FXML
    private void handleViewSnippet() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/FullSnippetView.fxml"));
            DialogPane dialogPane = fxmlLoader.load();

            FullSnippetController fullSnippetController = fxmlLoader.getController();
            fullSnippetController.loadSnippetOntoView(this.getItem());

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Full view");
            dialog.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
