/**
 *
 */
package org.javabase.apps.mapper;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.javabase.apps.entity.Student;
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
    public double getTotalPaidFeeForStudent(int studentId, int courseId, int batchId, int semesterId) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            String hql = "SELECT SUM(sf.amountPaid)+SUM(sf.discount) FROM StudentFee sf WHERE sf.studentId.id=" + studentId + " AND sf.courseId.id=" + courseId + " AND sf.batchId.id=" + batchId + " AND sf.semesterId.id=" + semesterId;
            hql += " GROUP BY sf.studentId.id,sf.batchId.id,sf.courseId.id,sf.semesterId.id ";
           
            Query query = session.createQuery(hql);
            List listResult = query.list();
            double paidfee = listResult != null && listResult.size() > 0 ? (double) listResult.get(0) : 0;
            return paidfee;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            session.close();
            return 0;
        }
    }
}
