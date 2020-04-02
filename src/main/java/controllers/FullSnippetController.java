package controllers;

import controllers.utils.DialogOpener;
import javafx.scene.control.Label;
import models.SnippetDataObserver;
import models.Snippet;
import javafx.fxml.FXML;
import models.SnippetManager;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;


public class FullSnippetController implements SnippetDataObserver {
    @FXML private Label nameLbl;
    @FXML private Label languageLbl;
    @FXML private Label tagsLbl;
    @FXML private Label descriptionLbl;
    @FXML private Label codeLbl;
    @FXML private Label dateLbl;

    private Snippet shownSnippet;


    public void initialize() {
        SnippetManager snippetManager = SnippetManager.instance();
        snippetManager.addObserver(this);
    }

    // Fills all Labels with information from given Snippet instance
    public void loadSnippetOntoView(Snippet shownSnippet) throws IOException {
        this.shownSnippet = shownSnippet;

        nameLbl.setText(shownSnippet.getName());
        dateLbl.setText(shownSnippet.getDate());
        languageLbl.setText(shownSnippet.getProgrammingLanguage());
        descriptionLbl.setText(shownSnippet.getDescription());
        codeLbl.setText(shownSnippet.getCode());
        tagsLbl.setText(String.join(" ", shownSnippet.getTags()));
    }


    @FXML
    // Copies the code of a snippet onto clipboard
    private void handleCopySnippetCode() throws IOException {
        StringSelection stringSelection = new StringSelection(shownSnippet.getCode());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }


    @FXML
    private void handleEditSnippet() throws IOException {
        new DialogOpener().openEditDialog(shownSnippet);
    }


    @Override
    // Called by SnippetManager when contents of data change
    public void contentsChanged() throws IOException {
        loadSnippetOntoView(shownSnippet);
    }
}
