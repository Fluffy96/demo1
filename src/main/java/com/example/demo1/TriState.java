package com.example.demo1;

/**
 * This class is the Subclass of NonResident that is used to create TriState students

 * @author Divyesh Nemam Baskaran, Viraj Patel
 *
 */
public class TriState extends NonResident{
    private Tri triState;

    /**
     *Method is Constructor for tristate Students
     * @param name, major, credits, triState
     * @returns none
     */
    public TriState(String name, Major major, int credits, Tri triState) {
        super(name, major, credits);
        this.triState = triState;
    }
    /**Method sets the values of tuition for tristate students
     * @Params none
     * @returns none
     */
    @Override
    public void tuitionDue(){
        super.tuitionDue();
        if(triState.equals(Tri.NY) && getCredits() >= MAX_PARTTIME_CREDITS){
            addTuition(-NY_DISCOUNT);
        }
        if(triState.equals(Tri.CT) && getCredits() >= MAX_PARTTIME_CREDITS){
            addTuition(-CT_DISCOUNT);
        }
    }
    /**Method returns toString of super class with added information of tristate class
     * @Param none
     * @return string
     */
    @Override
    public String toString() {
        String out = super.toString();
        out += " (tri-state) :";
        if(triState.equals(Tri.NY)){
            out += "NY";
        }
        if(triState.equals(Tri.CT)){
            out += "CT";
        }
        return  out;
    }
}