package academic.driver;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Driver1 {

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
                    boolean isCourseIdExists = false;
                    for (Course course : courses) {
                        if (course.getCourse_id().equals(course_id)) {
                            isCourseIdExists = true;
                            break;
                        }
                    }
                    if (isCourseIdExists) {
                        //System.out.println("Course ID already exists.");
                    } else {
                        Course course = new Course(course_id, course_name, credit, passingGrade);
                        courses.add(course);
                    }
                }
                else if (command.equals("student-add")) {
                    String id = tokens[1];
                    String name = tokens[2];
                    String year = tokens[3];
                    String studyProgram = tokens[4];
                    boolean isIdExist = false;
                    for (Student student: students) {
                        if (student.getId().equals(id)) {
                            isIdExist = true;
                            break;
                        }
                    }
                    if (isIdExist) {
                        //System.out.println("Student ID already exists.");
                    } else {
                        Student student = new Student(id, name, year, studyProgram);
                        students.add(student);
                    }

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
            courses.sort(Comparator.comparing(Course::getCourse_id));
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