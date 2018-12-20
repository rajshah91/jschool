package org.javabase.apps.controller.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempCourse;
import org.javabase.apps.dto.TempSearch;
import org.javabase.apps.dto.TempStudent;
import org.javabase.apps.entity.Student;
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
    public Map<String, Object> loadAllSubjects( @RequestParam("searchType") String searchType,@RequestParam("searchText") String searchText) {
        List<Student> studentList = new ArrayList<>();

        Map<String, Object> response = new HashMap<>();
        if (MyUtils.isNullOrEmpty(searchText) && MyUtils.isNullOrEmpty(searchType)) {
            studentList = studentService.getAllStudents();
        }else{
            studentList = studentService.getAllStudentsWithSearchCriteria(searchType,searchText);
        }

        List<TempStudent> tempStudentList = studentService.convertMainObjectToTemp(studentList);
        response.put("success", true);
        response.put("data", tempStudentList);
        return response;
    }

}
