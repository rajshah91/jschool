/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.javabase.apps.utility.MyUtils;

/**
 *
 * @author raj.shah
 */

public class TempCourse {
//   {"courseName":"WWWW","semester":"3","fees":"222","subjectId":["1", "2"]}
   
   private String courseName;
   private Integer semester;
   private Double fees;
   private String[] subjectId;
   private String commaSeparatedSubjectNames="";
   private String[] subjectNames;

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseName() {
        return courseName;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }
    
    public Double getFees() {
        return fees;
    }

    public void setSubjectId(String[] subjectId) {
        this.subjectId = subjectId;
    }

    public String[] getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectNames(String[] subjectNames) {
        this.subjectNames = subjectNames;
    }

    public String[] getSubjectNames() {
        return subjectNames;
    }

    public String getCommaSeparatedSubjectNames() {
        if(subjectNames != null && subjectNames.length >0 ){
            commaSeparatedSubjectNames.join(",", subjectNames);
        }
        return commaSeparatedSubjectNames;
    }
    
    
   
}
