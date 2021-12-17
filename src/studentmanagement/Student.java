/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

    private static int uniqueid = 10000;
    private int rollno;
    private String name;
    private int age;
    private String dob;
    private String standard;
    private String grade;
    private ArrayList<Subject> subjects = new ArrayList<>();
    private static ArrayList<Student> records = new ArrayList<>();

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public static ArrayList<Student> getRecords() throws FileNotFoundException, IOException, ClassNotFoundException {
        // Reading the object from a file
        FileInputStream file = new FileInputStream("F:\\Helping\\ObjecOrientedDesign\\StudentManagement\\src\\studentmanagement\\studentRecord.txt");
        ObjectInputStream in = new ObjectInputStream(file);

        // Method for deserialization of object
        ArrayList<Student> object = (ArrayList<Student>) in.readObject();

        in.close();
        file.close();
        System.out.println("Object has been deserialized\n"
                + "Data after Deserialization.");
        for(int i=0;i<records.size();i++){
            uniqueid=Math.max(uniqueid,records.get(i).getRollno());
        }
        uniqueid++;
        return object;
    }

    public static void setRecords(ArrayList<Student> records) throws FileNotFoundException, IOException {

        Student.records = records;
        FileOutputStream fileOut = new FileOutputStream("F:\\Helping\\ObjecOrientedDesign\\StudentManagement\\src\\studentmanagement\\studentRecord.txt");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(records);
        out.close();
    }

    private static String calculateGrade(ArrayList<Subject> subjects) {
        int total = 0;
        for (int i = 0; i < subjects.size(); i++) {
            Subject subject = subjects.get(i);
            total += subject.getMark();
        }
        int ftotal = total / subjects.size();
        return Subject.calculateGrade(ftotal);
    }

    public Student(String name, int age, String dob, String standard, ArrayList<Subject> subjects) {
        this.rollno = uniqueid++;
        this.name = name;
        this.age = age;
        this.standard = standard;
        this.subjects = subjects;
        this.grade = calculateGrade(subjects);
    }

    public int getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}
