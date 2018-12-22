package org.javabase.apps.mapper;

import java.util.List;
import org.javabase.apps.entity.Student;

public interface StudentMapper {

    public List<Student> getAllStudents();

    public List<Student> getAllStudentsWithSearchCriteria(String searchType, String searchText);

    public double getTotalPaidFeeForStudent(int studentId, int courseId, int batchId, int semesterId);

   

}
