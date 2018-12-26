package org.javabase.apps.service;

import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempStudent;
import org.javabase.apps.entity.Student;


public interface StudentService {

    public List<Student> getAllStudents();
    
    public Student convertTempObjectToMain(TempStudent tempStudent,Map<String,Object> objMap);
    
    public List<TempStudent> convertMainObjectToTemp(List<Student> student);

    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText);

    public double getTotalFeeToBePaidForStudentForCourse(int studentId, int courseId, int batchId);
    
    public double getTotalPaidFeeForStudent(int studentId, int courseId, int batchId);

}
