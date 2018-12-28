/**
 *
 */
package org.javabase.apps.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempStudent;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentFee;
import org.javabase.apps.mapper.CourseMapper;
import org.javabase.apps.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }

    @Override
    public Student convertTempObjectToMain(TempStudent tempStudent, Map<String, Object> objMap) {
            Batch batch= objMap.containsKey("batch") && objMap.get("batch") != null ? (Batch) objMap.get("batch") : null ;
            Course course= objMap.containsKey("course") && objMap.get("course") != null ? (Course) objMap.get("course") : null ;
            
            Student student = new Student();
            student.setEnrollmentNumber(tempStudent.getEnrollmentNumber());
            student.setCourseId(course);
            student.setBatchId(batch);
            student.setFirstName(tempStudent.getFirstName());
            student.setMiddleName(tempStudent.getMiddleName());
            student.setLastName(tempStudent.getLastName());
            student.setGender(tempStudent.getGender());
            student.setBirthDate(Date.valueOf(tempStudent.getBirthDate()));
            student.setEnrollmentDate(Date.valueOf(tempStudent.getEnrollmentDate()));
            student.setAddressLine1(tempStudent.getAddressLine1());
            student.setCity(tempStudent.getCity());
            student.setState(tempStudent.getState());
            student.setCountry(tempStudent.getCountry());
            student.setPincode(tempStudent.getPincode());
            student.setMobileNumber(tempStudent.getMobileNumber());
            student.setEmailId(tempStudent.getEmailId());
            student.setGuardianFullName(tempStudent.getGuardianFullName());
            student.setGuardianFullAddress(tempStudent.getGuardianFullAddress());
            student.setGuardianMobileNumber(tempStudent.getGuardianMobileNumber());
            student.setBloodGroup(tempStudent.getBloodGroup());
            student.setDisability(tempStudent.getDisability());
            student.setDisabilityDetail(tempStudent.getDisabilityDetail());
            student.setDiscount(tempStudent.getDiscount());
            student.setQualification(tempStudent.getQualification());
            
            return student;
        }

        @Override
        public List<TempStudent> convertMainObjectToTemp(List<Student> studentList) {
            List<TempStudent> tempStudentList=new ArrayList<>();
            if(studentList != null && studentList.size()>0){
                for(Student student : studentList){
                    TempStudent ts=new TempStudent();
                    ts.setId(student.getId());
                    ts.setEnrollmentNumber(student.getEnrollmentNumber());
                    ts.setCourseName(student.getCourseId() != null ? student.getCourseId().getCourseName() : "");
                    ts.setCourseId(student.getCourseId() != null ? String.valueOf(student.getCourseId().getId()) : "");
                    ts.setBatchName(student.getBatchId() != null ? student.getBatchId().getBatch() : "");
                    ts.setBatchId(student.getBatchId() != null ? String.valueOf(student.getBatchId().getId()) : "");
                    ts.setFirstName(student.getFirstName());
                    ts.setMiddleName(student.getMiddleName());
                    ts.setLastName(student.getLastName());
                    ts.setGender(student.getGender());
                    ts.setBirthDate(String.valueOf(student.getBirthDate()));
                    ts.setEnrollmentDate(String.valueOf(student.getEnrollmentDate()));
                    ts.setAddressLine1(student.getAddressLine1());
                    ts.setCity(student.getCity());
                    ts.setState(student.getState());
                    ts.setCountry(student.getCountry());
                    ts.setPincode(student.getPincode());
                    ts.setMobileNumber(student.getMobileNumber());
                    ts.setEmailId(student.getEmailId());
                    ts.setGuardianFullName(student.getGuardianFullName());
                    ts.setGuardianFullAddress(student.getGuardianFullAddress());
                    ts.setGuardianMobileNumber(student.getGuardianMobileNumber());
                    ts.setBloodGroup(student.getBloodGroup());
                    ts.setDisability(student.getDisability());
                    ts.setDisabilityDetail(student.getDisabilityDetail());
                    ts.setDiscount(student.getDiscount());
                    ts.setQualification(student.getQualification());
                    tempStudentList.add(ts);
                }
            }
            return tempStudentList;
        }

    @Override
    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText) {
        return studentMapper.getAllStudentsWithSearchCriteria(searchType,searchText);
    }

    @Override
    public double getTotalFeeToBePaidForStudentForCourse(int studentId, int courseId, int batchId) {
        return studentMapper.getTotalFeeToBePaidForStudentForCourse(studentId,courseId,batchId);
    }
    
    @Override
    public double getTotalPaidFeeForStudent(int studentId, int courseId, int batchId) {
        return studentMapper.getTotalPaidFeeForStudent(studentId,courseId,batchId);
    }

    @Override
    public List<StudentFee> getStudentFeeHistory(Student student) {
        return studentMapper.getStudentFeeHistory(student);
    }

}
