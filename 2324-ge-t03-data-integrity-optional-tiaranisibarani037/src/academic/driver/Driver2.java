package academic.driver;   

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import academic.model.Course;
import academic.model.Enrollment;
import academic.model.Student;

public class Driver2 {

    public static void main(String[] _args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        Set<String> invalidCourses = new HashSet<>();
        Set<String> invalidStudents = new HashSet<>();
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
                    
                    boolean isCourseIdExists = false;
                    boolean isStudentIdExists = false;

                    // Cek apakah course id dan student id ada di dalam list
                    for(Course course : courses) {
                        if(course.getCourse_id().equals(course_id)) {
                            isCourseIdExists = true;
                            break;
                        }
                    }
                    for(Student student : students) {
                        if(student.getId().equals(student_id)) {
                            isStudentIdExists = true;
                            break;
                        }
                    }

                    // Jika course id atau student id tidak ada, tambahkan ke daftar yang tidak valid
                    if (!isCourseIdExists) {
                        invalidCourses.add(course_id);
                    }
                    if (!isStudentIdExists) {
                        invalidStudents.add(student_id);
                    }

                    if (isCourseIdExists && isStudentIdExists) {
                        Enrollment enrollment = new Enrollment(course_id, student_id, year, semester);
                        enrollments.add(enrollment);
                    }
                }
            }
        }

        if (isFinished) {

             // Print invalid students (only once)
             if (!invalidStudents.isEmpty()) {
                //memeriksa apakah set invalidStudents kosong atau tidak
                //Jika set invalidStudents kosong, maka kondisi !invalidStudents.isEmpty() akan bernilai false. 
                //Namun, dengan menggunakan tanda seru ! sebelum pemanggilan metode isEmpty(), maka kondisi tersebut 
                //akan menjadi true jika set invalidStudents tidak kosong.
                System.out.println("invalid student|" + invalidStudents.iterator().next());
            }
            // Print invalid courses
            for (String invalidCourse : invalidCourses) {
                //Dalam loop ini, setiap elemen dari koleksi invalidCourses akan diambil secara bergantian dan disimpan dalam variabel 
                //invalidCourse yang dideklarasikan di dalam tanda kurung.
                System.out.println("invalid course|" + invalidCourse);
            }

            // Print courses
            courses.sort(Comparator.comparing(Course::getCourse_id));
            for (Course course : courses) {
                System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getCredit() + "|" + course.getPassingGrade());
            }

            // Print students
            for (Student student : students) {
                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getStudyProgram());
            }

            // Print enrollments
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment.toString());
            }
        }

        input.close();
    }
}
