package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 * @author 12S22003 Yohana Siahaan
 */

public class BestStudents {
    private String year;
    private String semester;
    private Enrollment bestStudentOdd;
    private Enrollment bestStudentEven;

    public BestStudents(String year, String semester){
        this.year = year;
        this.semester = semester;
    }

    public String getYear(){
        return year;
    }

    public String getSemester(){
        return semester;
    }

    public Enrollment getBestStudentOdd() {
        return bestStudentOdd;
    }

    public void setBestStudentOdd(Enrollment bestStudentOdd) {
        this.bestStudentOdd = bestStudentOdd;
    }

    public Enrollment getBestStudentEven() {
        return bestStudentEven;
    }

    public void setBestStudentEven(Enrollment bestStudentEven) {
        this.bestStudentEven = bestStudentEven;
    }

    @Override
    public String toString() {
        String bestStudentOddGrade = bestStudentOdd != null ? bestStudentOdd.getGrade() : "N/A";
        String bestStudentEvenGrade = bestStudentEven != null ? bestStudentEven.getGrade() : "N/A";
        String bestStudentEvenId = bestStudentEven != null ? bestStudentEven.getStudent_id() : "N/A";

        return bestStudentEvenId + "|" + bestStudentOddGrade + "/" + bestStudentEvenGrade;
    }
}