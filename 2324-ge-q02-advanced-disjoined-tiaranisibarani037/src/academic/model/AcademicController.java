package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 * @author 12S22003 Yohana Siahaan
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcademicController {
    private static ArrayList<Lecturer> lecturers = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<CourseOpening> courseOpenings = new ArrayList<>();
    private static ArrayList<Enrollment> enrollments = new ArrayList<>();
    private static ArrayList<String> bestStudents = new ArrayList<>();

    
    public static void addLecturer(String id, String name, String initial, String email, String studyProgram) {
        boolean isIdExist = lecturers.stream().anyMatch(lecturer -> lecturer.getId().equals(id));
        
        if (!isIdExist) {
            lecturers.add(new Lecturer(id, name, initial, email, studyProgram));
        }
    }

    public static void addStudent(String id, String name, String year, String studyProgram) {
        boolean isIdExist = students.stream().anyMatch(student -> student.getId().equals(id));

        if (!isIdExist) {
            Student student = new Student(id, name, year, studyProgram);
            students.add(student);
        }
    }

    public static void addCourse(String id, String name, double credit, String passingGrade) {
        boolean isIdExist = courses.stream().anyMatch(course -> course.getId().equals(id));

        if (!isIdExist) {
            courses.add(new Course(id, name, credit, passingGrade));
        }
    }

    public static void addCourseOpening(String courseId, String year, String semester, String lecturerInitials) {

        // Memisahkan inisial dosen
        String[] initials = lecturerInitials.split(",");
        List<Lecturer> courseLecturers = new ArrayList<>();
    
        // Mencari dosen berdasarkan inisial dan menambahkannya ke dalam daftar courseLecturers
        for (String initial : initials) {
            for (Lecturer lecturer : lecturers) {
                if (lecturer.getInitial().trim().equalsIgnoreCase(initial.trim())) {
                    courseLecturers.add(lecturer);
                    break;
                }
            }
        }
    
        
        // Mencari detail mata kuliah yang sesuai
        Course correspondingCourse = null;
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                correspondingCourse = course;
                break;
            }
        }
        
        if (correspondingCourse != null && !courseLecturers.isEmpty()) {
        // Membuat dan menambahkan pembukaan mata kuliah baru
        CourseOpening courseOpening = new CourseOpening(courseId, year, semester, correspondingCourse.getName(), correspondingCourse.getCredit(), correspondingCourse.getPassingGrade(), courseLecturers);
        courseOpenings.add(courseOpening);
    }
}

    public static void displayCourseHistory(String course_id) {
        // Sorting course openings by semester
        Collections.sort(courseOpenings, new Comparator<CourseOpening>() {
            @Override
            public int compare(CourseOpening co1, CourseOpening co2) {
                String s1 = co1.getSemester();
                String s2 = co2.getSemester();
                if (s1.equals(s2)) return 0;
                if ("odd".equals(s1)) return -1;
                return 1;
            }
        });

        List<CourseOpening> sortedCourseOpenings = courseOpenings;
        for (CourseOpening courseOpening : sortedCourseOpenings) {
            if (courseOpening.getCourseId().equals(course_id)) {
                System.out.println(courseOpening.getCourseId() + "|" 
                    + courseOpening.getName() + "|" 
                    + courseOpening.getCredit() + "|" 
                    + courseOpening.getPassingGrade() + "|" 
                    + courseOpening.getYear() + "|" 
                    + courseOpening.getSemester() + "|" 
                    + courseOpening.getLecturerList());
                
                // Filtering enrollments for this course, year, and semester
                displayEnrollments(courseOpening.getCourseId(), courseOpening.getYear(), courseOpening.getSemester());
            }
        }
    }
    
    // Method to display enrollments for a specific course, year, and semester
    private static void displayEnrollments(String courseId, String year, String semester) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse_id().equals(courseId) 
                && enrollment.getYear().equals(year) 
                && enrollment.getSemester().equals(semester)) {
                
                System.out.println(enrollment.getCourse_id() + "|"
                    + enrollment.getStudent_id() + "|"
                    + enrollment.getYear() + "|" 
                    + enrollment.getSemester() + "|" 
                    + enrollment.getGrade());
            }
        }
    }

    public static void addEnrollment(String course_id, String student_id, String year, String semester) {
        Enrollment enrollment = new Enrollment(course_id, student_id, year, semester);
        enrollments.add(enrollment);
    }

    public static void addEnrollmentGrade(String course_id, String student_id, String year, String semester, String grade) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourse_id().equals(course_id) &&
                enrollment.getStudent_id().equals(student_id) &&
                enrollment.getYear().equals(year) &&
                enrollment.getSemester().equals(semester)) {
                enrollment.setGrade(grade);
                break;
            }
        }
    }

    public static void addEnrollmentRemedial(String courseId, String studentId, String year, String semester, String grade) {
        // Mengecek apakah enrollment sudah ada
        Enrollment existingEnrollment = null;
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getCourse_id().equals(courseId)
                    && enrollment.getStudent_id().equals(studentId)
                    && enrollment.getYear().equals(year)
                    && enrollment.getSemester().equals(semester)) {
                    existingEnrollment = enrollment;
                    break;
                }
            }
    
        if (existingEnrollment != null && !existingEnrollment.getGrade().contains("(")) {
            // Membuat enrollment baru untuk remedial dengan nilai sebelumnya dicatat
            String previousGrade = existingEnrollment.getGrade();
            existingEnrollment.setGrade(grade + "(" + previousGrade + ")");
        } else if (existingEnrollment == null) {
            // Jika enrollment belum ada, membuat enrollment baru untuk remedial
            Enrollment enrollment = new Enrollment(courseId, studentId, year, semester, grade);
            enrollments.add(enrollment);
        }
    }

    private static ArrayList<BestStudents> bestStudentsList = new ArrayList<>();

    public static void findBestStudent(String year, String semester) {
        // Filter enrollments based on the specified year
        ArrayList<Enrollment> filteredEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getYear().equals(year)) {
                filteredEnrollments.add(enrollment);
            }
        }
    
        // Calculate the best student for odd and even semesters
        Enrollment bestOdd = null;
        Enrollment bestEven = null;
        for (Enrollment enrollment : filteredEnrollments) {
            if (enrollment.getSemester().equalsIgnoreCase("odd")) {
                if (bestOdd == null || calculateGradePoints(enrollment.getGrade()) > calculateGradePoints(bestOdd.getGrade())) {
                    bestOdd = enrollment;
                }
            } else {
                if (bestEven == null || calculateGradePoints(enrollment.getGrade()) > calculateGradePoints(bestEven.getGrade())) {
                    bestEven = enrollment;
                }
            }
        }
    
        // If bestEven is null or bestOdd has a higher grade, set bestEven to bestOdd
        if (bestEven == null || (bestOdd != null && calculateGradePoints(bestOdd.getGrade()) > calculateGradePoints(bestEven.getGrade()))) {
            bestEven = bestOdd;
        }
    
        // Create a new BestStudents object and set the best students
        BestStudents bestStudents = new BestStudents(year, semester);
        bestStudents.setBestStudentOdd(bestOdd);
        bestStudents.setBestStudentEven(bestEven);
    
        // Add the best students to the list
        addBestStudent(bestStudents);
    }

    public static void addBestStudent(BestStudents bestStudents) {
        bestStudentsList.add(bestStudents);
    }

    public static void addBestStudent(String bestStudent) {
        // String[] tokens = bestStudent.split("/");
        // String year = tokens[0];
        // String semester = tokens[1];
        // String studentIdOdd = tokens[2];
        // String gradeOdd = tokens[3];
        // String studentIdEven = tokens[4];
        // ArrayList<BestStudents> bestStudentsList = new ArrayList<>();

        // String gradeEven = tokens[5];

        // // Create BestStudents objects and set the best students
        // BestStudents bestStudents = new BestStudents(year, semester);
        // Enrollment enrollmentOdd = new Enrollment("", studentIdOdd, year, semester, gradeOdd);
        // Enrollment enrollmentEven = new Enrollment("", studentIdEven, year, semester, gradeEven);
        // bestStudents.setBestStudentOdd(enrollmentOdd);
        // bestStudents.setBestStudentEven(enrollmentEven);

        // // Print the added best students
        // System.out.println(studentIdEven + "|" + gradeOdd + "/" + gradeEven);

        // // Add the best students to the list of best students
        // bestStudentsList.add(bestStudents);
    }

    public static void addStudentDetail(String studentId) {
        double totalCredit = 0;
        double totalGradePoints = 0;
    
        Map<String, Enrollment> lastEnrollmentMap = new HashMap<>();
        Map<String, String> originalGradesMap = new HashMap<>();
        for (Enrollment enrollment : enrollments) {
            String key = enrollment.getStudent_id() + "|" + enrollment.getCourse_id();
    
            // Mengecek apakah map sudah berisi enrollment untuk mata kuliah ini
            if (lastEnrollmentMap.containsKey(key)) {
    
                // Jika enrollment yang ada memiliki nilai remedial, jangan menggantinya dengan yang baru
                if (!lastEnrollmentMap.get(key).getGrade().contains("(")) {
                    lastEnrollmentMap.put(key, enrollment);
                    if (enrollment.getGrade().contains("(")) {
                        originalGradesMap.put(key, enrollment.getGrade().substring(0, enrollment.getGrade().indexOf("(")));
                    }
                }
            } else {
                lastEnrollmentMap.put(key, enrollment);
                if (enrollment.getGrade().contains("(")) {
                    originalGradesMap.put(key, enrollment.getGrade().substring(0, enrollment.getGrade().indexOf("(")));
                }
            }
        }
    
        for (Course course : courses) {
            String key = studentId + "|" + course.getId();
            if (lastEnrollmentMap.containsKey(key)) {
                Enrollment lastEnrollment = lastEnrollmentMap.get(key);
                String grade = lastEnrollment.getGrade();
                // Jika nilai adalah remedial, gunakan nilai remedial untuk perhitungan
                if (grade.contains("(")) {
                    grade = grade.substring(0, grade.indexOf("("));
                }
                if (!grade.equals("None")) {
                    totalCredit += Double.parseDouble(course.getCredit());
                    totalGradePoints += calculateGradePoints(grade) * Double.parseDouble(course.getCredit());
                }
            }
        }
    
        double gpa;
        if (totalCredit == 0) {
            gpa = 0;
        } else {
            gpa = totalGradePoints / totalCredit;
        }
    
        final double finalTotalCredit = totalCredit;
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                System.out.printf("%s|%s|%s|%s|%.2f|%.0f%n", 
                    student.getId(), 
                    student.getName(), 
                    student.getYear(),
                    student.getStudyProgram(), 
                    gpa, 
                    finalTotalCredit);
            }
        }
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

    public static ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static ArrayList<CourseOpening> getCourseOpenings() {
        return courseOpenings;
    }

    public static ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public static ArrayList<String> getBestStudents() {
        return bestStudents;
    }

    public static BestStudents[] getBestStudentsList() {
        return bestStudentsList.toArray(new BestStudents[0]);
    }

}