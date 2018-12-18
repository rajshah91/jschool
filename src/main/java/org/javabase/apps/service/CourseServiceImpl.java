package org.javabase.apps.service;

import java.util.ArrayList;
import java.util.List;

import org.javabase.apps.entity.Course;
import org.javabase.apps.dto.TempCourse;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.CourseSubject;
import org.javabase.apps.mapper.CourseMapper;
import org.javabase.apps.utility.MyUtils;
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
        
//	@Override
//	public boolean addCourseSubjectMapping(CourseSubjectMapping csm){
//		return courseMapper.addCourseSubjectMapping(csm);
//	}

        @Override
        public List<TempCourse> getAllCourseWithSubjectForView(List<Course> courses) {
                List<TempCourse> courseTempList= new ArrayList<>();
                for(Course c : courses){
                    TempCourse tc=new TempCourse();
                    tc.setCourseName(c.getCourseName());
//                    tc.setSemester(c.getSemester());
//                    tc.setFees(c.getFees());
                    
//                    Collection<CourseSubjectMapping> csm=c.getCourseSubjectMappingCollection();
                    List<String> subs=new ArrayList<>();
//                    for(CourseSubjectMapping csmapping : csm){
//                        String subName=csmapping.getSubjectId().getSubName();
//                        subs.add(subName);
//                    }
                    tc.setSubjectNames(subs.toArray(new String[subs.size()]));
                    tc.setCommaSeparatedSubjectNames();
                    courseTempList.add(tc);
                }
                return courseTempList;
        }

        @Override
        public String findCourseByName(String courseSearchStr) {
            List <Course> courseList=courseMapper.findCourseByName(courseSearchStr);
            List <String> strList=new ArrayList<>();
            if(courseList != null && courseList.size()>0){
                for(Course c : courseList){
                    strList.add(c.getCourseName());
                }
            }
            return MyUtils.getCommaSeparatedStringFromList(strList);
        }

        @Override
        public Course getCourseByName(String courseName) {
            return courseMapper.getCourseByName(courseName);
        }

        @Override
        public List<CourseFee> getAllCourseFee() {
            return courseMapper.getAllCourseFee();
        }
        
        @Override
        public List<CourseSubject> getAllCourseSubject() {
            return courseMapper.getAllCourseSubject();
        }

}
