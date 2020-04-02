package controllers;

import controllers.utils.TextInputValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import models.Snippet;
import models.SnippetManager;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class AddAndEditController {
    @FXML private JFXTextField nameTf;
    @FXML private JFXTextField languageTf;
    @FXML private JFXTextField tagsTf;
    @FXML private JFXTextArea descriptionTa;
    @FXML private JFXTextArea codeTa;
    @FXML private GridPane inputGrd;

    private SnippetManager snippetManager;
    private TextInputValidator textInputValidator;
    private Snippet snippetToEdit;


    public void initialize() {
        snippetManager = SnippetManager.instance();

        ObservableList<TextInputControl> inputs = FXCollections.observableArrayList();
        inputGrd.getChildren().forEach(n -> inputs.add((TextInputControl) n));
        textInputValidator = new TextInputValidator(inputs);
    }


    public TextInputValidator getTextInputValidator() {
        return textInputValidator;
    }


    public void handleSaveNewSnippet() throws ParseException, IOException {
        Snippet newSnippet = new Snippet(nameTf.getText(), descriptionTa.getText(),
                languageTf.getText(), stringToTags(tagsTf.getText()));

        snippetManager.addSnippet(newSnippet, codeTa.getText());
    }


    private List<String> stringToTags(String tags){
        return Arrays.asList(tags.split(" "));
    }


    public void loadEditData(Snippet snippetToEdit) throws IOException {
        this.snippetToEdit = snippetToEdit;

        nameTf.setText(snippetToEdit.getName());
        languageTf.setText(snippetToEdit.getProgrammingLanguage());
        tagsTf.setText(String.join(" ",snippetToEdit.getTags()));
        descriptionTa.setText(snippetToEdit.getDescription());
        codeTa.setText(snippetToEdit.getCode());
    }


    public void handleSaveEditedSnippet() throws ParseException, IOException {
        snippetToEdit.setName(nameTf.getText());
        snippetToEdit.setDescription(descriptionTa.getText());
        snippetToEdit.setProgrammingLanguage(languageTf.getText());
        snippetToEdit.setTags(stringToTags(tagsTf.getText()));

        snippetManager.editSnippet(snippetToEdit, codeTa.getText());
    }


}
