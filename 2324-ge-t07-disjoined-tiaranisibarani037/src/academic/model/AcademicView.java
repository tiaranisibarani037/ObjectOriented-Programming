package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */

import java.util.List;


public class AcademicView {

    public void displayLecturers(List<Lecturer> lecturers) {
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getId() + "|" + lecturer.getName() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getStudyProgram());
        }
    }

    public void displayCourses(List<Course> courses) {
        for (Course course : courses) {
            System.out.println(course.getId() + "|" + course.getName() + "|" + Integer.parseInt(course.getCredit()) + "|" + course.getPassingGrade());
        }
    }

    public void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getStudyProgram());
        }
    }

    public void displayEnrollments(List<Enrollment> enrollments) {
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getCourse_id() + "|" + enrollment.getStudent_id() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
        }
    }
}