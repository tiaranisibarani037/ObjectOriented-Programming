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
                    int credit = Integer.parseInt(tokens[3]); // Change to int
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
                
                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getCourse_id().equals(course_id) &&
                            enrollment.getStudent_id().equals(student_id) &&
                            enrollment.getYear().equals(year) &&
                            enrollment.getSemester().equals(semester)) {
                            enrollment.setGrade(grade);
                            break;
                        }
                    }

                } else if (command.equals("enrollment-remedial")) {
                    String course_id = tokens[1];
                    String student_id = tokens[2];
                    String year = tokens[3];
                    String semester = tokens[4];
                    String grade = tokens[5];

                    // Check if enrollment exists
                    Enrollment existingEnrollment = enrollments.stream()
                            .filter(enrollment -> enrollment.getCourse_id().equals(course_id) &&
                                    enrollment.getStudent_id().equals(student_id) &&
                                    enrollment.getYear().equals(year) &&
                                    enrollment.getSemester().equals(semester))
                            .findFirst()
                            .orElse(null);

                    if (existingEnrollment != null && !existingEnrollment.getGrade().contains("(")) {
                        // Create a new enrollment for remedial with previous grade recorded
                        String previousGrade = existingEnrollment.getGrade();
                        existingEnrollment.setGrade(grade + "(" + previousGrade + ")");
                        
                    } else if (existingEnrollment == null) {
                        // If enrollment doesn't exist, create a new enrollment for remedial
                        Enrollment enrollment = new Enrollment(course_id, student_id, year, semester, grade);
                        enrollments.add(enrollment);
                    }    
                //penerapan polimorphism, melalui pemanggilan method dalam EntityInterface.
                } else if (command.equals("student-details")) {
                    String student_id = tokens[1];
                    double totalCredit = 0;
                    double totalGradePoints = 0;
                
                    Map<String, Enrollment> lastEnrollmentMap = new HashMap<>();
                    for (Enrollment enrollment : enrollments) {
                        String key = enrollment.getStudent_id() + "|" + enrollment.getCourse_id();
                        // Check if the map already contains an enrollment for this course
                        if (lastEnrollmentMap.containsKey(key)) {
                            // If the existing enrollment has a remedial grade, replace it with the new one
                            if (lastEnrollmentMap.get(key).getGrade().contains("(")) {
                                lastEnrollmentMap.put(key, enrollment);
                            }
                        } else {
                            lastEnrollmentMap.put(key, enrollment);
                        }
                    }
                
                    for (Course course : courses) {
                        String key = student_id + "|" + course.getId();
                        if (lastEnrollmentMap.containsKey(key)) {
                            Enrollment lastEnrollment = lastEnrollmentMap.get(key);
                            String grade = lastEnrollment.getGrade();
                            // If the grade is a remedial grade, use the remedial grade for the calculation
                            if (grade.contains("(")) {
                                grade = grade.substring(0, grade.indexOf("("));
                            }
                            if (!grade.equals("None")) {
                                totalCredit += Double.parseDouble(course.getCredit()); // Convert course.getCredit() to double
                                totalGradePoints += calculateGradePoints(grade) * Double.parseDouble(course.getCredit()); // Convert course.getCredit() to double
                            }
                        }
                    }
                
                    double gpa = totalCredit == 0 ? 0 : totalGradePoints / totalCredit;
                    final double finalTotalCredit = totalCredit;
                    students.stream()
                            .filter(student -> student.getId().equals(student_id))
                            .forEach(student -> System.out.printf("%s|%s|%s|%s|%.2f|%.0f%n", student.getId(), student.getName(), student.getYear(),
                                    student.getStudyProgram(), gpa, finalTotalCredit));
                }

            }
        }

        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getId() + "|" + lecturer.getName() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getStudyProgram());
        }

        for (Course course : courses) {
            System.out.println(course.getId() + "|" + course.getName() + "|" + Integer.parseInt(course.getCredit()) + "|" + course.getPassingGrade() + "|" + course.getLecturerList());
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