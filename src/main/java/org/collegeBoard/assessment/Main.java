package org.collegeBoard.assessment;

/**
 * Created by BayH on 1/31/2018.
 */
public class Main {
    public static void main(String[] args){
        StudentUtil studentUtil = new StudentUtil();
        System.out.println(studentUtil.getGradeOnGivenDate(args[0], args[1]));
    }
}
