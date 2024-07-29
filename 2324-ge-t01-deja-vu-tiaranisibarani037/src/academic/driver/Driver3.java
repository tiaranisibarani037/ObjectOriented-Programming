package academic.driver;

/**
 * @author 12S22037 Tiarani Sibarani
 */

import academic.model.Enrollment;
import java.util.Scanner;

public class Driver3 {

    public static void main(String[] _args) {

        Scanner input = new Scanner(System.in);

        String course_id = input.nextLine();
        String id = input.nextLine();
        String year = input.nextLine();
        String semester = input.nextLine();

        Enrollment enrollment = new Enrollment(course_id, id, year, semester);
        System.out.println(enrollment);
        
        input.close();

    }

}