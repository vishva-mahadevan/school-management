/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement implements Serializable{
    StudentManagement() throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Student> records = Student.getRecords();
        for (int i = 0; i < records.size(); i++) {
            Student person = records.get(i);
            System.out.print("Rollno:" + person.getRollno() + "\tName:" + person.getName() + "\tAge:" + person.getAge() + "\tDOB:" + person.getDob() + "\tClass:" + person.getStandard());
            ArrayList<Subject> marks = records.get(i).getSubjects();
            for (int j = 0; j < marks.size(); j++) {
                Subject mark = marks.get(j);
                System.out.print("\t" + mark.getSubjectname() + ":" + mark.getMark() + "\tGrade:" + mark.getGrade());
            }
            System.out.println("");
        }
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        int choice;
        do {
            System.out.println("1.Add Student\n2.Show All Students\n3.Filter Students\n4.Search Student\n5.Update Student Record\n6.Delete Record\n7.Average Percentage of Class\n8.Average Marks of Student\n9.Exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    addStudentData(scan);
                    break;
                case 2:
                    displayStudents(scan);
                    break;
                case 3:
                    filterStudentData(scan);
                    break;
                case 4:
                    searchStudentData(scan);
                    break;
                case 5:
                    updateStudentData(scan);
                    break;
                case 6:
                    deleteStudentData(scan);
                    break;
                case 7:getAveragePercentage(scan);
                    break;
                case 8:
                    System.out.println("Enter the rollno:");
                    int rollno=scan.nextInt();
                    float avg=getAverageMark(rollno,Student.getRecords());
                    System.out.println("Average mark:"+avg*100);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Enter valid choice!!");
            }
        } while (choice != 9);
    }

    private static void displayStudents(Scanner scan) throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Student> records = Student.getRecords();
        for (int i = 0; i < records.size(); i++) {
            Student person = records.get(i);
            System.out.print("Rollno:" + person.getRollno() + "\tName:" + person.getName() + "\tAge:" + person.getAge() + "\tDOB:" + person.getDob() + "\tClass:" + person.getStandard());
            ArrayList<Subject> marks = records.get(i).getSubjects();
            for (int j = 0; j < marks.size(); j++) {
                Subject mark = marks.get(j);
                System.out.print("\t" + mark.getSubjectname() + ":" + mark.getMark() + "\tGrade:" + mark.getGrade());
            }
            System.out.println("");
        }
    }

    private static void addStudentData(Scanner scan) throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.print("Enter Name of Student:");
        String name = scan.next();
        System.out.print("\nEnter Age:");
        int age = scan.nextInt();
        System.out.print("\nEnter Date of Birth(DD/MM/YYYY):");
        String dob = scan.next();
        System.out.print("\nEnter Class:");
        String standard = scan.next();
        System.out.println("Enter No. of Subjects");
        int subjectcount = scan.nextInt();
        ArrayList<Subject> subjects = new ArrayList<>();
        ArrayList<Student> records ;
        try{
            records= Student.getRecords();
        }
        catch(Exception e){
            records=new ArrayList<>();
        }
        for (int i = 0; i < subjectcount; i++) {
            System.out.print("\nEnter Subject Name:");
            String subjectname = scan.next();
            System.out.print("\nEnter Subject ID");
            String subjectid = scan.next();
            System.out.print("\nEnter mark");
            int mark = scan.nextInt();
            Subject newsubject = new Subject(mark, subjectname, subjectid);
            subjects.add(newsubject);
        }
        Student newstudent = new Student(name, age, dob, standard, subjects);
        records.add(newstudent);
        Student.setRecords(records);
        
        System.out.println("\nEntry Added Successfully!!");
    }

    private static void updateStudentData(Scanner scan) throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("Enter the RollNo:");
        int rollno = scan.nextInt();
        ArrayList<Student> records = Student.getRecords();
        for (int i = 0; i < records.size(); i++) {
            Student person = records.get(i);
            if (person.getRollno() == rollno) {
                System.out.print("Enter Name of Student:");
                String name = scan.next();
                person.setName(name);
                System.out.print("\nEnter Age:");
                int age = scan.nextInt();
                person.setAge(age);
                System.out.print("\nEnter Date of Birth(DD/MM/YYYY):");
                String dob = scan.next();
                person.setDob(dob);
                System.out.print("\nEnter Class:");
                String standard = scan.next();
                person.setStandard(standard);
                ArrayList<Subject> subjects = person.getSubjects();
                for (int j = 0; j < subjects.size(); i++) {
                    Subject subject = subjects.get(age);
                    System.out.print("Subjectname" + subject.getSubjectname());
                    System.out.print("\nEnter mark");
                    int mark = scan.nextInt();
                    subject.setMark(mark);
                    System.out.println("");
                }
            }
        }
    }

    private static void deleteStudentData(Scanner scan) throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("Enter the RollNo:");
        int rollno = scan.nextInt();
        ArrayList<Student> students = Student.getRecords();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (rollno == student.getRollno()) {
                students.remove(i);
                System.out.println("Record Removed successfully!!");
                Student.setRecords(students);
                break;
            }
        }

    }

    private static void searchStudentData(Scanner scan) throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("Enter the RollNo:");
        int rollno = scan.nextInt();
        ArrayList<Student> records = Student.getRecords();
        for (int i = 0; i < records.size(); i++) {
            Student person = records.get(i);
            if (person.getRollno() == rollno) {
                System.out.print("Rollno:" + person.getRollno() + "\tName:" + person.getName() + "\tAge:" + person.getAge() + "\tDOB:" + person.getDob() + "\tClass:" + person.getStandard());
                ArrayList<Subject> marks = records.get(i).getSubjects();
                for (int j = 0; j < marks.size(); j++) {
                    Subject mark = marks.get(j);
                    System.out.print("\t" + mark.getSubjectname() + ":" + mark.getMark() + "\tGrade:" + mark.getGrade());
                }
                System.out.println("");
            }
        }
    }

    private static void filterStudentData(Scanner scan) throws IOException, FileNotFoundException, ClassNotFoundException {
        int choice;
        do {
            System.out.println("\n1.Filter By Name\n2.Filter By Age\n3.Filter By Grade\n4.Back");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    filterStudentByName();
                    break;
                case 2:
                    filterStudentByAge();
                    break;
                case 3:
                    filterStudentByGrad();
                    break;
            }
        } while (choice != 4);

    }

    private static void displayData(ArrayList<Student> records) {
        for (int i = 0; i < records.size(); i++) {
            Student person = records.get(i);
            System.out.print("Rollno:" + person.getRollno() + "\tName:" + person.getName() + "\tAge:" + person.getAge() + "\tDOB:" + person.getDob() + "\tClass:" + person.getStandard());
            ArrayList<Subject> marks = records.get(i).getSubjects();
            for (int j = 0; j < marks.size(); j++) {
                Subject mark = marks.get(j);
                System.out.print("\t" + mark.getSubjectname() + ":" + mark.getMark() + "\tGrade:" + mark.getGrade());
            }
            System.out.println("");
        }
    }

    private static void filterStudentByName() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Student> records = Student.getRecords();
        records.sort((first, second) -> {
            return first.getName().compareTo(second.getName());
        });
        displayData(records);

    }

    private static void filterStudentByAge() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Student> records = Student.getRecords();
        records.sort((first, second) -> {
            if(first.getAge()>second.getAge()){
                return -1;
            }
            else{
                return 1;
            }
        });
        displayData(records);
    }

    private static void filterStudentByGrad() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Student> records = Student.getRecords();
        records.sort((first, second) -> {
            return first.getName().compareTo(second.getName());
        });
        displayData(records);
    }
    
    private static void getAveragePercentage(Scanner scan) throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("Enter the class:");
        String standard=scan.next();
        float totalmark=0;
        int totalstudent=0;
        ArrayList<Student> records = Student.getRecords();
        for(int i=0;i<records.size();i++){
            Student students=records.get(i);
            if(students.getStandard().equals(standard)){
                totalstudent++;
                totalmark+=getAverageMark(students.getRollno(),records);
            }
        }
        float total=totalmark/(float)totalstudent;
        System.out.println("Total Average Percentage of class"+standard+"is"+total*100+"%");
    }

    private static float getAverageMark(int rollno, ArrayList<Student> records) {
        int totalmark=0;
        int subjetcount=0;
        for(int i=0;i<records.size();i++){
            Student student=records.get(i);
            if(rollno==student.getRollno()){
                ArrayList<Subject> subjects=student.getSubjects();
                subjetcount=subjects.size();
                for(int j=0;j<subjects.size();j++){
                    Subject subject=subjects.get(i);
                    totalmark+=subject.getMark();
                }
            }
        }
        return (float)totalmark/(float)subjetcount;
    }

}
