/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagement;

import java.io.Serializable;


class Subject implements Serializable{
    private int mark;
    private String subjectname;
    private String subjectid;
    private String grade;
    public static String calculateGrade(int mark){
        if(mark>=90)
            return "A+";
        else if(mark>=80 && mark<90)
            return "A";
        else if(mark>=70 && mark<80)
            return "B";
        else if(mark>=60 && mark<70)
            return "C";
        else if(mark>=50 && mark<60)
            return "D";
        return "F";
    }
    public Subject(int mark,String subjectname,String subjecid){
        this.mark=mark;
        this.subjectname=subjectname;
        this.subjectid=subjectid;
        this.grade=calculateGrade(mark);
    }

    public int getMark() {
        return mark;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public String getGrade() {
        return grade;
    }

    public void setMark(int mark) {
        this.mark = mark;
        this.grade=calculateGrade(mark);
    }
}
