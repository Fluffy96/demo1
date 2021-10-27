package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Button addStudent;
    @FXML
    private TextField studentProfileOutput;

    @FXML
    protected void addClicked() {
        studentProfileOutput.setText("HI");

    }
}