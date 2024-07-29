package academic.model;

import java.util.List;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class CourseOpening {

    // class definition
    private String course_id;
    private String year;
    private String semester;
    private String name;
    private String credit;
    private String passingGrade;
    private List<Lecturer> lecturers;

    //constructor

    public CourseOpening(String course_id, String year, String semester, String name, String credit, String passingGrade, List<Lecturer> lecturers) {
        this.course_id = course_id;
        this.year = year;
        this.semester = semester;
        this.name = name;
        this.credit = credit;
        this.passingGrade = passingGrade;
        this.lecturers = lecturers;
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

    public String getCourseId() {
        return course_id;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
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