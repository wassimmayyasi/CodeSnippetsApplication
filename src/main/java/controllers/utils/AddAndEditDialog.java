package controllers.utils;

import controllers.AddAndEditController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import models.Snippet;
import org.json.simple.parser.ParseException;
import java.io.IOException;


class AddAndEditDialog {
    private AddAndEditController addAndEditController;
    private TextInputValidator textInputValidator;
    private Dialog dialog;

    private enum Options {
        ADD, EDIT
    }


    AddAndEditDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("/views/AddAndEditView.fxml"));

        dialog = DialogOpener.createDialog("", fxmlLoader);
        addAndEditController = fxmlLoader.getController();
        textInputValidator = addAndEditController.getTextInputValidator();

    }


    // Called by assignDialogActions to display a warning about empty fields
    // It marks the empty fields and consumes the event
    private void preventDialogFromClosing(Event event) {
        // Notify user that not all fields are filled
        new Alert(Alert.AlertType.WARNING,
                "Make sure all fields are filled").showAndWait();

        textInputValidator.markEmptyFields();

        // Prevent dialog from closing
        event.consume();
    }


    private Dialog assignDialogActions(String title, Options chosenOption) {
        dialog.setTitle(title);

        dialog.setOnCloseRequest(event -> {

            if (dialog.getResult() == ButtonType.APPLY) {
                // Prevents from alert showing when X button clicked after Apply
                dialog.setResult(null);

                if (!textInputValidator.allFieldsFilled()){
                    preventDialogFromClosing(event);
                }
                else {
                    try {
                        // Call function within the views controller to save changes
                        if (chosenOption == Options.ADD) {
                            addAndEditController.handleSaveNewSnippet();
                        }
                        else {
                            addAndEditController.handleSaveEditedSnippet();
                        }

                    } catch (ParseException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return dialog;
    }


    // Returns a dialog that handles editing functionality
    Dialog getEditDialog(Snippet snippet) throws IOException {
        addAndEditController.loadEditData(snippet);
        return assignDialogActions("Edit snippet", Options.EDIT);
    }


    // Returns a dialog that handles adding functionality
    Dialog getAddDialog() {
        return assignDialogActions("Add new snippet", Options.ADD);
    }


}

