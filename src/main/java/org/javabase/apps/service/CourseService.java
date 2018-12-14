package org.javabase.apps.service;

import java.util.List;

import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseSubjectMapping;
import org.javabase.apps.entity.TempCourse;

public interface CourseService {

    public List<Course> getAllCourse();
    
    public List<TempCourse> getAllCourseWithSubjectForView(List<Course> courses);

    public boolean addCourse(Course course);
    
    public boolean addCourseSubjectMapping(CourseSubjectMapping csm);
}
