/**
 *
 */
package org.javabase.apps.mapper;

import java.util.List;
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
}
