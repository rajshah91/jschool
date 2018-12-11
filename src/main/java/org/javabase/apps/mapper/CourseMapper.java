package org.javabase.apps.mapper;

import java.util.List;

import org.javabase.apps.entity.Course;

public interface CourseMapper {

	public List<Course> getAllCourse();
	public boolean addCourse(Course course);
}
