package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class AcademicController extends AbstractDatabase {

    public AcademicController(String url) throws SQLException {
        super(url);
    }

    //Pembuatan table untuk setiap entitas yang ada di dalam academic simulator
    protected void createTables() throws SQLException { 
        String lecturerDDL = "CREATE TABLE IF NOT EXISTS lecturers (" +
                "lecturer_id VARCHAR(10) NOT NULL," +
                "lecturer_name TEXT NOT NULL," +
                "initial VARCHAR(5) NOT NULL," +
                "email VARCHAR(255) NOT NULL," +
                "study_program TEXT NOT NULL" +
                ")";

        String courseDDL = "CREATE TABLE IF NOT EXISTS courses (" +
                "course_code VARCHAR(10) NOT NULL PRIMARY KEY," +
                "course_name TEXT NOT NULL," +
                "credits INT NOT NULL," +
                "grade VARCHAR(2) NOT NULL" +
                ")";

        String studentDDL = "CREATE TABLE IF NOT EXISTS students (" +
                "student_id VARCHAR(10) NOT NULL PRIMARY KEY," +
                "student_name TEXT NOT NULL," +
                "year INT NOT NULL," +
                "study_program TEXT NOT NULL" +
                ")";

        String enrollmentDDL = "CREATE TABLE IF NOT EXISTS enrollments (" +
                "course_code VARCHAR(10) NOT NULL," +
                "student_id VARCHAR(10) NOT NULL," +
                "year VARCHAR(10) NOT NULL," +
                "semester VARCHAR(50)," +
                "grade VARCHAR(20)" +
                ")";

        String enrollmentGradeDDL = "CREATE TABLE IF NOT EXISTS enrollment_grades (" +
                "course_code VARCHAR(10) NOT NULL," +
                "student_id VARCHAR(10) NOT NULL," +
                "year VARCHAR(10) NOT NULL," +
                "semester VARCHAR(2)," +
                "grade VARCHAR(10) NOT NULL," +
                "PRIMARY KEY (course_code, student_id)" +
                ")";

        String courseLecturersDDL = "CREATE TABLE IF NOT EXISTS course_lecturers (" +
                "course_code VARCHAR(10) NOT NULL," +
                "lecturer_id VARCHAR(10) NOT NULL," +
                "year VARCHAR(10) NOT NULL," +
                "semester VARCHAR(50)," +
                "PRIMARY KEY (lecturer_id)" +
                ")";

        String dropTableSQL = "DROP TABLE IF EXISTS course_opening"; // sebenarnya sama penggunaannya dengan query "if not exists"
        Statement statement = this.getConnection().createStatement();
        statement.execute(dropTableSQL);

        String course_openingDDL = "CREATE TABLE course_opening (" +
                "course_id VARCHAR(10) NOT NULL," +
                "year VARCHAR(10) NOT NULL," +
                "semester VARCHAR(50) NOT NULL," +
                "course_name TEXT NOT NULL," +
                "credit INT NOT NULL," +
                "passing_grade VARCHAR(10) NOT NULL," +
                "lecturer_list TEXT" +
                ")";

        //untuk mengeksekusi atau perintah untuk pembuatan tabel dalam databasenya
        statement.execute(course_openingDDL);
        statement.execute(lecturerDDL);
        statement.execute(courseDDL);
        statement.execute(studentDDL);
        statement.execute(enrollmentDDL);
        statement.execute(enrollmentGradeDDL);
        statement.execute(courseLecturersDDL);
        statement.close();
    }

    //fungsinya sebenarnya untuk mengisi tabel jika ingin diisi secara manual, tetapi di dalam tugas ini diisi sesuai dengan inputan yang diminta
    //jadi implementasi seed table kali ini digunakan untuk membersihkan tabel sebelum digunakan
    protected void seedTables() throws SQLException {
        String cleanDB[] = { "DELETE FROM courses;", "DELETE FROM course_opening;", "DELETE FROM students;",
                "DELETE FROM lecturers;", "DELETE FROM enrollments;", "DELETE FROM enrollment_grades" };

        //menjalin koneksi dengan database dan menghapus data yang ada di dalam tabel
        for (String sql : cleanDB) {
            Statement statement = this.getConnection().createStatement();
            statement.execute(sql);
        }
    }

    //menambahkan daftar dosen kedalam database yang telah dibuat sebelumnya, sehingga tidak memerlukan arraylist lagi
    public void addLecturer(String lecturerId, String lecturerName, String initial, String email, String programStudy)
            throws SQLException {

        String checkIdSQL = "SELECT COUNT(*) FROM lecturers WHERE lecturer_id = ?";
        try (PreparedStatement checkIdStatement = this.getConnection().prepareStatement(checkIdSQL)) {
            checkIdStatement.setString(1, lecturerId);
            ResultSet rs = checkIdStatement.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                String addLecturerSQL = "INSERT INTO lecturers (lecturer_id, lecturer_name, initial, email, study_program) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement lecturerStatement = this.getConnection().prepareStatement(addLecturerSQL); //gunanya untuk memasukkan data kedalam database dan mencegah injection dari dunia luar

                lecturerStatement.setString(1, lecturerId);
                lecturerStatement.setString(2, lecturerName);
                lecturerStatement.setString(3, initial);
                lecturerStatement.setString(4, email);
                lecturerStatement.setString(5, programStudy);

                lecturerStatement.executeUpdate();
                lecturerStatement.close();
            }
            checkIdStatement.close();
        }
    }

    //menambahkan mahasiswa ke dalam database
    public void addStudent(String studentId, String studentName, String year, String studyProgram) throws SQLException {
        String checkIdSQL = "SELECT COUNT(*) FROM students WHERE student_id = ?";
        try (PreparedStatement checkIdStatement = this.getConnection().prepareStatement(checkIdSQL)) { //pengecekan apakah id mahasiswa sudah ada di dalam database atau belum
            checkIdStatement.setString(1, studentId);
            ResultSet rs = checkIdStatement.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                String insertStudentSQL = "INSERT INTO students (student_id, student_name, year, study_program) VALUES (?, ?, ?, ?)";
                PreparedStatement studentStatement = this.getConnection().prepareStatement(insertStudentSQL);

                studentStatement.setString(1, studentId);
                studentStatement.setString(2, studentName);
                studentStatement.setString(3, year);
                studentStatement.setString(4, studyProgram);

                studentStatement.executeUpdate();
                studentStatement.close();
            }
        }
    }

    //menambahkan kursus ke database
    public void addCourse(String courseCode, String courseName, double credit, String grade) throws SQLException {
        String checkCourseSQL = "SELECT COUNT(*) FROM courses WHERE course_code = ?";
        try (PreparedStatement checkCourseStatement = this.getConnection().prepareStatement(checkCourseSQL)) {
            checkCourseStatement.setString(1, courseCode);
            ResultSet rs = checkCourseStatement.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                String insertCourseSQL = "INSERT INTO courses (course_code, course_name, credits, grade) VALUES (?, ?, ?, ?)";
                PreparedStatement courseStatement = this.getConnection().prepareStatement(insertCourseSQL);

                courseStatement.setString(1, courseCode);
                courseStatement.setString(2, courseName);
                courseStatement.setDouble(3, credit);
                courseStatement.setString(4, grade);

                courseStatement.executeUpdate();
                courseStatement.close();
            }
        }
    }

    //menambahkan enrollment ke dalam database
    public void addEnrollment(String courseCode, String studentId, String year, String semester) throws SQLException {
        String insertEnrollmentSQL = "INSERT INTO enrollments (course_code, student_id, year, semester) VALUES (?, ?, ?, ?)";
        try (PreparedStatement enrollmentStatement = this.getConnection().prepareStatement(insertEnrollmentSQL)) {
            enrollmentStatement.setString(1, courseCode);
            enrollmentStatement.setString(2, studentId);
            enrollmentStatement.setString(3, year);
            enrollmentStatement.setString(4, semester);

            enrollmentStatement.executeUpdate();
        }
    }

    //menambahkan grade ke dalam database atau dengan kata lain mengupdate enrollment yang sudah ada sebelumnya
    public void addEnrollmentGrade(String courseId, String studentId, String year, String semester, String grade)
            throws SQLException {
        String checkEnrollmentSQL = "SELECT COUNT(*) FROM enrollments WHERE course_code = ? AND student_id = ? AND year = ? AND semester = ?";
        try (PreparedStatement checkEnrollmentStatement = this.getConnection().prepareStatement(checkEnrollmentSQL)) {
            checkEnrollmentStatement.setString(1, courseId);
            checkEnrollmentStatement.setString(2, studentId);
            checkEnrollmentStatement.setString(3, year);
            checkEnrollmentStatement.setString(4, semester);
            ResultSet rs = checkEnrollmentStatement.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                String updateEnrollmentGradeSQL = "UPDATE enrollments SET grade = ? WHERE course_code = ? AND student_id = ? AND year = ? AND semester = ?";
                PreparedStatement enrollmentStatement = this.getConnection().prepareStatement(updateEnrollmentGradeSQL);

                enrollmentStatement.setString(1, grade);
                enrollmentStatement.setString(2, courseId);
                enrollmentStatement.setString(3, studentId);
                enrollmentStatement.setString(4, year);
                enrollmentStatement.setString(5, semester);

                enrollmentStatement.executeUpdate();
                enrollmentStatement.close();
            }
        }
    }

    //mengeset grade remedial ke dalam database dengan update enrollment yang telah diambil mahasiswa sebelumnya
    public void addEnrollmentRemedial(String courseId, String studentId, String year, String semester, String grade)
            throws SQLException {
        String checkEnrollmentSQL = "SELECT grade FROM enrollments WHERE course_code = ? AND student_id = ? AND year = ? AND semester = ?";
        try (PreparedStatement checkEnrollmentStatement = this.getConnection().prepareStatement(checkEnrollmentSQL)) {
            checkEnrollmentStatement.setString(1, courseId);
            checkEnrollmentStatement.setString(2, studentId);
            checkEnrollmentStatement.setString(3, year);
            checkEnrollmentStatement.setString(4, semester);
            ResultSet rs = checkEnrollmentStatement.executeQuery();

            if (rs.next()) {
                String existingGrade = rs.getString(1);
                if (existingGrade != null && !existingGrade.contains("(")) {
                    String newGrade = grade + "(" + existingGrade + ")";
                    String updateEnrollmentGradeSQL = "UPDATE enrollments SET grade = ? WHERE course_code = ? AND student_id = ? AND year = ? AND semester = ?";
                    PreparedStatement enrollmentStatement = this.getConnection()
                            .prepareStatement(updateEnrollmentGradeSQL);

                    enrollmentStatement.setString(1, newGrade);
                    enrollmentStatement.setString(2, courseId);
                    enrollmentStatement.setString(3, studentId);
                    enrollmentStatement.setString(4, year);
                    enrollmentStatement.setString(5, semester);

                    enrollmentStatement.executeUpdate();
                    enrollmentStatement.close();
                }
            } else {
                String insertEnrollmentSQL = "INSERT INTO enrollments (course_code, student_id, year, semester, grade) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement enrollmentStatement = this.getConnection().prepareStatement(insertEnrollmentSQL);

                enrollmentStatement.setString(1, courseId);
                enrollmentStatement.setString(2, studentId);
                enrollmentStatement.setString(3, year);
                enrollmentStatement.setString(4, semester);
                enrollmentStatement.setString(5, grade);

                enrollmentStatement.executeUpdate();
                enrollmentStatement.close();
            }
        }
    }

    //menambahkan pembukaan kursus ke dalam database yang dilengkapi dengan initial dan email dari dosen pengampu mata kuliah
    public void addCourseOpening(String courseId, String year, String semester, String lecturerInitials)
            throws SQLException {
        // Memisahkan inisial dosen
        String[] initials = lecturerInitials.split(",");

        // Mencari detail mata kuliah yang sesuai
        String courseSQL = "SELECT course_name, credits, grade FROM courses WHERE course_code = ?";
        try (PreparedStatement courseStatement = this.getConnection().prepareStatement(courseSQL)) {
            courseStatement.setString(1, courseId);
            ResultSet courseRs = courseStatement.executeQuery();

            if (courseRs.next()) {
                String courseName = courseRs.getString(1);
                int courseCredit = courseRs.getInt(2); // change this line
                String coursePassingGrade = courseRs.getString(3);

                // Membuat dan menambahkan pembukaan mata kuliah baru
                String insertCourseOpeningSQL = "INSERT INTO course_opening (course_id, year, semester, course_name, credit, passing_grade) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insertCourseOpeningStatement = this.getConnection()
                        .prepareStatement(insertCourseOpeningSQL)) {
                    insertCourseOpeningStatement.setString(1, courseId);
                    insertCourseOpeningStatement.setString(2, year);
                    insertCourseOpeningStatement.setString(3, semester);
                    insertCourseOpeningStatement.setString(4, courseName);
                    insertCourseOpeningStatement.setInt(5, courseCredit); // change this line
                    insertCourseOpeningStatement.setString(6, coursePassingGrade);
                    insertCourseOpeningStatement.executeUpdate();
                }

                // Mencari dosen berdasarkan inisial dan menambahkannya ke dalam daftar
                // courseLecturers
                StringBuilder sb = new StringBuilder();
                for (String initial : initials) {
                    String lecturerSQL = "SELECT lecturer_id, email FROM lecturers WHERE initial = ?";
                    try (PreparedStatement lecturerStatement = this.getConnection().prepareStatement(lecturerSQL)) {
                        lecturerStatement.setString(1, initial.trim());
                        ResultSet lecturerRs = lecturerStatement.executeQuery();

                        if (lecturerRs.next()) {
                            String lecturerId = lecturerRs.getString(1);
                            String lecturerEmail = lecturerRs.getString(2);

                            // Menambahkan dosen ke pembukaan mata kuliah
                            String insertCourseLecturerSQL = "INSERT IGNORE INTO course_lecturers (course_code, lecturer_id, year, semester) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement insertCourseLecturerStatement = this.getConnection()
                                    .prepareStatement(insertCourseLecturerSQL)) {
                                insertCourseLecturerStatement.setString(1, courseId);
                                insertCourseLecturerStatement.setString(2, lecturerId);
                                insertCourseLecturerStatement.setString(3, year);
                                insertCourseLecturerStatement.setString(4, semester);
                                insertCourseLecturerStatement.executeUpdate();
                            }

                            // Menambahkan dosen dan email mereka ke StringBuilder
                            sb.append(initial.trim()).append(" (").append(lecturerEmail).append(");");
                        }
                    }
                }
                // Remove the last semicolon
                if (sb.length() > 0) {
                    sb.setLength(sb.length() - 1);
                }
            }
        }
    }

    //menampilkan daftar dosen dan kursus yang telah diambil mahasiswa yang ada di dalam database
    public void displayCourseHistory(String courseId) throws SQLException {
        String courseHistorySQL = "SELECT co.year, co.semester, c.course_name, c.credits, c.grade, l.initial, l.email "
                +
                "FROM course_opening co " +
                "JOIN course_lecturers cl ON co.course_id = cl.course_code "
                +
                "JOIN lecturers l ON cl.lecturer_id = l.lecturer_id " +
                "JOIN courses c ON co.course_id = c.course_code " +
                "WHERE co.course_id = ? " +
                "ORDER BY CASE WHEN co.semester = 'odd' THEN 1 ELSE 2 END";
        try (PreparedStatement courseHistoryStatement = this.getConnection().prepareStatement(courseHistorySQL)) {
            courseHistoryStatement.setString(1, courseId);
            ResultSet courseHistoryRs = courseHistoryStatement.executeQuery();
            while (courseHistoryRs.next()) {
                String year = courseHistoryRs.getString(1);
                String semester = courseHistoryRs.getString(2);
                String courseName = courseHistoryRs.getString(3);
                int courseCredit = courseHistoryRs.getInt(4);
                String coursePassingGrade = courseHistoryRs.getString(5);
                String lecturerInitial = courseHistoryRs.getString(6);
                String lecturerEmail = courseHistoryRs.getString(7);
    
                System.out.println(courseId + "|"
                        + courseName + "|"
                        + courseCredit + "|"
                        + coursePassingGrade + "|"
                        + year + "|"
                        + semester + "|"
                        + lecturerInitial + " (" + lecturerEmail + ")");
    
                displayEnrollments(courseId, year, semester);
                
            }
        }
    }

    //menampilkan enrollment yang ada di dalam database
    private void displayEnrollments(String courseId, String year, String semester) throws SQLException {
        String enrollmentSQL = "SELECT student_id, grade FROM enrollments WHERE course_code = ? AND year = ? AND semester = ?";
        try (PreparedStatement enrollmentStatement = this.getConnection().prepareStatement(enrollmentSQL)) {
            enrollmentStatement.setString(1, courseId);
            enrollmentStatement.setString(2, year);
            enrollmentStatement.setString(3, semester);
            ResultSet enrollmentRs = enrollmentStatement.executeQuery();

            while (enrollmentRs.next()) {
                String studentId = enrollmentRs.getString(1);
                String grade = enrollmentRs.getString(2);

                System.out.println(courseId + "|"
                        + studentId + "|"
                        + year + "|"
                        + semester + "|"
                        + grade);
            }
        }
    }

    //menampilkan transkrip mahasiswa yang ada di dalam database lengkap dengan enrollment yang telah diambil mahasiswa
    public void displayStudentTranscript(String studentId) throws SQLException {
        String enrollmentSQL = "SELECT course_code, year, semester, grade FROM enrollments WHERE student_id = ? ORDER BY course_code, year DESC, semester DESC";
        try (PreparedStatement enrollmentStatement = this.getConnection().prepareStatement(enrollmentSQL)) {
            enrollmentStatement.setString(1, studentId);
            ResultSet enrollmentRs = enrollmentStatement.executeQuery();

            // Display student detail
            addStudentDetail(studentId);

            Map<String, String> lastEnrollmentMap = new HashMap<>();
            while (enrollmentRs.next()) {
                String courseId = enrollmentRs.getString(1);
                String year = enrollmentRs.getString(2);
                String semester = enrollmentRs.getString(3);
                String grade = enrollmentRs.getString(4);

                // If the map already contains an enrollment for this course, don't replace it
                // with the new one
                if (!lastEnrollmentMap.containsKey(courseId)) {
                    String detail = year + "|" + semester + "|" + grade;
                    lastEnrollmentMap.put(courseId, detail);
                }
            }

            for (String courseId : lastEnrollmentMap.keySet()) {
                String detail = lastEnrollmentMap.get(courseId);
                String[] parts = detail.split("\\|");
                String year = parts[0];
                String semester = parts[1];
                String grade = parts[2];

                System.out.println(courseId + "|"
                        + studentId + "|"
                        + year + "|"
                        + semester + "|"
                        + grade);
            }
        }
    }

    //melakukan perhitungan IPK yang dimiliki oleh mahasiswa yang ada di dalam database
    public void addStudentDetail(String studentId) throws SQLException {
        double totalCredit = 0;
        double totalGradePoints = 0;

        String enrollmentSQL = "SELECT course_code, grade, year, semester FROM enrollments WHERE student_id = ? ORDER BY course_code, year DESC, semester DESC";
        Map<String, String> lastEnrollmentMap = new HashMap<>();
        try (PreparedStatement enrollmentStatement = this.getConnection().prepareStatement(enrollmentSQL)) {
            enrollmentStatement.setString(1, studentId);
            ResultSet rs = enrollmentStatement.executeQuery();

            while (rs.next()) {
                String courseId = rs.getString(1);
                String grade = rs.getString(2);

                // If the grade is remedial, use the original grade for calculation
                if (grade.contains("(")) {
                    grade = grade.substring(0, grade.indexOf("("));
                }

                // If the map already contains an enrollment for this course, don't replace it
                // with the new one
                if (!lastEnrollmentMap.containsKey(courseId)) {
                    lastEnrollmentMap.put(courseId, grade);
                }
            }
        }

        for (String courseId : lastEnrollmentMap.keySet()) {
            String grade = lastEnrollmentMap.get(courseId);

            String courseSQL = "SELECT credits FROM courses WHERE course_code = ?";
            try (PreparedStatement courseStatement = this.getConnection().prepareStatement(courseSQL)) {
                courseStatement.setString(1, courseId);
                ResultSet courseRs = courseStatement.executeQuery();

                if (courseRs.next()) {
                    double credit = courseRs.getDouble(1);
                    totalCredit += credit;
                    totalGradePoints += calculateGradePoints(grade) * credit;
                }
            }
        }

        double gpa;
        if (totalCredit == 0) {
            gpa = 0;
        } else {
            gpa = totalGradePoints / totalCredit;
        }

        String studentSQL = "SELECT student_id, student_name, year, study_program FROM students WHERE student_id = ?";
        try (PreparedStatement studentStatement = this.getConnection().prepareStatement(studentSQL)) {
            studentStatement.setString(1, studentId);
            ResultSet studentRs = studentStatement.executeQuery();

            if (studentRs.next()) {
                System.out.printf("%s|%s|%s|%s|%.2f|%.0f%n",
                        studentRs.getString(1),
                        studentRs.getString(2),
                        studentRs.getString(3),
                        studentRs.getString(4),
                        gpa,
                        totalCredit);
            }
        }
    }

    //menghitung grade point yang dimiliki oleh mahasiswa yang ada di dalam database
    private double calculateGradePoints(String grade) throws SQLException {
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

    //membuat dan menyiapkan table yang akan digunakan sebagai tempat penyimpanan data dalam database
    @Override
    protected void prepareTables() throws SQLException {
        this.createTables();
        this.seedTables();
    }

}