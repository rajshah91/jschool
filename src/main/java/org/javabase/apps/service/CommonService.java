package org.javabase.apps.service;

/**
 * @author raj.shah rajshah131291@gmail.com>
 * @version	1.0.0
 * @since	1.0.0
 */
public interface CommonService {

    public Object getObjectById(Object obj, int id);

    public Boolean saveObject(Object obj);
}
