package com.example.demo1;

/**
 * This class is the Subclass of Student that is used to create NonResident students

 * @author Divyesh Nemam Baskaran, Viraj Patel
 *
 */
public class NonResident extends Student{

    /**
     * Method constructor for nonresidents in student class
     * @param name, major, credits
     * @returns none
     */
    public NonResident(String name, Major major, int credits) {
        super(name, major, credits);
    }
    /**Method sets the values of tuition for nonresidents
     * @Params none
     * @returns none
     */
    @Override
    public void tuitionDue(){
        if(getCredits() < MAX_PARTTIME_CREDITS){
            addTuition(NONRESIDENT_PARTTIME_PERCREDIT*getCredits());
            addTuition(UNIVERSITY_FEES*PARTTIME_UNIDISCOUNT);
        }
        else{
            addTuition(NONRESIDENT_TUITION);
            addTuition(UNIVERSITY_FEES);
            if(getCredits()>MAX_FULLTIME_CREDITS){
                addTuition(NONRESIDENT_PARTTIME_PERCREDIT*(getCredits()-MAX_FULLTIME_CREDITS));
            }
        }
        addTuition(-getPayment());
    }
    /**Method returns toString of super class with added information of nonresident class
     * @Param none
     * @return string
     */
    @Override
    public String toString() {
        String out = super.toString();
        out += "non-resident";
        return out;
    }
}