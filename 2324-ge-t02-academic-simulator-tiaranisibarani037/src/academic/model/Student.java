package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Student {

    private String id;
    private String name;
    private String year;
    private String studyProgram;

    public Student() {
    }

    public Student(String _id, String _name, String _year, String _studyProgram) {
        this.id = _id;
        this.name = _name;
        this.year = _year;
        this.studyProgram = _studyProgram;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String _id) {
        this.id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String _year) {
        this.year = _year;
    }

    public String getStudyProgram() {
        return this.studyProgram;
    }

    public void setStudyProgram(String _studyProgram) {
        this.studyProgram = _studyProgram;
    }

}