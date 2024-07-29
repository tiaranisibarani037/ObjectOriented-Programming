package academic.driver;

/**
 * @author 12S22037 Tiarani Sibarani
 */
import java.util.Scanner;

import academic.model.AcademicView;
import academic.model.AcademicController;

public class Driver1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AcademicView academicView = new AcademicView();

        while (input.hasNextLine()) {
            String str = input.nextLine();
            if (str.equals("---")) {
                break;
            } else {
                String[] tokens = str.split("#");
                String command = tokens[0];

                switch (command) {
                    case "lecturer-add":
                        AcademicController.addLecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                        break;
                    case "student-add":
                        AcademicController.addStudent(tokens[1], tokens[2], tokens[3], tokens[4]);
                        break;
                    case "course-add":
                        AcademicController.addCourse(tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4]);
                        break;
                    case "course-open":
                        AcademicController.addCourseOpening(tokens[1], tokens[2], tokens[3], tokens[4]);
                        break;
                    case "enrollment-add":
                        AcademicController.addEnrollment(tokens[1], tokens[2], tokens[3], tokens[4]);
                        break;
                    case "course-history":
                        AcademicController.displayCourseHistory(tokens[1]);
                        break;
                    case "enrollment-grade":
                        AcademicController.addEnrollmentGrade(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                        break;
                    case "enrollment-remedial":
                        AcademicController.addEnrollmentRemedial(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                        break;
                    case "student-details":
                        AcademicController.addStudentDetail(tokens[1]);
                        break;
                    case "student-transcript":
                        AcademicController.displayStudentTranscript(tokens[1]);
                        break;
                    default:
                        System.out.println("Invalid command.");
                        break;
                }

            }
        }

        academicView.displayLecturers(AcademicController.getLecturers());
        academicView.displayCourses(AcademicController.getCourses());
        academicView.displayStudents(AcademicController.getStudents());
        academicView.displayEnrollments(AcademicController.getEnrollments());

        input.close();
    }
}
