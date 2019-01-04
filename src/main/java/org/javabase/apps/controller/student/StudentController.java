package org.javabase.apps.controller.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempCourse;
import org.javabase.apps.dto.TempStudent;
import org.javabase.apps.dto.TempStudentFee;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentFee;
import org.javabase.apps.service.CommonService;
import org.javabase.apps.service.CourseService;
import org.javabase.apps.service.StudentService;
import org.javabase.apps.utility.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "dashboard/student")
public class StudentController {

    @Autowired
    CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String studentPage() {
        return "student/students";
    }

    @ResponseBody
    @RequestMapping(value = "loadallstudents", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> loadAllStudents(@RequestParam("searchType") String searchType, @RequestParam("searchText") String searchText) {
        List<Student> studentList = new ArrayList<>();

        Map<String, Object> response = new HashMap<>();
        if (MyUtils.isNullOrEmpty(searchText) && MyUtils.isNullOrEmpty(searchType)) {
            studentList = studentService.getAllStudents();
        } else {
            studentList = studentService.getAllStudentsWithSearchCriteria(searchType, searchText);
        }
        List<TempStudent> tempStudentList = studentService.convertMainObjectToTemp(studentList);
        response.put("success", true);
        response.put("data", tempStudentList);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "getremainingfeeforstudent", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> getRemainingFeeForStudent(@RequestBody TempStudentFee ts) {
        double totalCourseFee=0;
        double studentGivenDiscount=0;
        double totalPaidFee = 0;
        double totalFeeToBePaidForCourse=0;
        double remainingFeeToBePaid=0;
        Student student = new Student();
        Map<String, Object> response = new HashMap<>();
        if (!MyUtils.isNullOrEmpty(ts.getBatchId()) && !MyUtils.isNullOrEmpty(ts.getCourseId()) && !MyUtils.isNullOrEmpty(ts.getStudentId())) {
            totalPaidFee = studentService.getTotalPaidFeeForStudent(Integer.parseInt(ts.getStudentId()), Integer.parseInt(ts.getCourseId()), Integer.parseInt(ts.getBatchId()));
            
            CourseFee cf=courseService.getFeeForCourse(Integer.parseInt(ts.getCourseId()),Integer.parseInt(ts.getBatchId()));
            if(cf !=null){
                totalCourseFee=cf.getFeeAmount();
            }
            
            
            student = (Student) commonService.getObjectById(student, Integer.parseInt(ts.getStudentId()));
            if(student != null){
                studentGivenDiscount=student.getDiscount();
            }
            totalFeeToBePaidForCourse=totalCourseFee-studentGivenDiscount;
            remainingFeeToBePaid=totalFeeToBePaidForCourse-totalPaidFee;
       
        }
        TempStudentFee tempStudent = new TempStudentFee();
        tempStudent.setRemainingFee(remainingFeeToBePaid);
        tempStudent.setTotalFee(totalCourseFee);
        tempStudent.setDiscount(studentGivenDiscount);
        tempStudent.setAmountPaid(totalPaidFee);
        tempStudent.setStudentName(student.getFirstName()+" "+student.getLastName());
        tempStudent.setCourseName(student.getCourseId().getCourseName());
        tempStudent.setBatchName(student.getBatchId().getBatch());
        tempStudent.setEnrollmentNumber(student.getEnrollmentNumber());
        response.put("success", true);
        response.put("data", tempStudent);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "paystudentfee", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> payStudentFee(@RequestBody TempStudentFee ts) {
        Map<String, Object> response = new HashMap<>();
        Boolean save = false;
        
        Batch batch = new Batch();
        batch = (Batch) commonService.getObjectById(batch, Integer.parseInt(ts.getBatchId()));

        Course course = new Course();
        course = (Course) commonService.getObjectById(course, Integer.parseInt(ts.getCourseId()));
        
        Student student = new Student();
        student = (Student) commonService.getObjectById(student, Integer.parseInt(ts.getStudentId()));
        
        StudentFee sf=new StudentFee();
        sf.setAmountPaid(ts.getAmountPaid());
        sf.setBatchId(batch);
        sf.setCourseId(course);
        sf.setStudentId(student);
        sf.setChequeNumber(ts.getChequeNumber());
        sf.setPaymentMode(ts.getPaymentMode());

        save = commonService.saveObject(sf);

        if (save) {
            response.put("success", true);
            response.put("message", "Fee Payment Sucess");
            response.put("payment_id", sf.getId());
            return response;
        } else {
            response.put("success", false);
            response.put("error", true);
            response.put("message", "Fee Payment Failed");
            return response;
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "getfeeforcourse", method = { RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> getFeeForCourse(@RequestBody TempCourse tempCourse) {
        Map<String, Object> response = new HashMap<>();
        Boolean success = false;
        CourseFee courseFee=new CourseFee();
        Batch batch=new Batch();
        batch = (Batch)commonService.getObjectById(batch, Integer.parseInt(tempCourse.getBatchId()));
        
        Course course=new Course();
        course = (Course)commonService.getObjectById(course, Integer.parseInt(tempCourse.getCourseId()));
        
        if (course != null && batch != null) {
            courseFee = courseService.getFeeForCourse(course,batch);
            success=true;
        }
        
        response.put("success", success);
        response.put("data", courseFee);
        return response;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "getstudentfeehistory", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> loadStudentFeeHistory(@RequestParam("studentId") String studentId) {
        
        List<TempStudentFee> tempStudentFeeList = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        
        if (!MyUtils.isNullOrEmpty(studentId)) {
            Student student = new Student();
            student = (Student) commonService.getObjectById(student, Integer.parseInt(studentId));
            if(student != null){
                List<StudentFee> sfList=studentService.getStudentFeeHistory(student);
                if(sfList != null && sfList.size()>0){
                    for(StudentFee sf : sfList){
                        TempStudentFee tsf=new TempStudentFee();
                        tsf.setAmountPaid(sf.getAmountPaid());
                        tsf.setChequeNumber(sf.getChequeNumber());
                        tsf.setPaymentMode(sf.getPaymentMode());
                        tsf.setId(sf.getId());
                        tsf.setPaymentDate(String.valueOf(sf.getDataCreateTime()));
                        tsf.setStudentId(studentId);
                        tempStudentFeeList.add(tsf);
                    }
                }
            }
        }
        response.put("success", true);
        response.put("data", tempStudentFeeList);
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "deletefeerecord", method = {RequestMethod.POST, RequestMethod.GET})
    public Map<String, Object> deleteStudentFeeRecord(@RequestParam("feePaymentId") String feePaymentId) {
        Map<String, Object> response = new HashMap<>();
        boolean isSuccess=false;
        if (!MyUtils.isNullOrEmpty(feePaymentId)) {
            StudentFee studentFee = new StudentFee();
            studentFee = (StudentFee) commonService.getObjectById(studentFee, Integer.parseInt(feePaymentId));
            if(studentFee != null){
                isSuccess=commonService.deleteObject(studentFee);
            }
        }
        if (isSuccess) {
            response.put("success", true);
            response.put("message", "Fee Payment Undo Successful");
            return response;
        } else {
            response.put("success", false);
            response.put("error", true);
            response.put("message", "Fee Payment Undo Failed");
            return response;
        }
    }
}
