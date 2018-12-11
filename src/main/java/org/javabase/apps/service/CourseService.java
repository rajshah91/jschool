package org.javabase.apps.service;

import java.util.List;

import org.javabase.apps.entity.Course;

public interface CourseService {

	public List<Course> getAllCourse();
	public boolean addCourse(Course course);
}
