package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Course {

    // definisikanlah kelas Course dengan objek code, name, credit, passing grade
    private String course_id;
    private String name;
    private int credit;
    private String passingGrade;

    public Course(String _course_id, String _name, int _credit, String _passingGrade) {
        this.course_id = _course_id;
        this.name = _name;
        this.credit = _credit;
        this.passingGrade = _passingGrade;
    }


    public String toString(){
        return this.course_id + "|" + this.name + "|" + this.credit + "|" + this.passingGrade;
    }



}