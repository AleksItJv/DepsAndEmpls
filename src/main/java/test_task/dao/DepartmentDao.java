package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Department;

import java.util.List;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {
    //TODO Get a list of department IDS where the number of employees doesn't exceed 3 people
    @Query(
            value = "select department.id from department\n" +
                    "join employee on department.id = employee.department_id\n" +
                    "group by department.id\n" +
                    "having count(*) < 3;",
            nativeQuery = true)
    List<Long> findAllWhereDepartmentDoesntExceedThreePeople();

    //TODO Get a list of departments IDs with the maximum total salary of employees
    @Query(
            value = "select department.id, avg(employee.salary) from department, employee\n" +
                    "where employee.department_id = department.id\n" +
                    "group by department.id\n" +
                    "order by department.id;",
            nativeQuery = true)
    List<Long> findAllByMaxTotalSalary();
}
