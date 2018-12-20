package org.javabase.apps.mapper;

import java.util.List;
import org.javabase.apps.entity.Batch;

import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.CourseSubject;
import org.javabase.apps.entity.Semester;

public interface CourseMapper {

    public List<Course> getAllCourse();
    
    public List<Course> findCourseByName(String searchCourseName);

    public boolean addCourse(Course course);
    
//    public boolean addCourseSubjectMapping(CourseSubjectMapping csm);
    
    public Course getCourseByName(String courseName);

    public List<CourseFee> getAllCourseFee();
    
    public List<CourseSubject> getAllCourseSubject();

    public CourseFee getFeeForCourse(Course course, Batch batch, Semester sem);
}
