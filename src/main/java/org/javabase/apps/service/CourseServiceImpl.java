package org.javabase.apps.service;

import java.util.List;

import org.javabase.apps.entity.Course;
import org.javabase.apps.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseMapper courseMapper;

	@Override
	public List<Course> getAllCourse() {
		return courseMapper.getAllCourse();
	}

	@Override
	public boolean addCourse(Course course) {
		return courseMapper.addCourse(course);
	}

}
