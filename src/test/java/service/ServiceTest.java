package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class ServiceTest {

    public static Service service;

    @org.junit.jupiter.api.BeforeAll
    public static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void findAllStudents() {
    }

    @org.junit.jupiter.api.Test
    void findAllHomework() {
    }

    @org.junit.jupiter.api.Test
    void findAllGrades() {
    }

    @org.junit.jupiter.api.Test
    void saveStudent() {
        int result = service.saveStudent("11", "John", 1);
        assertTrue(result == 1);
        service.deleteStudent("11");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("checking if homework save works")
    void saveValidHomework() {
        Homework hw = new Homework("77", "some easy homework", 6, 2);
        int result = service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());
        assertEquals(0, result);
        //assertTrue(result == 1);
        service.deleteHomework(hw.getID());
    }

    @org.junit.jupiter.api.Test
    void saveGrade() {
    }

    @org.junit.jupiter.api.Test
    void deleteStudent() {
        int result = service.deleteStudent("4");
        assertTrue(result == 0);
        service.saveStudent("4", "Ion", 227);
    }

    @org.junit.jupiter.api.Test
    void deleteHomework() {
        int result = service.deleteHomework("1");
        assertTrue(result == 0);
        service.saveHomework("1", "File", 7, 6);
    }

    @org.junit.jupiter.api.Test
    void updateStudent() {
        int result = service.updateStudent("2", "Bella", 22);
        assertTrue(result == 0);
    }

    @org.junit.jupiter.api.Test
    void updateHomework() {
        int result = service.updateHomework("2", "Other", 7, 7);
        assertTrue(result == 0);
    }

    @org.junit.jupiter.api.Test
    void updateHomeworkInvalidData() {
        int result = service.updateHomework("777", "Other", 7, 7);
        assertFalse(result == 0);
    }

    @org.junit.jupiter.api.Test
    void extendDeadline() {
    }

    @org.junit.jupiter.api.Test
    void createStudentFile() {
    }

    @Test
    void assertAllTest(){
        Student s = new Student("99", "Johan", 533);
        assertAll(
                "student",
                () -> assertEquals("99", s.getID()),
                () -> assertEquals("Johan", s.getName())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 55, 533})
    void testStudentAddByGroup(int group){
        assumeTrue(group >= 0);
        Student s = new Student("99", "Johan", group);
        int result = service.saveStudent(s.getID(), s.getName(), s.getGroup());
        assertEquals(1, result);
        service.deleteStudent(s.getID());
    }


}