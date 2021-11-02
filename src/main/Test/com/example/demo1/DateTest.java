package com.example.demo1;

import org.junit.jupiter.api.Test;
import com.example.demo1.Student.Major;
import org.junit.jupiter.api.Assertions;

class DateTest {

    @Test
    void isValid() {
        //Invalid month
        Date t1 = new Date("2021-13-1");//false
        Assertions.assertEquals(false,t1.isValid());

        //Future date
        Date t2 = new Date("2021-12-2");//false
        Assertions.assertEquals(false,t2.isValid());

        //Invalid day
        Date t3 = new Date("2021-12-32");//false
        Assertions.assertEquals(false,t3.isValid());

        //Invalid month
        Date t4 = new Date("2021-0-2");//false
        Assertions.assertEquals(false,t4.isValid());

        //Valid date
        Date t5 = new Date("2021-3-12");//true
        Assertions.assertEquals(true,t5.isValid());

        //Invalid day in non-leap year
        Date t6 = new Date("2021-2-29");//false
        Assertions.assertEquals(false,t6.isValid());

        //Valid day in non-leap year
        Date t7 = new Date("2021-2-28");//true
        Assertions.assertEquals(true,t7.isValid());

        //Year before 2021
        Date t8 = new Date("1979-2-28");//false
        Assertions.assertEquals(false,t8.isValid());

        //Valid date
        Date t9 = new Date("2021-2-28");//true
        Assertions.assertEquals(true,t9.isValid());
    }
}