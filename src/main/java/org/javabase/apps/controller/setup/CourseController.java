package org.javabase.apps.controller.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseSubjectMapping;
import org.javabase.apps.entity.Subject;
import org.javabase.apps.entity.TempCourse;
import org.javabase.apps.service.CourseService;
import org.javabase.apps.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "dashboard/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET)
    public String roomPage() {
        return "institution/course";
    }

    @ResponseBody
    @RequestMapping(value = "load", method = RequestMethod.GET)
    public Map<String, Object> getAllCourse() {
        Map<String, Object> response = new HashMap<>();
        List<Course> courseList = courseService.getAllCourse();
        List<TempCourse> tempCourseList= courseService.getAllCourseWithSubjectForView(courseList);
        response.put("success", true);
        response.put("data", tempCourseList);
        return response;

    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> addCourse(@RequestBody TempCourse tempCourse) {
        
        Map<String, Object> response = new HashMap<>();
        Course course=new Course();
        course.setCourseName(tempCourse.getCourseName());
        course.setFees(tempCourse.getFees());
        course.setSemester(tempCourse.getSemester());
        Boolean save = courseService.addCourse(course);
        
        String[] subArr = tempCourse.getSubjectId();
        if (subArr != null && subArr.length > 0) {
            for (String subjId : subArr) {
                Subject subject = subjectService.getSubjectById(Integer.valueOf(subjId));
                CourseSubjectMapping csm = new CourseSubjectMapping();
                csm.setSubjectId(subject);
                csm.setCourseId(course);
                save = courseService.addCourseSubjectMapping(csm);
            }
        }
        
        if (save) {
            response.put("suceess", true);
            response.put("message", "Add Course Sucess");
            return response;
        } else {
            response.put("error", true);
            response.put("message", "Add Course Failed");
            return response;
        }

    }

    @ResponseBody
    @RequestMapping(value = "subjectload", method = RequestMethod.POST)
    public Map<String, Object> loadSubjects() {
        Map<String, Object> response = new HashMap<>();
        List<Subject> subList = subjectService.getAllSubjects();
        response.put("success", true);
        response.put("data", subList);
        return response;
    }

}
