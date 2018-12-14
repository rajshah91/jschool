package org.javabase.apps.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseSubjectMapping;
import org.javabase.apps.entity.TempCourse;
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
        
	@Override
	public boolean addCourseSubjectMapping(CourseSubjectMapping csm){
		return courseMapper.addCourseSubjectMapping(csm);
	}

        @Override
        public List<TempCourse> getAllCourseWithSubjectForView(List<Course> courses) {
                List<TempCourse> courseTempList= new ArrayList<>();
                for(Course c : courses){
                    Collection<CourseSubjectMapping> csm=c.getCourseSubjectMappingCollection();
                    for(CourseSubjectMapping csmapping : csm){
                        Integer mappingId=csmapping.getId();
                    }
                }
                return courseTempList;
        }

}
