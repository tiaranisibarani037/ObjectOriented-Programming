package academic.driver;

import java.util.ArrayList;
import java.util.Scanner;
import academic.model.Student;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Driver2 {

    public static void main(String[] _args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<Student>();
        String str;

        while(true) {
            str = input.nextLine();
            if(str.equals("---")) {
                break;
            }
            else {
                String[] tokens = str.split("#");
                String id = tokens[0];
                String name = tokens[1];
                String year = tokens[2];
                String studyProgram = tokens[3];

                Student student = new Student(id, name, year, studyProgram);
                students.add(student);

                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getStudyProgram());

            }
        }
        input.close();

    }

}