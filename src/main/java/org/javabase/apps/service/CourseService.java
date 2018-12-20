package org.javabase.apps.service;

import java.util.List;

import org.javabase.apps.entity.Course;
import org.javabase.apps.dto.TempCourse;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.CourseSubject;
import org.javabase.apps.entity.Semester;

public interface CourseService {

    public List<Course> getAllCourse();
    
    public List<TempCourse> getAllCourseWithSubjectForView(List<Course> courses);

    public boolean addCourse(Course course);
    
    public String findCourseByName(String courseSearchStr);
    
    public Course getCourseByName(String courseName);

    public List<CourseFee> getAllCourseFee();

    public List<CourseSubject> getAllCourseSubject();

    public CourseFee getFeeForCourse(Course course, Batch batch, Semester sem);
}
