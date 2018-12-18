/**
 *
 */
package org.javabase.apps.mapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class CommonMapperImpl implements CommonMapper {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    private static final Logger log = LoggerFactory.getLogger(CommonMapperImpl.class);

    @Override
    @Transactional(readOnly = true)
    public Object getObjectById(Object obj, int id) {
        try {
            return hibernateTemplate.get(obj.getClass(), id);
        } catch (Exception e) {
            System.out.println("Error in CommonMapperImpl.getObjectById : "+e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    @Transactional
    public Boolean saveObject(Object obj) {
        try {
            hibernateTemplate.save(obj);
            return true;
        } catch (Exception e) {
            System.out.println("Error in CommonMapperImpl.saveObject : "+e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
    }

}
