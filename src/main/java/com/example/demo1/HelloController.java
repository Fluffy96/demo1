package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.demo1.Student.Major;
import com.example.demo1.Student.Tri;

import java.time.LocalDate;
/**
 * This it the Controller class that controls what methods run based on user input on the GUI
 *
 * @author Divyesh Nemam Baskaran, Viraj Patel
 */
public class HelloController {
    private static final int NOT_FOUND = -1;
    @FXML
    private DatePicker isDate;
    @FXML
    private TextField inputName,tuitionField, creditHours, isPaymentName, isPaymentAmount, isFinancialAid;
    @FXML
    private TextArea profileOutput, paymentOutput, processOutput;
    @FXML
    private ToggleGroup Majors, Students, State, PaymentMajors;
    @FXML
    private RadioButton isRes, isNonRes, isTristate, isNY, isCT, isInternational;
    @FXML
    private CheckBox isAbroad;

    private static final double INVISIBLE = 0.60;
    private static final double VISIBLE = 1.00;
    private Roster roster = new Roster();

    /**
     * This method adds a student to the roster when the "Add" button is clicked on the first tab
     */
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
                            if(roster.inRoster(new Student(name,major,credits))==NOT_FOUND) {
                                roster.add(new Resident(name, major, credits));
                                profileOutput.setText("Student added.");
                            }else{
                                profileOutput.setText("Student is already in the roster.");
                            }
                        }else if(studentType.equals("Non-Resident")){
                            if(roster.inRoster(new Student(name,major,credits))==NOT_FOUND) {
                                roster.add(new NonResident(name, major, credits));
                                profileOutput.setText("Student added.");
                            }else{
                            profileOutput.setText("Student is already in the roster.");
                        }
                        }else if(studentType.equals("Tristate")){
                            if(returnState()!=null){
                                if(roster.inRoster(new Student(name,major,credits))==NOT_FOUND){
                                    roster.add(new TriState(name,major,credits,returnState()));
                                    profileOutput.setText("Student added.");
                                }else{
                                    profileOutput.setText("Student is already in the roster.");
                                }
                            }else{
                                profileOutput.setText("Have not selected State");
                            }
                        }else if(studentType.equals("International") && creditValidator(credits,isAbroad.isSelected())){
                            if(roster.inRoster(new Student(name,major,credits))==NOT_FOUND){
                                roster.add(new International(name,major,credits,isAbroad.isSelected()));
                                profileOutput.setText("Student added.");
                            }else{
                                profileOutput.setText("Student is already in the roster.");
                            }

                        }
                    }else{
                        if(creditHours.getText().equals("")) {
                            profileOutput.setText("Have not filled in Credits");
                        }else{
                            profileOutput.setText("Invalid credit hours.");
                        }
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

    /**
     * This method makes a tuition payment if the button is clicked on the second tab
     */
    @FXML
    protected void onChangeAbroadStatus(){
        String name = inputName.getText();
        profileOutput.setText("");
        if(!name.equals("")) {
            String maj = returnMajor();
            if (maj != null) {
                Major major = toMajor(maj);
                String output =roster.changeStudyAbroad(new Student(name,major), !roster.getStatus(new Student(name,major)));
                profileOutput.setText(output);
            } else {
                profileOutput.setText("Have not clicked a Major");
            }
        }else{
            profileOutput.setText("No name provided");
        }
    }
    /**
     * This method makes a tuition payment if the button is clicked on the second tab
     */
    @FXML
    protected void onPayClick(){
        String name = isPaymentName.getText();
        paymentOutput.setText("");
        if(!name.equals("")) {
            String maj = returnPaymentMajor();
            if (maj != null) {
                Major major = toMajor(maj);
                String payment = isPaymentAmount.getText();
                if(!payment.equals("")){
                    LocalDate date = isDate.getValue();
                    if(date != null){
                        Date d = new Date(date.toString());
                        double paymentAmount = Double.parseDouble(payment);
                        paymentOutput.setText(roster.tuitionPayment(d, paymentAmount, new Student(name,major)));
                    }else {
                        paymentOutput.setText("Have not selected date");
                    }
                }else{
                    paymentOutput.setText("Have not set payment amount");
                }
            } else {
                paymentOutput.setText("Have not clicked a Major");
            }
        }else{
            paymentOutput.setText("No name provided");
        }
    }

    /**
     * This method sets the financial aid amount if applicable to the desired student when clicked on
     */
    @FXML
    protected void onSetClick(){
        String name = isPaymentName.getText();
        paymentOutput.setText("");
        if(!name.equals("")) {
            String maj = returnPaymentMajor();
            if (maj != null) {
                Major major = toMajor(maj);
                String aid = isFinancialAid.getText();
                if(!aid.equals("")){
                    double aidAmount = Double.parseDouble(aid);
                    paymentOutput.setText(roster.financialAid(name, major, aidAmount));
                }else {
                    paymentOutput.setText("Missing Aid Amount");
                }
            } else {
                paymentOutput.setText("Have not clicked a Major");
            }
        }else{
            paymentOutput.setText("No name provided");
        }
    }

    /**
     * This button calculates the tuition of all the students in the roster
     */
    @FXML
    protected void onCalculateTuitionClick(){
        processOutput.setText("Calculations completed.");
        roster.calculations();
    }

    /**
     * This button prints the entire roster of students
     */
    @FXML
    protected void onPrintRosterClick(){
        roster.print(processOutput);
    }

    /**
     * This button prints out the roster sorted by the name of the students
     */
    @FXML
    protected void onPrintRosterByNameClick(){
        roster.printByName(processOutput);
    }

    /**
     * This button prints out the roster sorted by the payment dates of the students
     */
    @FXML
    protected void onPrintRosterByDateClick(){
        roster.printByDate(processOutput);
    }

    /**
     * This method controls the radio buttons when Resident is clicked
     */
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

    /**
     * This method controls the radio buttons when NonResident is clicked
     */
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

    /**
     * This method controls the radio buttons when TriState is clicked
     */
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

    /**
     * This method controls the radio buttons when International is clicked
     */
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

    /**
     * This method returns the name of the TriState selected
     * @return String - name of Tristate
     */
    private Tri returnState(){
        try {
            RadioButton selectedRadioButton = (RadioButton) State.getSelectedToggle();
            return(toTriState(selectedRadioButton.getText()));
        }catch(NullPointerException e){
            return(null);
        }
    }

    /**
     * This method returns the number of credit hours for a student
     * @return int - number of credit hours
     */
    private int returnCredit(){
        try{
            return Integer.parseInt(creditHours.getText());
        }catch (NumberFormatException e){
            return -1;
        }
    }

    /**
     * This method returns the name of the major selected
     * @return String - name of the major
     */
    private String returnMajor(){
        try {
            RadioButton selectedRadioButton = (RadioButton) Majors.getSelectedToggle();
            return(selectedRadioButton.getText());
        }catch(NullPointerException e){
            return(null);
        }
    }

    /**
     * This method returns name of a major selected int the Payment tab
     * @return String - Major of student in payment tab
     */
    private String returnPaymentMajor(){
        try {
            RadioButton selectedRadioButton = (RadioButton) PaymentMajors.getSelectedToggle();
            return(selectedRadioButton.getText());
        }catch (NullPointerException e){
            return (null);
        }
    }

    /**
     * This method returns the type of student selected
     * @return String - type of Student
     */
    private String returnStudent(){
        try {
            RadioButton selectedRadioButton = (RadioButton) Students.getSelectedToggle();
            return(selectedRadioButton.getText());
        }catch(NullPointerException e){
            return(null);
        }
    }

    /**
     * This method removes a student from the roster when the "Remove" button is clicked
     */
    @FXML
    protected void onRemoveClick() {
        String name = inputName.getText();
        profileOutput.setText("");
        if(!name.equals("")) {
            String maj = returnMajor();
            if (maj != null) {
                Major major = toMajor(maj);
                String studentType = returnStudent();
                if(roster.remove(new Student(name,major,0))){
                    profileOutput.setText("Student removed from the roster.");
                }
                else{
                    profileOutput.setText("Student is not in the roster.");
                }
            } else {
                profileOutput.setText("Have not clicked a Major");
            }
        }else{
            profileOutput.setText("No name provided");
        }
    }

    /**
     * This method calculates the tuition due of a student when clicked
     */
    @FXML
    protected void onTuitionDueClick(){
        String name = inputName.getText();
        profileOutput.setText("");
        if(!name.equals("")) {
            String maj = returnMajor();
            if (maj != null) {
                Major major = toMajor(maj);
                String studentType = returnStudent();
                if(roster.inRoster(new Student(name,major))!= NOT_FOUND){
                    Student tuit = new Student(name,major);
                    if(roster.amountDue(tuit)<=0.0){
                        roster.calculation(new Student(name,major));
                        tuitionField.setText(Double.toString(roster.amountDue(tuit)));
                    }else{
                        tuitionField.setText(Double.toString(roster.amountDue(tuit)));
                    }
                }
                else{
                    profileOutput.setText("Student is not in the roster.");
                }
            } else {
                profileOutput.setText("Have not clicked a Major");
            }
        }else{
            profileOutput.setText("No name provided");
        }
    }

    /**
     * Method returns tristate enum based on string input
     * @param triState
     * @returns Tri
     */
    private Student.Tri toTriState (String triState){
        switch (triState){
            case "Connecticut":
                return Student.Tri.CT;
            case "New York":
                return Student.Tri.NY;
            default:
                profileOutput.setText("Not part of the tri-state area.");
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
                profileOutput.setText(input+" is not a valid major.");
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
            profileOutput.setText("Credit hours cannot be negative.");
            return false;
        }
        else if (credits < Student.MIN_CREDITS){
            profileOutput.setText("Minimum credit hours is 3.");
            return false;
        }
        else if (credits > Student.MAX_CREDITS){
            profileOutput.setText("Credit hours exceed the maximum 24.");
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
            profileOutput.setText("Credit hours cannot be negative.");
            return false;
        }
        else if (credits < Student.MIN_CREDITS){
            profileOutput.setText("Minimum credit hours is 3.");
            return false;
        }
        else if(!isStudyAbroad) {
            if (credits < Student.MAX_PARTTIME_CREDITS) {
                profileOutput.setText("International students must enroll at least 12 credits.");
                return false;
            } else if (credits > Student.MAX_CREDITS) {
                profileOutput.setText("Credit hours exceed the maximum 24.");
                return false;
            } else {
                return true;
            }
        }
        else if(credits > Student.MAX_PARTTIME_CREDITS){
            profileOutput.setText("Credit hours exceed the maximum 12.");
            return false;
        }
        else{
            return true;
        }
    }

}