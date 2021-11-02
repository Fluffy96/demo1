package com.example.demo1;



import com.example.demo1.Student.Major;
/**
 * This class is the helper class of Student that stores to name and major of individuals

 * @author Divyesh Nemam Baskaran, Viraj Patel
 *
 */
public class Profile {
    private String name;
    private Major major; //5 majors and 2-character each: CS, IT, BA, EE, ME

    /**Method is a constructor class for profile
     * @param name,major
     * @returns none
     */
    public Profile(String name, Major major){
        this.name = name;
        this.major = major;
    }

    /**Method returns the name of a profile
     * @Param none
     * @return name
     */
    public String getName() {
        return name;
    }
    /**Method returns the type of major
     * @Param none
     * @return major
     */
    public Major getMajor() {
        return major;
    }
    /**Method returns the toString of a profile with name and major
     * @Param none
     * @return string
     */
    @Override
    public String toString() {
        return name+":"+major+":";
    }
}