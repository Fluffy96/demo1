package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button addStudent;
    @FXML
    private TextField profileOutput;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onAddClick() {
        profileOutput.setText("HI");
    }
    @FXML
    protected void onremoveClick() {
        profileOutput.setText("HI");
    }
}