package controllers;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Snippet;
import models.SnippetDataManager;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BaseController {
    @FXML private JFXListView snippetsLV;
    private ObservableList<Snippet> displaySnippets;

    public void initialize() {
        displaySnippets = FXCollections.observableList(SnippetDataManager.getAllSnippets());
        displaySnippets.addListener((InvalidationListener)
                o -> snippetsLV.getItems().setAll(displaySnippets));

        snippetsLV.setCellFactory(s -> new SnippetCellController());
        snippetsLV.getItems().setAll(SnippetDataManager.getAllSnippets());
    }

    @FXML
    private void handleDisplay() {
        List<Snippet> updatedList = SnippetDataManager.getAllSnippets();
        displaySnippets.setAll(FXCollections.observableList(updatedList));
    }

    @FXML
    private void openAddDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/views/AddAndEditView.fxml"));
            DialogPane dialogPane = fxmlLoader.load();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle("Add new snippet");

            Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.APPLY){
                AddAndEditController controller = fxmlLoader.getController();
                controller.handleSaveNewSnippet();
                handleDisplay();
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }



}
