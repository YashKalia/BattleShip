package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTest {
    private transient Student student1;
    private transient Student student2;
    private transient Student student3;
    private transient Student notValid;


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

}
