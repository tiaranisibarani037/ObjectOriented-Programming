package academic.model;

import java.util.List;
/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Course extends Entity {
    private String credit;
    private String passingGrade;
    private List<Lecturer> lecturers;

    // Constructor
    public Course(String _course_id, String _course_name, double _credit, String _passingGrade, List<Lecturer> _lecturers) {
        super(_course_id, _course_name);
        this.credit = Double.toString(_credit);
        this.passingGrade = _passingGrade;
        this.lecturers = _lecturers;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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