package controllers;

import models.Snippet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.List;

public class FullSnippetController {
    @FXML Label nameLbl, languageLbl, tagsLbl, descriptionLbl, codeLbl, dateLbl;

    void loadSnippetOntoView(Snippet snippet) throws IOException {
        nameLbl.setText(snippet.getName());
        languageLbl.setText(snippet.getProgrammingLanguage());
        tagsLbl.setText(tagsToString(snippet.getTags()));
        descriptionLbl.setText(snippet.getDescription());
        codeLbl.setText(snippet.getContent());
        dateLbl.setText(snippet.getDate().toString());
    }

    private String tagsToString(List<String> tags) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String tag : tags) {
            stringBuilder.append(tag).append(" ");
        }
        return stringBuilder.toString();
    }
}
