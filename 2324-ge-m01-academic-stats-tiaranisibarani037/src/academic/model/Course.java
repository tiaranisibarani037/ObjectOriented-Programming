package academic.model;

import java.util.List;
/**
 * @author 12S22037 Tiarani Sibarani
 * @author 12S22004 Bethania Hasibuan
 */
public class Course {
    private String course_id;
    private String course_name;
    private String credit;
    private String passingGrade;
    private List<Lecturer> lecturers;

    // Constructor
    public Course(String _course_id, String _course_name, double _credit, String _passingGrade, List<Lecturer> _lecturers) {
        this.course_id = _course_id;
        this.course_name = _course_name;
        this.credit = Double.toString(_credit);
        this.passingGrade = _passingGrade;
        this.lecturers = _lecturers;
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

    public String getLecturerList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lecturers.size(); i++) {
            Lecturer lecturer = lecturers.get(i);
            sb.append(lecturer.getInitial()).append(" (").append(lecturer.getEmail()).append(")");
            if (i < lecturers.size() - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }


}