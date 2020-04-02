package controllers.utils;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.control.TextInputControl;
import javafx.scene.paint.Paint;


public class TextInputValidator {
    private ObservableList<TextInputControl> textInputs;


    public TextInputValidator(ObservableList<TextInputControl> textInputs) {
        this.textInputs = textInputs;
    }


    // Detects empty fields
    Boolean allFieldsFilled() {
        for(TextInputControl i : textInputs) {
            if(i.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }


    void markEmptyFields() {
        for(TextInputControl i : textInputs) {
            if(i.getText().isEmpty()) {
                underlineTextInput(i, "red");
            }
        }
    }


    // sets the unfocus color of JFXTextFields/ JFXTextAreas to specified color
    private void underlineTextInput(TextInputControl input, String color){
        if(input instanceof JFXTextField)
            ((JFXTextField) input).
                    setUnFocusColor(Paint.valueOf(color));
        else if(input instanceof JFXTextArea)
            ((JFXTextArea) input).
                    setUnFocusColor(Paint.valueOf(color));
        else
            throw new IllegalArgumentException();
    }


}
