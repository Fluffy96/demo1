package com.example.demo1;

/**
 * This class is the Subclass of Student that is used to create a Resident student

 * @author Divyesh Nemam Baskaran, Viraj Patel
 *
 */

public class Resident extends Student{
    /**Method is a constructor for students that are residents
     * @param name, major, credits
     * @returns none
     */
    public Resident(String name, Major major, int credits) {
        super(name, major, credits);
    }
    /**Method sets the values of tuition for residents
     * @Params none
     * @returns none
     */
    @Override
    public void tuitionDue(){

        if(getCredits() < MAX_PARTTIME_CREDITS){
            addTuition(RESIDENT_PARTTIME_PERCREDIT*getCredits());
            addTuition(UNIVERSITY_FEES*PARTTIME_UNIDISCOUNT);
        }
        else{
            addTuition(RESIDENT_TUITION);
            addTuition(UNIVERSITY_FEES);
            if(getCredits()>MAX_FULLTIME_CREDITS){
                addTuition(RESIDENT_PARTTIME_PERCREDIT*(getCredits()-MAX_FULLTIME_CREDITS));
            }
        }
        addTuition(-getFinancialAid());
        addTuition(-getPayment());
    }
    /**Method returns whether student is a resident
     * @Param none
     * @return True
     */
    @Override
    public boolean isResident(){
        return true;
    }
    /**Method returns toString of super class with added information of resident class
     * @Param none
     * @return string
     */
    @Override
    public String toString() {
        String out = super.toString();
        out += "resident";
        if(getFinancialAidStatus()){
            out += ":financial aid $" + getFinancialAid();
        }
        return out;
    }
}