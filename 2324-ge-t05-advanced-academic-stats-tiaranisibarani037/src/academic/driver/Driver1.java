package academic.driver;

/**
 * @author 12S22037 Tiarani Sibarani
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import academic.model.Lecturer;
import academic.model.Student;
import academic.model.Course;
import academic.model.Enrollment;

public class Driver1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();

        while (input.hasNext()) {
            String str = input.nextLine();

            if (str.equals("---")) {
                break;
            } else {
                String[] tokens = str.split("#");
                String command = tokens[0];

                if (command.equals("lecturer-add")) {
                    String IdDosen = tokens[1];
                    String NameDosen = tokens[2];
                    String initial = tokens[3];
                    String email = tokens[4];
                    String StudyProgram = tokens[5];

                    boolean isIDdosenExist = lecturers.stream().anyMatch(lecturer -> lecturer.getId().equals(IdDosen));

                    if (!isIDdosenExist) {
                        Lecturer lecturer = new Lecturer(IdDosen, NameDosen, initial, email, StudyProgram);
                        lecturers.add(lecturer);
                    }
                } else if (command.equals("student-add")) {
                    String id = tokens[1];
                    String name = tokens[2];
                    String year = tokens[3];
                    String studyProgram = tokens[4];

                    boolean isIdExist = students.stream().anyMatch(student -> student.getId().equals(id));

                    if (!isIdExist) {
                        Student student = new Student(id, name, year, studyProgram);
                        students.add(student);
                    }
                } else if (command.equals("enrollment-add")) {
                    String course_id = tokens[1];
                    String student_id = tokens[2];
                    String year = tokens[3];
                    String semester = tokens[4];

                    Enrollment enrollment = new Enrollment(course_id, student_id, year, semester);
                    enrollments.add(enrollment);
                } else if (command.equals("course-add")) {
                    String course_id = tokens[1];
                    String course_name = tokens[2];
                    double credit = Double.parseDouble(tokens[3]);
                    String passingGrade = tokens[4];
                    String lecturer_initial_list = tokens[5];

                    boolean idCourseIdExist = courses.stream().anyMatch(course -> course.getId().equals(course_id));

                    if (!idCourseIdExist) {
                        String[] initials = lecturer_initial_list.split(",");
                        List<Lecturer> courseLecturers = new ArrayList<>();

                        for (String initial : initials) {
                            for (Lecturer lecturer : lecturers) {
                                if (lecturer.getInitial().equals(initial.trim())) {
                                    courseLecturers.add(lecturer);
                                    break;
                                }
                            }
                        }

                        Course course = new Course(course_id, course_name, credit, passingGrade, courseLecturers);
                        courses.add(course);
                    }
                } else if (command.equals("enrollment-grade")) {
                    String course_id = tokens[1];
                    String student_id = tokens[2];
                    String year = tokens[3];
                    String semester = tokens[4];
                    String grade = tokens[5];

                    enrollments.stream()
                            .filter(enrollment -> enrollment.getCourse_id().equals(course_id) &&
                                    enrollment.getStudent_id().equals(student_id) &&
                                    enrollment.getYear().equals(year) &&
                                    enrollment.getSemester().equals(semester))
                            .findFirst()
                            .ifPresent(enrollment -> enrollment.setGrade(grade));
                } else if (command.equals("student-details")) {
                    String student_id = tokens[1];
                    double[] totalCredit = {0}; // Array yang hanya memiliki satu elemen untuk menyimpan nilai totalCredit
                    double totalGradePoints = 0;

                    Map<String, Enrollment> lastEnrollmentMap = new HashMap<>();
                    for (Enrollment enrollment : enrollments) {
                        String key = enrollment.getStudent_id() + "|" + enrollment.getCourse_id();
                        lastEnrollmentMap.put(key, enrollment);
                    }

                    for (Course course : courses) {
                        String key = student_id + "|" + course.getId();
                        if (lastEnrollmentMap.containsKey(key)) {
                            Enrollment lastEnrollment = lastEnrollmentMap.get(key);
                            if (!lastEnrollment.getGrade().equals("None")) {
                                totalCredit[0] += Double.parseDouble(course.getCredit());
                                totalGradePoints += calculateGradePoints(lastEnrollment.getGrade()) * Double.parseDouble(course.getCredit());
                            }
                        }
                    }

                    double gpa = totalCredit[0] != 0 ? totalGradePoints / totalCredit[0] : 0;

                    students.stream()
                            .filter(student -> student.getId().equals(student_id))
                            .forEach(student -> System.out.printf("%s|%s|%s|%s|%.2f|%.0f%n", student.getId(), student.getName(), student.getYear(),
                                    student.getStudyProgram(), gpa, totalCredit[0]));
                }
            }
        }

        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getId() + "|" + lecturer.getName() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getStudyProgram());
        }

        for (Course course : courses) {
            System.out.println(course.getId() + "|" + course.getName() + "|" + course.getCredit() + "|" + course.getPassingGrade() + "|" + course.getLecturerList());
        }

        for (Student student : students) {
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getStudyProgram());
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.getCourse_id() + "|" + enrollment.getStudent_id() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
        }

        input.close();
    }

    private static double calculateGradePoints(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "E":
                return 0.0;
            default:
                return 0.0;
        }
    }
}