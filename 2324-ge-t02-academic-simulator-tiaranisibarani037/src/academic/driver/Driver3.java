package academic.driver;

import java.util.ArrayList;
import java.util.Scanner;
import academic.model.Enrollment;


/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Driver3 {

    public static void main(String[] _args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
        String str;

        while(true) {
            str = input.nextLine();
            if(str.equals("---")) {
                break;
            }
            else {
                String[] tokens = str.split("#");
                String course_id = tokens[0];
                String student_id = tokens[1];
                String year = tokens[2];
                String semester = tokens[3];

                Enrollment enrollment = new Enrollment(course_id, student_id, year, semester);
                enrollments.add(enrollment);

                System.out.println(enrollment.toString());

            }
        }
        input.close();

    }

}