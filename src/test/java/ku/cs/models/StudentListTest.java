package ku.cs.models;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {

    StudentList list;

    @BeforeEach
    void setUp() { list = new StudentList(); }

    @Test
    void addNewStudent_trimsAndAdds_whenValidAndUnique() {
        list.addNewStudent("  001 ", "  Alice  ");
        assertEquals(1, list.getStudents().size());
        Student s = list.getStudents().get(0);
        assertEquals("001", s.getId());
        assertEquals("Alice", s.getName());
        assertEquals(0.0, s.getScore());
    }

    @Test
    void addNewStudent_withScore_trimsAndAdds() {
        list.addNewStudent("002", "Bob", 55.5);
        assertEquals(1, list.getStudents().size());
        assertEquals(55.5, list.getStudents().get(0).getScore());
        assertEquals("D", list.getStudents().get(0).getGrade());
    }

    @Test
    void addNewStudent_ignored_whenBlankIdOrName() {
        list.addNewStudent(" ", "A");
        list.addNewStudent("001", " ");
        assertTrue(list.getStudents().isEmpty());
    }

    @Test
    void addNewStudent_ignored_whenDuplicateId() {
        list.addNewStudent("001", "A");
        list.addNewStudent("001", "B");
        assertEquals(1, list.getStudents().size());
        assertEquals("A", list.getStudents().get(0).getName());
    }

    @Test
    void findStudentById_returnsStudentOrNull() {
        list.addNewStudent("001", "A");
        assertNotNull(list.findStudentById("001"));
        assertNull(list.findStudentById("999"));
    }

    @Test
    void filterByName_caseInsensitiveContains_returnsNewList() {
        list.addNewStudent("001", "Alice");
        list.addNewStudent("002", "Bob");
        list.addNewStudent("003", "ALICIA");

        StudentList filtered = list.filterByName("ali");
        assertEquals(2, filtered.getStudents().size());

        assertSame(list.findStudentById("001"), filtered.findStudentById("001"));
    }

    @Test
    void giveScoreToId_addsOnlyWhenFound() {
        list.addNewStudent("001", "A");
        list.giveScoreToId("001", 10);
        list.giveScoreToId("999", 10);
        assertEquals(10.0, list.findStudentById("001").getScore());
        assertNull(list.findStudentById("999"));
    }

    @Test
    void viewGradeOfId_returnsGradeOrNull() {
        list.addNewStudent("001", "A", 82);
        assertEquals("A", list.viewGradeOfId("001"));
        assertNull(list.viewGradeOfId("404"));
    }

    @Test
    void getStudents_returnsBackingList_referenceNotNull() {
        ArrayList<Student> students = list.getStudents();
        assertNotNull(students);
        list.addNewStudent("x","y");
        assertEquals(1, students.size());
    }
}
