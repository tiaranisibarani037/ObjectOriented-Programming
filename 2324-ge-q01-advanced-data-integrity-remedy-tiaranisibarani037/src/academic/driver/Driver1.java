package academic.driver;

/**
 * @author 12S22037 Tiarani Sibarani
 */

import java.util.Scanner;
import java.util.ArrayList;
import academic.model.Lecturer;
import academic.model.Student;


public class Driver1 {

    public static void main(String[] _args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
        ArrayList<Student> students = new ArrayList<Student>();

        String str;
        boolean isFinished = false;

        while(input.hasNext()){
            str = input.nextLine();

            if (str.equals("---")){
                isFinished = true;
                break;
            }else{
                String[] tokens = str.split("#");
                String command = tokens[0];

                //lecturer-add#0130058501#Parmonangan Rotua Togatorop#PAT#mona.togatorop@del.ac.id#Information Systems
                if (command.equals("lecturer-add")){
                    String IdDosen = tokens[1];
                    String NameDosen = tokens[2];
                    String initial = tokens[3];
                    String email = tokens[4];
                    String StudyProgram = tokens[5];

                    boolean isIDdosenExist = false;

                    for (Lecturer lecturer : lecturers){
                        if (lecturer.getIdDosen().equals(IdDosen)){
                            isIDdosenExist = true;
                            break;
                        }
                    }

                    if(isIDdosenExist){
                        //System.out.println("Lecturer with the same ID already exists");
                    } else {
                        Lecturer lecturer = new Lecturer(IdDosen, NameDosen, initial, email, StudyProgram);
                        lecturers.add(lecturer);
                    }
                }

                else if (command.equals("student-add")) {
                    String id = tokens[1];
                    String name = tokens[2];
                    String year = tokens[3];
                    String studyProgram = tokens[4];
                    boolean isIdExist = false;
                    for (Student student: students) {
                        if (student.getId().equals(id)) {
                            isIdExist = true;
                            break;
                        }
                    }
                    if (isIdExist) {
                        //System.out.println("Student ID already exists.");
                    } else {
                        Student student = new Student(id, name, year, studyProgram);
                        students.add(student);
                    }

                }


            }
            
        }

        if(isFinished){
            for(Lecturer lecturer : lecturers){
                System.out.println(lecturer.getIdDosen() + "|" + lecturer.getNameDosen() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getStudyProgram());
            }

            for(Student student : students){
                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getStudyProgram());
            }
        }
         input.close();
    }

}