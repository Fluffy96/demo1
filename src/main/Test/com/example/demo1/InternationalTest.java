package com.example.demo1;

import org.junit.jupiter.api.Test;
import com.example.demo1.Student.Major;
import org.junit.jupiter.api.Assertions;

class InternationalTest {

    @Test
    void tuitionDue() {
        International t1 = new International("Bob Ross", Major.IT,12,false);
        t1.setTuition(0);
        t1.tuitionDue();
        Assertions.assertEquals(35655.00,t1.getTuition(),0.001);

        International t2 = new International("Bob Ross", Major.IT,20,false);
        t2.setTuition(0);
        t2.tuitionDue();
        Assertions.assertEquals(39519.00,t2.getTuition(),0.001);

        International t3 = new International("Bob Ross", Major.IT,12,true);
        t3.setTuition(0);
        t3.tuitionDue();
        Assertions.assertEquals(5918.00,t3.getTuition(),0.001);
    }
}