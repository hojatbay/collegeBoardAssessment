package org.collegeBoard.assessment;

import java.time.LocalDate;
import java.time.Month;

public class StudentUtil {
    private final static int GRADE_12 = 12;
    private final static int KINDERGARTEN = 0;
    private final static int PRESCHOOL = -1;
    private final static int POST_HIGH_SCHOOL = 99;
    //Considering kindergarten as a full academic year
    private final static int TOTAL_GRADES_INCLUDING_KINDERGARTEN = 13;

    /**
     * @param highSchoolGradDate
     * @param givenDate
     * @return
     */
    public int getGradeOnGivenDate(String highSchoolGradDate, String givenDate){

        //I assume the graduation date is a valid date
        LocalDate gradDate = LocalDate.parse(highSchoolGradDate);
        //according to the Academic Year constraint the graduation date is in month of June or after
        //So I set to month of June in all cases
        if(gradDate.getMonth().compareTo(Month.JUNE) != 0){
            LocalDate.of(gradDate.getYear(), Month.JUNE, gradDate.getDayOfMonth());
        }

        LocalDate testDate;
        try{
            testDate = LocalDate.parse(givenDate);
        } catch (Exception e){ //if given date is empty, null or invalid then takes today's date
            System.out.println("Given Date is not valid. Let's continue with today's date");
            testDate = LocalDate.now();
        }

        //since academic year spans two years (SEP-01 to Jun-30),
        // we need to take into account if the academic year started or not for a given date
        boolean isAcademicYearStarted = false;
        if(testDate.getMonth().compareTo(Month.AUGUST) > 0){
            isAcademicYearStarted = true;
        }

        if(testDate.isAfter(gradDate)){ return POST_HIGH_SCHOOL;}
        else if(gradDate.compareTo(testDate) == 0){return GRADE_12;}
        else if(gradDate.compareTo(testDate) == TOTAL_GRADES_INCLUDING_KINDERGARTEN && isAcademicYearStarted){return KINDERGARTEN;}
        else if(gradDate.compareTo(testDate) > TOTAL_GRADES_INCLUDING_KINDERGARTEN ||
                (gradDate.compareTo(testDate) == TOTAL_GRADES_INCLUDING_KINDERGARTEN && !isAcademicYearStarted)){
            return PRESCHOOL;
        }
        else {
            int diff = gradDate.compareTo(testDate);
            return isAcademicYearStarted ? TOTAL_GRADES_INCLUDING_KINDERGARTEN - diff : TOTAL_GRADES_INCLUDING_KINDERGARTEN - (diff+1);
        }
    }
}
