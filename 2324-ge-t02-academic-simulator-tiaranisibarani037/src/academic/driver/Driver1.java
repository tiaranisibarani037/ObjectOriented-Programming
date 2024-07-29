package academic.driver;

import java.util.ArrayList;

/**
 * @author 12S22037 Tiarani Sibarani
 * 
 */

import java.util.Scanner;
import academic.model.Course;

public class Driver1 {

    public static void main(String[] _args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<Course>();
        String str;

        while(true) {
            str = input.nextLine();
            if(str.equals("---")) {
                break;
            }
            else {
                String[] tokens = str.split("#");
                String course_id = tokens[0];
                String course_name = tokens[1];
                String credit = tokens[2];
                String passingGrade = tokens[3];

                Course course = new Course(course_id, course_name, credit, passingGrade);
                courses.add(course);

                System.out.println(course.getCourse_id() + "|" + course.getCourse_name() + "|" + course.getCredit() + "|" + course.getPassingGrade());
            }
        }
        input.close();

    }

}