package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Student {

    private String id;
    private String name;
    private String GPA;
    private String credit;

    public Student() {
    }

    public Student(String _id, String _name, String gPA2, String _credit) {
        this.id = _id;
        this.name = _name;
        this.GPA = gPA2;
        this.credit = _credit;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getGPA() {
        return this.GPA;
    }

    public String getCredit() {
        return this.credit;
    }

}