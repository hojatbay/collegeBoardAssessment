package org.collegeBoard.assessment.test;

import org.collegeBoard.assessment.StudentUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentUtilTest {

    @Test
    public void testGetGradeOnGivenDate() {
        StudentUtil studentUtil = new StudentUtil();

        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-02","2016-06-02"), 12);
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-02", "2000-06-02"), -1);
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-02","2016-06-03"), 99);
        //given date is empty
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-02",""), 99);
        //given date is null
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-30", null), 99);
        //invalid given date
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-30", "2398567"), 99);
        //base on the given date academic year is not started (last academic year is 2009-09-01 2010-06-30)
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-02","2010-08-02"), 6);
        //base on the given date academic year is started (it is 2010-09-01 2011-06-30)
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-02","2010-09-02"), 7);

        //difference between given date and graduation date is 13 and academic year started at 2003-9-01
        //so we are in the kindergarten academic year (2003-09-01 2004-06-30)
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-02","2003-09-02"), 0);

        //difference between given date and graduation date is 13 and academic year (kindergarten) not started
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-02","2003-08-02"), -1);

        //more tests
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-30", "2007-07-07"), 3);
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-20", "2009-01-01"), 5);
        assertEquals(studentUtil.getGradeOnGivenDate("2016-08-20", "2009-01-01"), 5);
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-30","2003-12-02"), 0);
        assertEquals(studentUtil.getGradeOnGivenDate("2016-06-30","2000-12-02"), -1);

    }
}
