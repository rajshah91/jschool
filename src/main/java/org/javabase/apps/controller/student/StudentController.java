package org.javabase.apps.controller.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempStudent;
import org.javabase.apps.dto.TempStudentFee;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Semester;
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
        double totalFee = ts.getTotalFee();
        double totalPaidFee = 0;
        Map<String, Object> response = new HashMap<>();
        if (!MyUtils.isNullOrEmpty(ts.getBatchId()) && !MyUtils.isNullOrEmpty(ts.getCourseId()) && !MyUtils.isNullOrEmpty(ts.getStudentId()) && !MyUtils.isNullOrEmpty(ts.getSemesterId())) {
            totalPaidFee = studentService.getTotalPaidFeeForStudent(Integer.parseInt(ts.getStudentId()), Integer.parseInt(ts.getCourseId()), Integer.parseInt(ts.getBatchId()), Integer.parseInt(ts.getSemesterId()));
        }
        TempStudentFee tempStudent = new TempStudentFee();
        tempStudent.setRemainingFee(totalFee - totalPaidFee);
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

        Semester sem = new Semester();
        sem = (Semester) commonService.getObjectById(sem, Integer.parseInt(ts.getSemesterId()));

        Course course = new Course();
        course = (Course) commonService.getObjectById(course, Integer.parseInt(ts.getCourseId()));
        
        Student student = new Student();
        student = (Student) commonService.getObjectById(student, Integer.parseInt(ts.getStudentId()));
        
        StudentFee sf=new StudentFee();
        sf.setAmountPaid(ts.getAmountPaid());
        sf.setDiscount(ts.getDiscount());
        sf.setBatchId(batch);
        sf.setCourseId(course);
        sf.setSemesterId(sem);
        sf.setStudentId(student);
        sf.setChequeNumber(ts.getChequeNumber());
        sf.setPaymentMode(ts.getPaymentMode());

        save = commonService.saveObject(sf);

        if (save) {
            response.put("success", true);
            response.put("message", "Fee Mayment Sucess");
            return response;
        } else {
            response.put("error", true);
            response.put("message", "Fee Payment Failed");
            return response;
        }
    }

}
