package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.demo1.Student.Major;
import com.example.demo1.Student.Tri;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button addStudent;
    @FXML
    private TextField inputName, creditHours;
    @FXML
    private TextArea profileOutput;
    @FXML
    private ToggleGroup Majors, Students, State;
    @FXML
    private RadioButton isRes, isNonRes, isTristate, isNY, isCT, isInternational;
    @FXML
    private CheckBox isAbroad;

    private static final double INVISIBLE = 0.60;
    private static final double VISIBLE = 1.00;

    private Roster roster = new Roster();

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onAddClick() {
        String name = inputName.getText();
        profileOutput.setText("");
        if(!name.equals("")) {
            String maj = returnMajor();
            if (maj != null) {
                Major major = toMajor(maj);
                String studentType = returnStudent();
                if(studentType!= null){
                    int credits = returnCredit();
                    if(credits != -1 && creditValidator(credits)){
                        if(studentType.equals("Resident")){
                            roster.add(new Resident(name,major,credits));
                        }else if(studentType.equals("Non-Resident")){
                            roster.add(new NonResident(name,major,credits));
                        }else if(studentType.equals("Tristate")){
                            if(returnState()!=null){
                                roster.add(new TriState(name,major,credits,returnState()));
                            }else{
                                profileOutput.setText("Have not selected State");
                            }
                        }else if(studentType.equals("International")){

                        }
                    }
                    else{
                        profileOutput.setText("Invalid Credit Hours");
                    }

                }else {
                    profileOutput.setText("Have not clicked Student Type");
                }
            } else {
                profileOutput.setText("Have not clicked a Major");
            }
        }else{
            profileOutput.setText("No name provided");
        }
    }
    @FXML
    protected void onResidentClick(){
        isNY.setOpacity(INVISIBLE);
        isCT.setOpacity(INVISIBLE);
        isAbroad.setOpacity(INVISIBLE);
        isNY.setDisable(true);
        isCT.setDisable(true);
        isAbroad.setDisable(true);
        isTristate.setSelected(false);
        isNY.setSelected(false);
        isCT.setSelected(false);
        isInternational.setSelected(false);
        isAbroad.setSelected(false);
    }

    @FXML
    protected void onNonResidentClick(){
        isTristate.setOpacity(VISIBLE);
        isNY.setOpacity(INVISIBLE);
        isCT.setOpacity(INVISIBLE);
        isInternational.setOpacity(VISIBLE);
        isAbroad.setOpacity(INVISIBLE);
        isTristate.setDisable(false);
        isInternational.setDisable(false);
        isNY.setDisable(true);
        isCT.setDisable(true);
        isAbroad.setDisable(true);
        isInternational.setSelected(false);
        isNY.setSelected(false);
        isCT.setSelected(false);
        isAbroad.setSelected(false);
        isTristate.setSelected(false);
    }

    @FXML
    protected void onTriStateClick(){
        isTristate.setOpacity(VISIBLE);
        isNY.setOpacity(VISIBLE);
        isCT.setOpacity(VISIBLE);
        isInternational.setOpacity(VISIBLE);
        isAbroad.setOpacity(INVISIBLE);
        isTristate.setDisable(false);
        isInternational.setDisable(false);
        isNY.setDisable(false);
        isCT.setDisable(false);
        isAbroad.setDisable(true);
        isNonRes.setSelected(false);
        isNY.setSelected(false);
        isCT.setSelected(false);
        isAbroad.setSelected(false);
    }

    @FXML
    protected void onInternationalClick(){
        isTristate.setOpacity(VISIBLE);
        isNY.setOpacity(INVISIBLE);
        isCT.setOpacity(INVISIBLE);
        isInternational.setOpacity(VISIBLE);
        isAbroad.setOpacity(VISIBLE);
        isTristate.setDisable(false);
        isInternational.setDisable(false);
        isNY.setDisable(true);
        isCT.setDisable(true);
        isAbroad.setDisable(false);
        isNonRes.setSelected(false);
        isNY.setSelected(false);
        isCT.setSelected(false);
        isAbroad.setSelected(false);
    }

    private Tri returnState(){
        try {
            RadioButton selectedRadioButton = (RadioButton) State.getSelectedToggle();
            return(toTriState(selectedRadioButton.getText()));
        }catch(NullPointerException e){
            return(null);
        }
    }

    private int returnCredit(){
        try{
            return Integer.parseInt(creditHours.getText());
        }catch (NumberFormatException e){
            return -1;
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
    private String returnStudent(){
        try {
            RadioButton selectedRadioButton = (RadioButton) Students.getSelectedToggle();
            return(selectedRadioButton.getText());
        }catch(NullPointerException e){
            return(null);
        }
    }
    @FXML
    protected void onRemoveClick() {
        profileOutput.setText("HI");
    }
    /**
     * Method returns tristate enum based on string input
     * @param triState
     * @returns Tri
     */
    private Student.Tri toTriState (String triState){
        switch (triState.toUpperCase()){
            case "CT":
                return Student.Tri.CT;
            case "NY":
                return Student.Tri.NY;
            default:
                System.out.println("Not part of the tri-state area.");
                return null;
        }
    }

    /**
     * Method returns a major based on input
     * @param input
     * @returns major
     */
    private Student.Major toMajor (String input){
        switch (input.toUpperCase()){
            case "EE":
                return Student.Major.EE;
            case "CS":
                return Student.Major.CS;
            case "BA":
                return Student.Major.BA;
            case "IT":
                return Student.Major.IT;
            case "ME":
                return Student.Major.ME;
            default:
                System.out.println(input+" is not a valid major.");
                return null;
        }
    }

    /**
     * Method returns boolean based on if credits input is valid
     * @param credits
     * @returns boolean
     */
    private boolean creditValidator (int credits){
        if (credits < 0){
            System.out.println("Credit hours cannot be negative.");
            return false;
        }
        else if (credits < Student.MIN_CREDITS){
            System.out.println("Minimum credit hours is 3.");
            return false;
        }
        else if (credits > Student.MAX_CREDITS){
            System.out.println("Credit hours exceed the maximum 24.");
            return false;
        }
        else{
            return true;
        }
    }
    /**
     * Method returns boolean based on if credits input is valid for international students
     * @param credits
     * @returns boolean
     */
    private boolean creditValidator (int credits, boolean isStudyAbroad){
        if (credits < 0){
            System.out.println("Credit hours cannot be negative.");
            return false;
        }
        else if (credits < Student.MIN_CREDITS){
            System.out.println("Minimum credit hours is 3.");
            return false;
        }
        else if(!isStudyAbroad) {
            if (credits < Student.MAX_PARTTIME_CREDITS) {
                System.out.println("International students must enroll at least 12 credits.");
                return false;
            } else if (credits > Student.MAX_CREDITS) {
                System.out.println("Credit hours exceed the maximum 24.");
                return false;
            } else {
                return true;
            }
        }
        else if(credits > Student.MAX_PARTTIME_CREDITS){
            System.out.println("Credit hours exceed the maximum 12.");
            return false;
        }
        else{
            return true;
        }
    }

}