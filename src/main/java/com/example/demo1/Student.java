package com.example.demo1;
import java.text.DecimalFormat;
/**
 * This class is the superclass for all different type of students

 * @author Divyesh Nemam Baskaran, Viraj Patel
 *
 */
public class Student {

    private Profile profile;
    private double tuition;
    private int credits;
    private Date date = null;
    private double payment;
    private double financialAid;
    private boolean awardedFA = false;
    private boolean isStudyAbroad;
    private DecimalFormat df = new DecimalFormat("0.00");
    public static enum Major {CS,IT,BA,EE,ME};
    public static enum Tri {NY,CT};

    public static final double RESIDENT_TUITION = 12536;
    public static final double NONRESIDENT_TUITION = 29737;
    public static final double INTERNATIONAL_TUITION = 29737;
    public static final double UNIVERSITY_FEES = 3268;
    public static final double ADDITIONAL_FEES = 2650;
    public static final double RESIDENT_PARTTIME_PERCREDIT = 404;
    public static final double NONRESIDENT_PARTTIME_PERCREDIT = 966;
    public static final double PARTTIME_UNIDISCOUNT = 0.8;
    public static final double NY_DISCOUNT = 4000;
    public static final double CT_DISCOUNT = 5000;
    public static final int MAX_CREDITS = 24;
    public static final int MIN_CREDITS = 3;
    public static final int MAX_PARTTIME_CREDITS = 12;
    public static final int MAX_FULLTIME_CREDITS = 16;
    public static final int MAX_FINANCIAL_AID = 10000;
    /** Constructor method for student class
     * @Params String name, Major major
     * @retrun void
     */
    public Student (String name, Major major){
        this.profile = new Profile(name,major);
    }
    /**Constructor method for student class including credits
     * @Params String name, Major major, int credits
     * @return void
     */
    public Student (String name, Major major, int credits){
        this.profile = new Profile(name,major);
        this.credits = credits;
    }
    /** Method returns whether or not the student is a resident
     * @Params none
     * @return false
     */
    public boolean isResident(){
        return false;
    }
    /** Method returns profile of a student
     * @Params none
     * @return profile
     */
    public Profile getProfile(){
        return profile;
    }
    /**Method returns the amount of financial aid received
     * @Params none
     * @return financialAid
     */
    public double getFinancialAid(){
        return financialAid;
    }
    /**Method that lets you set the value of study aborad for an international student
     * @Params studyAbroad
     * @return nothing
     */
    public void setStudyAbroad(boolean studyAbroad) {
        isStudyAbroad = false;
    }
    /**Method that returns value of study abroad
     * @Params none
     * @return false if not or if not international and True if you are international and abroad
     */
    public boolean getStudyAbroad(){
        return false;
    }
    /**Method sets how much financial aid a resident student received
     * @Params double financialAid
     * @return none
     */
    public void setFinancialAid(double financialAid) {
        this.financialAid = financialAid;
        addTuition(-financialAid);
        awardedFA = true;
    }

    /**Method returns how much tuition someone has left to pay
     * @Params None
     * @return tuition
     */
    public double getTuition() {
        return tuition;
    }
    /**Method adds to the value of tuition
     * @Params double money
     * @returns none
     */
    public void addTuition(double money){
        tuition += money;
    }
    /**Method returns whether a resident has received financial aid or not
     *@Params none
     *@returns awardedFA
     */
    public boolean getFinancialAidStatus() {
        return awardedFA;
    }
    /**Method returns how many credits a student has
     * @Params none
     * @returns credits
     */
    public int getCredits() {
        return credits;
    }
    /**Method sets the number of credits a student is taking
     * @Params int credits
     * @returns none
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }
    /**Method whether or not student is international
     * @Params none
     * @returns boolean
     */
    public boolean isInternational(){
        return false;
    }
    /**Method sets the total payment
     * @Params double payment
     * @returns none
     */
    public void setPayment(double payment) {
        this.payment = payment;
    }
    /**Method returns the total payment so far
     * @Param none
     * @returns payment
     */
    public double getPayment() {
        return payment;
    }
    /** Method sets tuition due
     * @Param double tuition
     * @returns none
     */
    public void setTuition(double tuition) {
        this.tuition = tuition;
    }
    /**Method sets the date of the last payment
     * @Param Date date
     * @return none
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**Method returns the date of the last payment
     * @Param none
     * @returns date
     */
    public Date getDate() {
        return  date;
    }
    /**Method allows to make a tuition payment
     * @Param double deposit, Date fate
     * @returns none
     */
    public void payment(double deposit, Date date){
        this.tuition -= deposit;
        this.payment += deposit;
        this.date = date;
    }
    /**Method allows to set how much tuition is due
     * @Param none
     * @returns none
     */
    public void tuitionDue(){

    }

    /**Method returns student credits, tuition, last payment, last pay date, name, and major
     * @Params none
     * @returns string
     */
    @Override
    public String toString() {
        String out = profile.toString();
        out += credits+" credit hours:tuition due:"+
                df.format(tuition)+":total payment:"+df.format(payment)+":last payment date: ";
        if(date == null){
            out += "--/--/--:";
        }
        else{
            out += date.toString()+":";
        }
        return out;
    }
}