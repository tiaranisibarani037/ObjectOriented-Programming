package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */

 public class Course extends Entity {
    private String credit;
    private String passingGrade;

    public Course(String _course_id, String _course_name, double _credit, String _passingGrade) {
        super(_course_id, _course_name);
        this.credit = Integer.toString((int) _credit);
        this.passingGrade = _passingGrade;
    }

    public String getCredit() {
        return credit;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

}