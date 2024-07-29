package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Student extends Entity{

    private String year;
    private String studyProgram;

    public Student() {
    }

    public Student(String _id, String _name, String _year, String _studyProgram) {
        super(_id, _name);
        this.year = _year;
        this.studyProgram = _studyProgram;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return this.year;
    }

    public String getStudyProgram() {
        return this.studyProgram;
    }

}