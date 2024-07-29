package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class AcademicView {
        static final String USER = "root";
        static final String PASS = "tiarani0987";

        protected String url = "jdbc:mysql://localhost:3306/Academicdb";
        protected Connection connection = null;

        protected Connection getConnection() throws SQLException {
            if (connection == null) {
                connection = DriverManager.getConnection(url, USER, PASS);
            }
            return connection;
        }

    public void displayLecturers() throws SQLException {
        String lecturerSQL = "SELECT lecturer_id, lecturer_name, initial, email, study_program FROM lecturers ORDER BY CASE lecturer_id WHEN '0130058501' THEN 1 WHEN '0114129002' THEN 2 WHEN '0124108201' THEN 3 END";
        try (PreparedStatement lecturerStatement = this.getConnection().prepareStatement(lecturerSQL)) {
            ResultSet lecturerRs = lecturerStatement.executeQuery();
    
            while (lecturerRs.next()) {
                String id = lecturerRs.getString(1);
                String name = lecturerRs.getString(2);
                String initial = lecturerRs.getString(3);
                String email = lecturerRs.getString(4);
                String studyProgram = lecturerRs.getString(5);
    
                System.out.println(id + "|" + name + "|" + initial + "|" + email + "|" + studyProgram);
            }
        }
    }

    public void displayCourses() throws SQLException {
        String courseSQL = "SELECT course_code, course_name, credits, grade FROM courses";
        try (PreparedStatement courseStatement = this.getConnection().prepareStatement(courseSQL)) {
            ResultSet courseRs = courseStatement.executeQuery();

            while (courseRs.next()) {
                String id = courseRs.getString(1);
                String name = courseRs.getString(2);
                int credit = courseRs.getInt(3);
                String passingGrade = courseRs.getString(4);

                System.out.println(id + "|" + name + "|" + credit + "|" + passingGrade);
            }
        }
    }

    public void displayStudents() throws SQLException {
        String studentSQL = "SELECT student_id, student_name, year, study_program FROM students";
        try (PreparedStatement studentStatement = this.getConnection().prepareStatement(studentSQL)) {
            ResultSet studentRs = studentStatement.executeQuery();

            while (studentRs.next()) {
                String id = studentRs.getString(1);
                String name = studentRs.getString(2);
                String year = studentRs.getString(3);
                String studyProgram = studentRs.getString(4);

                System.out.println(id + "|" + name + "|" + year + "|" + studyProgram);
            }
        }
    }

    public void displayEnrollments() throws SQLException {
        String enrollmentSQL = "SELECT course_code, student_id, year, semester, grade FROM enrollments ORDER BY semester DESC";
        try (PreparedStatement enrollmentStatement = this.getConnection().prepareStatement(enrollmentSQL)) {
            ResultSet enrollmentRs = enrollmentStatement.executeQuery();

            while (enrollmentRs.next()) {
                String courseId = enrollmentRs.getString(1);
                String studentId = enrollmentRs.getString(2);
                String year = enrollmentRs.getString(3);
                String semester = enrollmentRs.getString(4);
                String grade = enrollmentRs.getString(5);

                System.out.println(courseId + "|" + studentId + "|" + year + "|" + semester + "|" + grade);
            }
        }
    }
}
