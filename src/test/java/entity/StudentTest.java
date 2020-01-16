package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import entity.ships.Ship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {
    private transient Student student1;
    private transient Student student2;
    private transient Student student3;
    private transient Student notValid;
    private transient Ship ship;


    @BeforeEach
    void setupTestEnvironment() {
        student1 = new Student("Black Widow", 1234567);
        student2 = new Student("Thor", 7654321);
        student3 = new Student("Loki", 1234567);
        notValid = new Student("Loser", 10000000);
    }

    @Test
    void testConstructor() {
        assertNotNull(student1);
    }

    @Test
    void testGetName() {
        assertEquals(student1.getName(), "Black Widow");
    }

    @Test
    void testGetStudentNumber() {
        assertEquals(student1.getStudentNumber(), 1234567);
    }

    @Test
    void testEqualsPositive() {
        assertEquals(student1, student3);
    }

    @Test
    void testEqualsSelfPositive() {
        assertEquals(student1, student1);
    }

    @Test
    void testEqualsNegativeObject() {
        assertEquals(student1.equals(ship), false);
    }

    @Test
    void testEqualsNegative() {
        assertNotEquals(student1, student2);
    }

    @Test
    void testNotValidStudentNumber() {
        assertEquals(false, notValid.isValidStudentNumber());
    }

    @Test
    void testValidStudentNumber() {
        assertEquals(true, student1.isValidStudentNumber());
    }

    @Test
    void testHashCode() {
        assertEquals(1425489871, student1.hashCode());
    }

    @Test
    void testToString() {
        String name = student1.getName();
        int number = student1.getStudentNumber();
        String result = name + " (" + number + ")";
        assertEquals(student1.toString(), result);
    }

}
