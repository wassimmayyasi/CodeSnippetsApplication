package controllers;

import models.Snippet;
import models.SnippetDataManager;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.json.simple.parser.ParseException;
import java.util.Arrays;
import java.util.List;

public class AddAndEditController {
    @FXML private JFXTextField nameTf, languageTf, tagsTf;
    @FXML private JFXTextArea descriptionTa, codeTa;

    void handleSaveNewSnippet() throws ParseException {
        Snippet snippet = parseUserInput();
        snippet.writeContentFile(codeTa.getText());
        SnippetDataManager.addSnippet(snippet);
    }

    private List<String> stringToTags(String tags){
        return Arrays.asList(tags.split(" "));
    }

    private Snippet parseUserInput() {
        return new Snippet(nameTf.getText(), descriptionTa.getText(),
                languageTf.getText(), stringToTags(tagsTf.getText()));

    }

}
