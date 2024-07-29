package academic.driver;

import java.util.Scanner;

import academic.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;;

/**
 * @author 12S22037 Tiarani Sibarani
 */
public class Driver2 {

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
                    for(Student student : students){
                        System.out.println(student.getId() + "|" + student.getName() + "|" + student.getGPA() + "|" + student.getCredit());
                    }
                }
                else if(command.equals("student-best")){
                    int n = Integer.parseInt(tokens[1]); 
                    //pernyataan yang digunakan untuk mengambil jumlah terbaik mahasiswa yang akan ditampilkan, yang diberikan oleh pengguna sebagai argumen kedua setelah perintah. 
                    Collections.sort(students,new Comparator<Student>() {
                        public int compare(Student s1, Student s2) {
                            double gpa1 = Double.parseDouble(s1.getGPA());
                            double gpa2 = Double.parseDouble(s2.getGPA());
                            return Double.compare(gpa2, gpa1);
                        }
                    }
                    );
                    //mencetak sejumlah terbaik mahasiswa, sesuai dengan jumlah yang telah ditentukan (n).
                    for (int i=0 ;i < n; i++) {
                       System.out.println(students.get(i).getId() + "|" + students.get(i).getName() + "|" + students.get(i).getGPA() + "|" + students.get(i).getCredit());
                    }
                }
                
            }
        }

        input.close();


    }

}