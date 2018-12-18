/**
 *
 */
package org.javabase.apps.mapper;

import java.util.List;
import org.javabase.apps.entity.Batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class BatchMapperImpl implements BatchMapper {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    private static final Logger log = LoggerFactory.getLogger(BatchMapperImpl.class);

    @Override
    @Transactional(readOnly = true)
    public Batch getBatchById(int batchId) {
        return hibernateTemplate.get(Batch.class, batchId);
    }

    /**
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Batch> getAllBatch() {
        String hql = "FROM Batch";
        return (List<Batch>) hibernateTemplate.find(hql);
    }

    @Override
    @Transactional
    public Boolean addBatch(Batch batch) {
        try {
            hibernateTemplate.save(batch);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

}
