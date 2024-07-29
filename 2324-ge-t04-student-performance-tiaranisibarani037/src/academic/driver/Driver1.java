package academic.driver;

import java.util.Scanner;

import academic.model.Student;

import java.util.ArrayList;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Driver1 {

    public static void main(String[] _args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        String str;

        while(input.hasNext()) {
            str = input.nextLine();
            if(str.equals("---")){
                break;
            } else {
                String [] tokens = str.split("#");
                String command = tokens[0];

                if(command.equals("student-add")){
                    String id = tokens[1];
                    String name = tokens[2];
                    String GPA = tokens[3];
                    String credit =  tokens[4];
                    
                    Student student = new Student(id,name,GPA,credit);
                    students.add(student);

                }
                else if(command.equals("student-show-all")){
                    //for each digunakan untuk mengakses setiap elemen dari arraylist (semua)
                    for(Student student : students){
                        System.out.println(student.getId() + "|" + student.getName() + "|" + student.getGPA() + "|" + student.getCredit());
                    }
                }
            }
        }

        input.close();


    }

}