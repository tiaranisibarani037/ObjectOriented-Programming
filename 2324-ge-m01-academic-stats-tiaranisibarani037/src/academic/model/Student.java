package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 * @author 12S22004 Bethania Hasibuan
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

    public String getName() {
        return this.name;
    }

    public String getYear() {
        return this.year;
    }

    public String getStudyProgram() {
        return this.studyProgram;
    }

}