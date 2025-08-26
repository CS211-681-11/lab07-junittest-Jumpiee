package ku.cs.models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void constructor_withoutScore_setsZero() {
        Student s = new Student("001", "Alice");
        assertEquals("001", s.getId());
        assertEquals("Alice", s.getName());
        assertEquals(0.0, s.getScore());
        assertEquals("F", s.getGrade());
    }

    @Test
    void constructor_withScore_setsFields() {
        Student s = new Student("002", "Bob", 72.5);
        assertEquals("002", s.getId());
        assertEquals("Bob", s.getName());
        assertEquals(72.5, s.getScore());
        assertEquals("B", s.getGrade());
    }

    @Test
    void changeName_trimsAndChanges_whenNotBlank() {
        Student s = new Student("003", "Old");
        s.changeName("   New Name   ");
        assertEquals("New Name", s.getName());
    }

    @Test
    void changeName_ignores_whenBlankAfterTrim() {
        Student s = new Student("003", "Keep");
        s.changeName("   ");
        assertEquals("Keep", s.getName());
    }

    @Test
    void addScore_positive_addsUp() {
        Student s = new Student("004", "C");
        s.addScore(10);
        s.addScore(5.5);
        assertEquals(15.5, s.getScore());
    }

    @Test
    void addScore_nonPositive_isIgnored() {
        Student s = new Student("004", "C");
        s.addScore(0);
        s.addScore(-7);
        assertEquals(0.0, s.getScore());
    }

    @Test void grade_A()   { assertEquals("A", new Student("x","n",80).grade()); }
    @Test void grade_B()  { assertEquals("B", new Student("x","n",79.99).grade()); }
    @Test void grade_C()  { assertEquals("C", new Student("x","n",60).grade()); }
    @Test void grade_D()  { assertEquals("D", new Student("x","n",59.99).grade()); }
    @Test void grade_F()    { assertEquals("F", new Student("x","n",49.99).grade()); }

    @Test
    void isId_matchesExactly() {
        Student s = new Student("ABC-123", "Zoe");
        assertTrue(s.isId("ABC-123"));
        assertFalse(s.isId("abc-123"));
    }

    @Test
    void isNameContains_caseInsensitiveAndSubstring() {
        Student s = new Student("id", "sacsoos");
        assertTrue(s.isNameContains("sacsoos"));
        assertTrue(s.isNameContains("sacsoos"));
        assertFalse(s.isNameContains("Nope"));
    }

    @Test
    void toString_hasAllFields() {
        Student s = new Student("9","Ann",10);
        String str = s.toString();
        assertTrue(str.contains("id: '9'"));
        assertTrue(str.contains("name: Ann"));
        assertTrue(str.contains("score: 10.0"));
    }
}
