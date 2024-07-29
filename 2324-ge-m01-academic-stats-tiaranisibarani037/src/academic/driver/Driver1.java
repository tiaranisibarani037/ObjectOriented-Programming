package academic.driver;

/**
 * @author 12S22037 Tiarani Sibarani
 * @author 12S22004 Bethania Hasibuan
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import academic.model.Lecturer;
import academic.model.Student;
import academic.model.Course;
import academic.model.Enrollment;

public class Driver1 {

    public static void main(String[] _args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

        String str;
        boolean isFinished = false;

        while(input.hasNext()){
            str = input.nextLine();

            if (str.equals("---")){
                isFinished = true;
                break;
            }else{
                String[] tokens = str.split("#");
                String command = tokens[0];

                if (command.equals("lecturer-add")){
                    String IdDosen = tokens[1];
                    String NameDosen = tokens[2];
                    String initial = tokens[3];
                    String email = tokens[4];
                    String StudyProgram = tokens[5];

                    boolean isIDdosenExist = false;

                    for (Lecturer lecturer : lecturers){
                        if (lecturer.getIdDosen().equals(IdDosen)){
                            isIDdosenExist = true;
                            break;
                        }
                    }

                    if(!isIDdosenExist){
                        Lecturer lecturer = new Lecturer(IdDosen, NameDosen, initial, email, StudyProgram);
                        lecturers.add(lecturer);
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
                    if (!isIdExist) {
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

                else if (command.equals("course-add")) {
                    String course_id = tokens[1];
                    String course_name = tokens[2];
                    double credit = Double.parseDouble(tokens[3]);
                    String passingGrade = tokens[4];
                    String lecturer_initial_list = tokens[5];
                    boolean idCourseIdExist = false;

                    for (Course course : courses) {
                        if (course.getCourse_id().equals(course_id)) {
                            idCourseIdExist = true;
                            break;
                        }
                    }
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
                }

                else if (command.equals("enrollment-grade")) {
                    String course_id = tokens[1];
                    String student_id = tokens[2];
                    String year = tokens[3];
                    String semester = tokens[4];
                    String grade = tokens[5];

                    for (Enrollment enrollment : enrollments) {
                        if (enrollment.getCourse_id().equals(course_id) && enrollment.getStudent_id().equals(student_id)
                                && enrollment.getYear().equals(year) && enrollment.getSemester().equals(semester)) {
                            enrollment.setGrade(grade);
                            break;
                        }
                    }
                }
            }
        }

        if(isFinished){
            for(Lecturer lecturer : lecturers){
                System.out.println(lecturer.getIdDosen() + "|" + lecturer.getNameDosen() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getStudyProgram());
            }

            for(Course course : courses){
                System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getCredit() + "|" + course.getPassingGrade() + "|" + course.getLecturerList());
            }

            for(Student student : students){
                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getStudyProgram());
            }

            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment.getCourse_id() + "|" + enrollment.getStudent_id() + "|" + enrollment.getYear() + "|" + enrollment.getSemester() + "|" + enrollment.getGrade());
            }
        }
        input.close();
    }

}
