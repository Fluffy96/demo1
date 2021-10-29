package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button addStudent;
    @FXML
    private TextField profileOutput, inputName;
    @FXML
    private ToggleGroup Majors,rVSnr;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onAddClick() {
        String name = inputName.getText();
        if(!name.equals("")) {
            String maj = returnMajor();
            if (maj != null) {
                String resType = returnRes();
                if(resType!= null){
                    if(resType.equals("Resident")){

                    }else{
                        //check for international or tristate followed by check for credits
                    }

                }else {
                    profileOutput.setText("Have not clicked Non Resident or Resident");
                }
            } else {
                profileOutput.setText("Have not clicked a Major");
            }
        }else{
            profileOutput.setText("No name provided");
        }
    }
    private String returnMajor(){
        try {
            RadioButton selectedRadioButton = (RadioButton) Majors.getSelectedToggle();
            return(selectedRadioButton.getText());
        }catch(NullPointerException e){
            return(null);
        }
    }
    private String returnRes(){
        try {
            RadioButton selectedRadioButton = (RadioButton) rVSnr.getSelectedToggle();
            return(selectedRadioButton.getText());
        }catch(NullPointerException e){
            return(null);
        }
    }
    @FXML
    protected void onRemoveClick() {
        profileOutput.setText("HI");
    }
}