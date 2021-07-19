package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Employee;

import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    //TODO Get a list of employees receiving a salary greater than that of the boss
    @Query(
            value = "select e.* from employee e\n" +
                    "join employee b on e.boss_id = b.id\n" +
                    "where e.salary > b.salary;",
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(
            value = "select * from employee where (employee.department_id, employee.salary) in " +
                    "(select employee.department_id, max(employee.salary) from employee " +
                    "GROUP BY employee.department_id);",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "SELECT * FROM employee where boss_id is null;",
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
