package org.javabase.apps.mapper;

import java.util.List;
import javax.persistence.NamedQuery;
import org.javabase.apps.entity.Batch;

import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.CourseFee;
import org.javabase.apps.entity.CourseSubject;
import org.javabase.apps.entity.Semester;
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
            try {
		String hql = "FROM Course";
                    return (List<Course>) hibernateTemplate.find(hql);
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
		}
	}

	@Override
	@Transactional
	public boolean addCourse(Course course) {
		try {
			hibernateTemplate.save(course);
			return true;
		} catch (Exception e) {
                        System.out.println(e.getMessage());
			return false;
		}
	}

        @Override
        public List<Course> findCourseByName(String searchCourseName) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Query query = session.createQuery("FROM Student WHERE studentName like concat('%',:studentName,'%')");
//        query.setParameter("studentName", likeStudentName);
//        List&lt;Student&gt; list = query.list();
//        if(list.size()==0)
//            return null;
//        return list;
            try {
                String hql = "FROM Course c WHERE c.courseName like '%" + searchCourseName + "%'";
                return (List<Course>) hibernateTemplate.find(hql);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
//            String hql2 = "FROM Course c WHERE c.courseName like :courseName ";
//            return (List<Course>) hibernateTemplate.findByNamedParam(hql2, "courseName", '%' + searchCourseName + '%');
        }

        @Override
        public Course getCourseByName(String courseName) {
            try {
                    String hql = "FROM Course c WHERE c.courseName = '"+courseName+"'";
                    return (Course) hibernateTemplate.find(hql).get(0);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }
        }

        @Override
        public List<CourseFee> getAllCourseFee() {
            try {
                String hql = "FROM CourseFee";
                return (List<CourseFee>) hibernateTemplate.find(hql);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        
        @Override
        public List<CourseSubject> getAllCourseSubject() {
            try {
                String hql = "FROM CourseSubject";
                return (List<CourseSubject>) hibernateTemplate.find(hql);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        @Override
        public CourseFee getFeeForCourse(Course course, Batch batch, Semester sem) {
            try {
                String hql = "FROM Course c WHERE c.courseId = " + course + " AND c.batchId= "+ batch +" AND c.semesterId= "+sem;
                return (CourseFee) hibernateTemplate.find(hql).get(0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

}
