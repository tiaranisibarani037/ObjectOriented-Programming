package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Course {

    private String course_id;
    private String course_name;
    private String credit;
    private String passingGrade;

    public Course(String _course_id, String _course_name, String _credit2, String _passingGrade) {
        this.course_id = _course_id;
        this.course_name = _course_name;
        this.credit = _credit2;
        this.passingGrade = _passingGrade;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getCredit() {
        return credit;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

}