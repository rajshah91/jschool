package org.javabase.apps.controller.setup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javabase.apps.dto.TempCourse;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.entity.CourseSubject;
import org.javabase.apps.entity.Subject;
import org.javabase.apps.service.CommonService;
import org.javabase.apps.service.CourseService;
import org.javabase.apps.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "dashboard/courseSubject")
public class CourseSubjectController {

    @Autowired
    CourseService courseService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    CommonService commonService;

   
    @RequestMapping(method = RequestMethod.GET)
    public String getCourseSubjectPage() {
        return "institution/courseSubject";
    }
    
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> addCourseSubject(@RequestBody TempCourse tempCourse) {
        Map<String, Object> response = new HashMap<>();
        Boolean save = false;
        
        Batch batch=new Batch();
        batch = (Batch)commonService.getObjectById(batch, Integer.parseInt(tempCourse.getBatchId()));
        
        Semester sem=new Semester();
        sem = (Semester)commonService.getObjectById(sem, Integer.parseInt(tempCourse.getSemesterId()));
        
        Course course=new Course();
        course = (Course)commonService.getObjectById(course, Integer.parseInt(tempCourse.getCourseId()));
        
        String [] subjectIds=tempCourse.getSubjectIds();
        
        if (course != null && sem != null && batch != null) {
            for (String subId : subjectIds) {
                Subject sub = new Subject();
                sub = (Subject) commonService.getObjectById(sub, Integer.parseInt(subId));
                if (subId != null) {
                    CourseSubject cs = new CourseSubject();
                    cs.setBatchId(batch);
                    cs.setCourseId(course);
                    cs.setSemesterId(sem);
                    cs.setSubjectId(sub);
                    save = commonService.saveObject(cs);
                }
            }
        }

        if (save) {
            response.put("suceess", true);
            response.put("message", "Add Course Subject Sucess");
            return response;
        } else {
            response.put("error", true);
            response.put("message", "Add Course Subject Failed");
            return response;
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "loadcoursesubject", method = RequestMethod.GET)
    public Map<String, Object> getAllCourseSubject() {
        Map<String, Object> response = new HashMap<>();
        List<CourseSubject> courseSubjectList = courseService.getAllCourseSubject();
        List<TempCourse> tempcourseFeeList = new ArrayList<>();
        for(CourseSubject cs : courseSubjectList){
            TempCourse tc=new TempCourse();
            tc.setCourseName(cs.getCourseId().getCourseName());
            tc.setBatchName(cs.getBatchId().getBatch());
            tc.setSemester(cs.getSemesterId().getSemester());
            tc.setSubjectName(cs.getSubjectId().getSubName());
            tempcourseFeeList.add(tc);
        }
        response.put("success", true);
        response.put("data", tempcourseFeeList);
        return response;

    }
}
