package org.javabase.apps.controller.student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempCourse;
import org.javabase.apps.dto.TempStudent;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.entity.Student;
import org.javabase.apps.service.BatchService;
import org.javabase.apps.service.CommonService;
import org.javabase.apps.service.CourseService;
import org.javabase.apps.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "dashboard/student")
public class AddStudentController {

    @Autowired
    CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST})
    public String studentPage() {
        return "student/addStudent";
    }

    @ResponseBody
    @RequestMapping(value = "loadallbatch", method = RequestMethod.GET)
    public Map<String, Object> getAllBatch() {
        Map<String, Object> response = new HashMap<>();
        List<Batch> subjectList = batchService.getAllBatch();
        response.put("success", true);
        response.put("data", subjectList);
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "loadallcourse", method = RequestMethod.GET)
    public Map<String, Object> getAllCourse() {
        Map<String, Object> response = new HashMap<>();
        List<Course> courseList = courseService.getAllCourse();
        response.put("success", true);
        response.put("data", courseList);
        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "enrollstudent", method = RequestMethod.POST)
    public Map<String, Object> enrollStudent(@RequestBody TempStudent tempStudent) {
        Map<String, Object> response = new HashMap<>();
        TempStudent ts=tempStudent;
        Boolean save = false;

        Batch batch=new Batch();
        batch = (Batch)commonService.getObjectById(batch, Integer.parseInt(ts.getBatchId()));
        
        Course course=new Course();
        course = (Course)commonService.getObjectById(course, Integer.parseInt(ts.getCourseId()));
        
        if (course != null  && batch != null) {
            Map<String,Object> map=new HashMap();
            map.put("course", course);
            map.put("batch", batch);
            Student student=studentService.convertTempObjectToMain(tempStudent, map);
            save=commonService.saveObject(student);
            
            // username VARCHAR ,
            // password VARCHAR ,
        }

        if (save) {
            response.put("success", true);
            response.put("message", "Student enrolled Sucessfully.");
            return response;
        } else {
            response.put("error", true);
            response.put("success", false);
            response.put("message", "Student enrollment Failed");
            return response;
        }
    }
    
}
