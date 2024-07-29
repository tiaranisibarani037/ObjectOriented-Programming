package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
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

    public String toString(){
        return this.course_id + "|" + this.student_id + "|" + this.year + "|" + this.semester  + "|" + this.grade;
    }

}