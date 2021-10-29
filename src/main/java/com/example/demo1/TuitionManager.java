package com.example.demo1;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import com.example.demo1.Student.Major;
import com.example.demo1.Student.Tri;
import com.example.demo1.Roster;

public class TuitionManager {

    private String command;
    private String name;
    private int credits;
    private Major major;
    private Tri triState;
    private double aid;
    private double payment;
    private boolean isStudyAbroad;

    private Roster roster;

    private static final int NOT_FOUND = -1;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;

    /**
     * Method is a runner class for tuition manager
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        roster = new Roster();
        System.out.println("Tuition Manager Starts Running");
        while(true){
            String input = scanner.nextLine();
            StringTokenizer c =new StringTokenizer(input,",");
            int tokens = c.countTokens();
            command = c.nextToken();
            switch(command) {
                case "AR":
                    if (tokens == FOUR){
                        AR(c);
                    }
                    if (tokens == TWO){
                        System.out.println("Missing data in command line.");
                    }
                    if (tokens == THREE){
                        System.out.println("Credit hours missing.");
                    }
                    break;
                case "AN":
                    if (tokens == FOUR){
                        AN(c);
                    }
                    if (tokens == TWO){
                        System.out.println("Missing data in command line.");
                    }
                    if (tokens == THREE){
                        System.out.println("Credit hours missing.");
                    }
                    break;
                case "AT":
                    if (tokens == FIVE){
                        AT(c);
                    }
                    if (tokens == TWO || tokens == FOUR){
                        System.out.println("Missing data in command line.");
                    }
                    if (tokens == THREE){
                        System.out.println("Credit hours missing.");
                    }
                    break;
                case "AI":
                    if (tokens == FIVE){
                        AI(c);
                    }
                    if (tokens == TWO || tokens == FOUR){
                        System.out.println("Missing data in command line.");
                    }
                    if (tokens == THREE){
                        System.out.println("Credit hours missing.");
                    }
                    break;
                case "C":
                    System.out.println("Calculations completed.");
                    roster.calculations();
                    break;
                case "R":
                    if (tokens == THREE){
                        R(c);
                    }
                    if (tokens == TWO){
                        System.out.println("Missing data in command line.");
                    }
                    break;
                case "F":
                    if (tokens == FOUR){
                        F(c);
                    }
                    if (tokens == TWO){
                        System.out.println("Missing data in command line.");
                    }
                    if (tokens == THREE){
                        System.out.println("Missing the amount.");
                    }
                    break;
                case "T":
                    if (tokens == FIVE){
                        T(c);
                    }
                    if (tokens == TWO){
                        System.out.println("Missing data in command line.");
                    }
                    if (tokens == FOUR){
                        System.out.println("Payment date invalid.");
                    }
                    if (tokens == THREE){
                        System.out.println("Credit hours missing.");
                    }
                    break;
                case "S":
                    S(c);
                    break;
                case "P":
                    roster.print();
                    break;
                case "PT":
                    roster.printByDate();
                    break;
                case "PN":
                    roster.printByName();
                    break;
                case "Q":
                    System.out.println("Tuition Manager terminated.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command \'"+command+"\' not supported!");
                    break;
            }
        }

    }

    /**
     * Method adds resident in roster
     * @param c
     * @returns none
     */
    private void AR(StringTokenizer c){
        name = c.nextToken();
        major = toMajor(c.nextToken());
        try {
            credits = Integer.parseInt(c.nextToken());
            if (major!= null && creditValidator(credits)) {
                if(roster.inRoster(new Student(name,major,credits))==NOT_FOUND) {
                    roster.add(new Resident(name, major, credits));
                    System.out.println("Student added.");
                }
                else {
                    System.out.println("Student is already in the roster.");
                }
            }
        }catch (NumberFormatException e) {
            System.out.println("Invalid credit hours.");
        }
    }
    /**
     * Method adds nonresident students in roster
     * @param c
     * @returns none
     */
    private void AN(StringTokenizer c){
        name = c.nextToken();
        major = toMajor(c.nextToken());
        try {
            credits = Integer.parseInt(c.nextToken());
            if (major != null && creditValidator(credits)) {
                if(roster.inRoster(new Student(name,major,credits))==NOT_FOUND) {
                    roster.add(new NonResident(name, major, credits));
                    System.out.println("Student added.");
                }
                else {
                    System.out.println("Student is already in the roster.");
                }
            }
        }catch (NumberFormatException e) {
            System.out.println("Invalid credit hours.");
        }
    }
    /**
     * Method adds tristate students in roster
     * @param c
     * @returns none
     */
    private void AT(StringTokenizer c){
        name = c.nextToken();
        major = toMajor(c.nextToken());
        try {
            credits = Integer.parseInt(c.nextToken());
            triState = toTriState(c.nextToken());
            if(triState != null) {
                if (major != null && creditValidator(credits)) {
                    if (roster.inRoster(new Student(name, major, credits)) == NOT_FOUND) {
                        roster.add(new com.example.demo1.TriState(name, major, credits, triState));
                        System.out.println("Student added.");
                    } else {
                        System.out.println("Student is already in the roster.");
                    }
                }
            }
        }catch (NumberFormatException e) {
            System.out.println("Invalid credit hours.");
        }
    }
    /**
     * Method adds International students in roster
     * @param c
     * @returns none
     */
    private void AI(StringTokenizer c){
        name = c.nextToken();
        major = toMajor(c.nextToken());
        try {
            credits = Integer.parseInt(c.nextToken());
            isStudyAbroad = Boolean.parseBoolean(c.nextToken());
            if (major != null && creditValidator(credits, isStudyAbroad)) {
                if(roster.inRoster(new Student(name,major,credits))==NOT_FOUND) {
                    roster.add(new com.example.demo1.International(name, major, credits, isStudyAbroad));
                    System.out.println("Student added.");
                }
                else {
                    System.out.println("Student is already in the roster.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid credit hours.");
        }
    }
    /**
     * Method removes students in roster
     * @param c
     * @returns none
     */
    private void R(StringTokenizer c){
        name = c.nextToken();
        major = toMajor(c.nextToken());
        if(roster.remove(new Student(name,major,0))){
            System.out.println("Student removed from the roster.");
        }
        else{
            System.out.println("Student is not in the roster.");
        }
    }
    /**
     * Method gives financial aid to residents
     * @param c
     * @returns none
     */
    private void F(StringTokenizer c){
        name = c.nextToken();
        major = toMajor(c.nextToken());
        try {
            aid = Double.parseDouble(c.nextToken());
            System.out.println(roster.financialAid(name,major,aid));
        } catch (NumberFormatException e) {
            System.out.println("Missing the amount.");
        }
    }
    /**
     * Method does tuition payments for any students
     * @param c
     * @returns none
     */
    private void T(StringTokenizer c){
        name = c.nextToken();
        major = toMajor(c.nextToken());
        try {
            payment = Double.parseDouble(c.nextToken());
            Date date = new Date(c.nextToken());
            System.out.println(roster.tuitionPayment(date, payment, new Student(name,major)));
        } catch (NumberFormatException e) {
            System.out.println("Missing the amount.");
        }
    }
    /**
     * Method allows you to change whether an international student is studying abroad
     * @param c
     * @returns none
     */
    private void S(StringTokenizer c){
        name = c.nextToken();
        major = toMajor(c.nextToken());
        try {
            boolean isAbroad = Boolean.parseBoolean(c.nextToken());
            System.out.println(roster.changeStudyAbroad(new Student(name,major),isAbroad));
        } catch (NumberFormatException e) {
            System.out.println("Missing data on command line.");
        }
    }


    /**
     * Method returns a major based on input
     * @param input
     * @returns major
     */
    private Major toMajor (String input){
        switch (input.toUpperCase()){
            case "EE":
                return Major.EE;
            case "CS":
                return Major.CS;
            case "BA":
                return Major.BA;
            case "IT":
                return Major.IT;
            case "ME":
                return Major.ME;
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
    /**
     * Method returns tristate enum based on string input
     * @param triState
     * @returns Tri
     */
    private Tri toTriState (String triState){
        switch (triState.toUpperCase()){
            case "CT":
                return Tri.CT;
            case "NY":
                return Tri.NY;
            default:
                System.out.println("Not part of the tri-state area.");
                return null;
        }
    }

}