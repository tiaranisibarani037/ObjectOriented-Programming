package academic.driver;

import java.util.ArrayList;
import java.util.Scanner;

import academic.model.Course;
import academic.model.Student;
import academic.model.Enrollment;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Driver4 {

    public static void main(String[] _args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
        String str;

        boolean isFinished = false;

        while(input.hasNext()) {
            str = input.nextLine();
            if(str.equals("---")) {
                isFinished = true;
                break;
            }
            else {
                String[] tokens = str.split("#");
                String command = tokens[0];

                if (command.equals("course-add")) {
                    String course_id = tokens[1];
                    String course_name = tokens[2];
                    String credit = tokens[3];
                    String passingGrade = tokens[4];

                    Course course = new Course(course_id, course_name, credit, passingGrade);
                    courses.add(course);
                }
                else if (command.equals("student-add")) {
                    String id = tokens[1];
                    String name = tokens[2];
                    String year = tokens[3];
                    String studyProgram = tokens[4];

                    Student student = new Student(id, name, year, studyProgram);
                    students.add(student);
                }
                else if (command.equals("enrollment-add")) {
                    String course_id = tokens[1];
                    String student_id = tokens[2];
                    String year = tokens[3];
                    String semester = tokens[4];

                    Enrollment enrollment = new Enrollment(course_id, student_id, year, semester);
                    enrollments.add(enrollment);
                }
            }
        }

        if (isFinished) {
            for (Course course : courses) {
                System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getCredit() + "|" + course.getPassingGrade());
            }

            for (Student student : students) {
                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getStudyProgram());
            }

            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment.toString());
            }
        }

        input.close();

    }

}