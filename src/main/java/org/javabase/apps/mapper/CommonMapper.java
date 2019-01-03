package org.javabase.apps.mapper;

public interface CommonMapper {

    public Object getObjectById(Object obj, int id);
    
    public Boolean saveObject(Object obj);
   
    public Boolean saveOrUpdateObject(Object obj);

    public Boolean deleteObject(Object obj);

}
