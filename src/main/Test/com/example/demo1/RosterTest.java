package com.example.demo1;

import org.junit.jupiter.api.Test;
import com.example.demo1.Student.Major;
import org.junit.jupiter.api.Assertions;

class RosterTest {

    @Test
    void add() {
        Roster roster = new Roster();
        Student one = new Student("Dan",Student.Major.BA);
        roster.add(one);
        Assertions.assertEquals(0,roster.inRoster(one),0.001);
        Student two = new Resident("Jack",Student.Major.BA,12);
        roster.add(two);
        Assertions.assertEquals(1,roster.inRoster(two),0.001);
        Student three = new TriState("Jackson",Student.Major.BA,12,Student.Tri.NY);
        roster.add(three);
        Assertions.assertEquals(2,roster.inRoster(three),0.001);
        Student four = new International("Ksi",Student.Major.BA,12,true);
        roster.add(four);
        Assertions.assertEquals(3,roster.inRoster(four),0.001);
        Student five = new NonResident("Mac",Student.Major.BA,12);
        roster.add(five);
        Assertions.assertEquals(4,roster.inRoster(five),0.001);
    }

    @Test
    void remove() {
        Roster roster = new Roster();
        Student one = new Student("Dan",Student.Major.BA);
        roster.add(one);
        Student two = new Resident("Jack",Student.Major.BA,12);
        roster.add(two);
        Student three = new TriState("Jackson",Student.Major.BA,12,Student.Tri.NY);
        roster.add(three);
        Student four = new International("Ksi",Student.Major.BA,12,true);
        roster.add(four);
        Student five = new NonResident("Mac",Student.Major.BA,12);
        roster.add(five);
        roster.remove(one);
        Assertions.assertEquals(-1,roster.inRoster(one),0.001);
        roster.remove(two);
        Assertions.assertEquals(-1,roster.inRoster(two),0.001);
        roster.remove(three);
        Assertions.assertEquals(-1,roster.inRoster(three),0.001);
        roster.remove(four);
        Assertions.assertEquals(-1,roster.inRoster(four),0.001);
        roster.remove(five);
        Assertions.assertEquals(-1,roster.inRoster(five),0.001);
    }
}