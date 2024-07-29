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
    private List<Lecturer> lecturers;

    public CourseOpening(String _course_id, String _year, String _semester, List<Lecturer> _lecturers) {
        this.course_id = _course_id;
        this.year = _year;
        this.semester = _semester;
        this.lecturers = _lecturers;
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