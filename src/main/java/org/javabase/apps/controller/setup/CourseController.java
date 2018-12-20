package org.javabase.apps.controller.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.javabase.apps.entity.Course;
import org.javabase.apps.dto.TempCourse;
import org.javabase.apps.service.CourseService;
import org.javabase.apps.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"dashboard/course"})
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    private SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAddCoursePage() {
        return "institution/course";
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
    
//    @ResponseBody
//    @RequestMapping(value = "loadcoursewithsubject", method = RequestMethod.GET)
//    public Map<String, Object> getAllCourseWithSubject() {
//        Map<String, Object> response = new HashMap<>();
//        List<Course> courseList = courseService.getAllCourse();
//        List<TempCourse> tempCourseList= courseService.getAllCourseWithSubjectForView(courseList);
//        response.put("success", true);
//        response.put("data", tempCourseList);
//        return response;
//
//    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> addCourse(@RequestBody TempCourse tempCourse) {
        Map<String, Object> response = new HashMap<>();
        Boolean save = false;
        Course course = new Course();
        course.setCourseName(tempCourse.getCourseName());
        course.setTotalSemester(tempCourse.getTotalSemester());
        save = courseService.addCourse(course);

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
    @RequestMapping(value = "searchcoursebyname", method = RequestMethod.GET)
    public Map<String, Object> searchCourseByName(HttpServletRequest request) {
        String searchCourseName=request.getParameter("searchCourseName");
        Map<String, Object> response = new HashMap<>();
        String courseList = courseService.findCourseByName(searchCourseName);
        response.put("success", true);
        response.put("data", courseList);
        return response;
    }

}
