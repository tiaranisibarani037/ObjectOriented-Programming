package academic.driver;

/**
 * @author 12S22037 Tiarani Sibarani
 */

import java.sql.SQLException;
import java.util.Scanner;

import academic.model.AcademicView;
import academic.model.AcademicController;

public class Driver1 {

    static final String USER = "root";
    static final String PASS = "tiarani0987";

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        AcademicView academicView = new AcademicView();

        try {
            AcademicController database = new AcademicController("jdbc:mysql://localhost:3306/Academicdb");

            while (input.hasNextLine()) {
                String str = input.nextLine();
                if (str.equals("---")) {
                    break;
                } else {
                    String[] tokens = str.split("#");
                    String command = tokens[0];

                    switch (command) {
                        case "lecturer-add":
                            database.addLecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                            break;
                        case "student-add":
                            database.addStudent(tokens[1], tokens[2], tokens[3], tokens[4]);
                            break;
                        case "course-add":
                            database.addCourse(tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4]);
                            break;
                        case "course-open":
                            database.addCourseOpening(tokens[1], tokens[2], tokens[3], tokens[4]);
                            break;
                        case "enrollment-add":
                            database.addEnrollment(tokens[1], tokens[2], tokens[3], tokens[4]);
                            break;
                        case "course-history":
                            database.displayCourseHistory(tokens[1]);
                            break;
                        case "enrollment-grade":
                            database.addEnrollmentGrade(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                            break;
                        case "enrollment-remedial":
                            database.addEnrollmentRemedial(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                            break;
                        case "student-details":
                            database.addStudentDetail(tokens[1]);
                            break;
                        case "student-transcript":
                            database.displayStudentTranscript(tokens[1]);
                            break;
                        default:
                            System.out.println("Invalid command.");
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            input.close();
        }

        academicView.displayLecturers();
        academicView.displayCourses();
        academicView.displayStudents();
        academicView.displayEnrollments();
    }
}
