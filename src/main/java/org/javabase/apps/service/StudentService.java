package org.javabase.apps.service;

import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempStudent;
import org.javabase.apps.dto.TempStudentAttendance;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentAttendance;
import org.javabase.apps.entity.StudentFee;


public interface StudentService {

    public List<Student> getAllStudents();
    
    public Student convertTempObjectToMain(TempStudent tempStudent,Map<String,Object> objMap);
    
    public List<TempStudentAttendance> convertStudentAttendanceObjectToTemp(List<StudentAttendance> studentattendanceList);
    
    public Student getStudentByEnrollmentNumber(String enrollmentNumber);
    
    public Student getStudentByMobileNumber(String mobileNumber);
    
    public List<TempStudent> convertMainObjectToTemp(List<Student> student);

    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText);

    public double getTotalFeeToBePaidForStudentForCourse(int studentId, int courseId, int batchId);
    
    public double getTotalPaidFeeForStudent(int studentId, int courseId, int batchId);
    
    public List<StudentFee> getStudentFeeHistory(Student student);
    
    public int deleteStudentAttendanceForGivenCriteria(int courseId, int batchId,int semesterId,String month);
    
    public List<StudentAttendance> getStudentAttendanceForGivenCriteria(int courseId, int batchId,int semesterId,String month);
    
    public List<StudentAttendance> getStudentAttendanceForGivenCriteria(int studentId);
}
