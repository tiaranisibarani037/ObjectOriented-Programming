package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Lecturer extends Entity {
    private String initial;
    private String email;
    private String StudyProgram;

    public Lecturer(String IdDosen_, String NameDosen_, String initial_, String email_, String StudyProgram_) {
        super(IdDosen_, NameDosen_);
        this.initial = initial_;
        this.email = email_;
        this.StudyProgram = StudyProgram_;
    }

    public String getInitial() {
        return this.initial;
    }

    public String getEmail() {
        return this.email;
    }

    public String getStudyProgram() {
        return this.StudyProgram;
    }
}
