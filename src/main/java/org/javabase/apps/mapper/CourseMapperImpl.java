package org.javabase.apps.mapper;

import java.util.List;

import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseSubjectMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class CourseMapperImpl implements CourseMapper{
	
	@Autowired
	private HibernateTemplate  hibernateTemplate;

	@Override
	public List<Course> getAllCourse() {
		String hql = "FROM Course";
		return (List<Course>) hibernateTemplate.find(hql);
	}

	@Override
	@Transactional
	public boolean addCourse(Course course) {
		try {
			hibernateTemplate.save(course);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

        @Override
        @Transactional
        public boolean addCourseSubjectMapping(CourseSubjectMapping csm) {
                try {
			hibernateTemplate.save(csm);
			return true;
		} catch (Exception e) {
			return false;
		}
        }

}
