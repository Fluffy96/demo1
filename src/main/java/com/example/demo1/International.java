package com.example.demo1;

/**
 * This class is the Subclass of NonResident that is used to create an International Student

 * @author Divyesh Nemam Baskaran, Viraj Patel
 *
 */
public class International extends NonResident {

    private boolean isStudyAbroad;
    /**Method is a constructor for students that are international student
     * @param name, major, credits isStudyBorad
     * @returns none
     */
    public International(String name, Major major, int credits, boolean isStudyAbroad) {
        super(name, major, credits);
        this.setStudyAbroad(isStudyAbroad);
    }

    /**Method sets the values of tuition for international students
     * @Params none
     * @returns none
     */
    @Override
    public void tuitionDue() {
        if(!this.isStudyAbroad) {
            super.tuitionDue();
            if (getCredits() >= MAX_PARTTIME_CREDITS)
                addTuition(ADDITIONAL_FEES);
        } else{
            setTuition(UNIVERSITY_FEES+ADDITIONAL_FEES);
        }
    }
    /**Method sets the values if an international student is studying abroad
     * @Params isAbroad
     * @returns none
     */
    @Override
    public void setStudyAbroad(boolean isAbroad) {
        this.isStudyAbroad = isAbroad;
    }

    /**Method that returns value of study abroad
     * @Params none
     * @return false if not or if not international and True if you are international and abroad
     */
    @Override
    public boolean getStudyAbroad(){
        return this.isStudyAbroad;
    }

    /**Method returns whether student is an international
     * @Param none
     * @return True
     */
    @Override
    public boolean isInternational(){
        return true;
    }
    /**Method returns toString of super class with added information of international class
     * @Param none
     * @return string
     */
    @Override
    public String toString() {
        String out = super.toString();
        out += ":international";
        if (this.isStudyAbroad){
            out += ":study abroad";
        }
        return out;
    }
}