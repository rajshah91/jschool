/**
 *
 */
package org.javabase.apps.mapper;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class StudentMapperImpl implements StudentMapper {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        try {
            String hql = "FROM Student";
            return (List<Student>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText) {
        try {
            String hql = "FROM Student s WHERE s." + searchType + " like '%" + searchText + "%'";
            return (List<Student>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public double getTotalPaidFeeForStudent(int studentId, int courseId, int batchId) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            String hql = "SELECT SUM(sf.amountPaid) FROM StudentFee sf WHERE sf.studentId.id=" + studentId + " AND sf.courseId.id=" + courseId + " AND sf.batchId.id=" + batchId ;
            hql += " GROUP BY sf.studentId.id,sf.batchId.id,sf.courseId.id ";
           
            Query query = session.createQuery(hql);
            List listResult = query.list();
            double paidfee = listResult != null && listResult.size() > 0 ? (double) listResult.get(0) : 0;
            session.close();
            return paidfee;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            session.close();
            return 0;
        }
    }

    @Override
    public double getTotalFeeToBePaidForStudentForCourse(int studentId, int courseId, int batchId) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            String hql = "SELECT cf.feeAmount-s.discount FROM CourseFee cf JOIN Student s ON s.courseId=cf.courseId "
                    + " WHERE s.id=" + studentId + " AND cf.courseId.id=" + courseId + " AND cf.batchId.id=" + batchId ;
           
//            String sql="SELECT cf.fee_amount-s.discount from course_fee cf INNER JOIN student s ON s.course_id=cf.course_id "
//                    + " WHERE s.id='' AND cf.course_id ='' AND cf.batch_id=''";
            Query query = session.createQuery(hql);
            List listResult = query.list();
            double paidfee = listResult != null && listResult.size() > 0 ? (double) listResult.get(0) : 0;
            session.close();
            return paidfee;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            session.close();
            return 0;
        }
    }

    @Override
    public List<StudentFee> getStudentFeeHistory(Student student) {
        try {
            String hql = "FROM StudentFee sf WHERE sf.studentId.id=" + student.getId();
            return (List<StudentFee>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
