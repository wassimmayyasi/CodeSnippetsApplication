package controllers.utils;

import controllers.FullSnippetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import models.Snippet;
import models.SnippetManager;

import java.io.IOException;
import java.util.Optional;


public class DialogOpener {
    private AddAndEditDialog addAndEditDialog;


    public DialogOpener() throws IOException {
        addAndEditDialog = new AddAndEditDialog();
    }


    // Opens an alert dialog and returns whether the user confirmed it or not
    public static Boolean userHasConfirmed(AlertType type, String message) {
        Alert alert = new Alert(type, message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }


    // Creates a dialog, sets its basic elements and returns it
    static Dialog createDialog(String title, FXMLLoader fxmlLoader) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(fxmlLoader.load());
        dialog.setTitle(title);
        return dialog;
    }


    // Opens the fullSnippetDialog which loads the FullSnippetView.
    // The gui elements on the view are handled by the FullSnippetController.
    public void openFullSnippetDialog(Snippet snippet) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("/views/FullSnippetView.fxml"));

        Dialog dialog = createDialog("Full snippet", fxmlLoader);

        // Obtains instance of FullSnippetController to access loadSnippetOntoView
        FullSnippetController fullSnippetController = fxmlLoader.getController();
        fullSnippetController.loadSnippetOntoView(snippet);

        // Ensures that the controller object is removed from the
        // observer list in SnippetManager when the dialog is closed
        dialog.setOnCloseRequest(event -> SnippetManager.instance().
                removeObserver(fullSnippetController));

        dialog.showAndWait();
    }


    public void openEditDialog(Snippet snippet) throws IOException {
        addAndEditDialog.
                getEditDialog(snippet).showAndWait();
    }


    public void openAddDialog() {
        addAndEditDialog.
                getAddDialog().showAndWait();
    }

}
