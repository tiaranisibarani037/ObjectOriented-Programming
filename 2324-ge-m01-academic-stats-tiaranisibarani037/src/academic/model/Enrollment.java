package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 * @author 12S22004 Bethania Hasibuan
 */
public class Enrollment {

    private String course_id;
    private String student_id;
    private String year;
    private String semester;
    private String grade = "None";

    public Enrollment(String _course_id, String _student_id, String _year, String _semester) {
        this.course_id = _course_id;
        this.student_id = _student_id;
        this.year = _year;
        this.semester = _semester;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
