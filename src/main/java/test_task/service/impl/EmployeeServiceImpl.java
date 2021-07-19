package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.EmployeeDao;
import test_task.model.Employee;
import test_task.service.EmployeeService;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public List<Employee> findAllBySalaryGreaterThatBoss() {
        return employeeDao.findAllWhereSalaryGreaterThatBoss();
    }

    @Override
    public List<Employee> findAllByMaxSalary() {
        return employeeDao.findAllByMaxSalary();
    }

    @Override
    public List<Employee> findAllWithoutBoss() {
        return employeeDao.findAllWithoutBoss();
    }

    @Override
    public Long fireEmployee(String name) {
        Iterable<Employee> employees = employeeDao.findAll();
        //TODO Implement method using Collection
        // ---write your code here

        Iterator<Employee> it = employees.iterator();
        long id = 0;
        Employee empl = null;
        while (it.hasNext()) {
            if (((empl = it.next()).getName().equals(name))) {
                id = empl.getId();
                it.remove();
                //System.out.println("--- Deleted : " + empl);
                break;
            }
        }
        if (empl != null) {
            employeeDao.delete(empl);
        }

        employeeDao.saveAll(employees);
        return id;
    }

    @Override
    public Long changeSalary(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        //TODO Implement method using Collection
        // ---write your code here

        Iterator<Employee> it = employees.iterator();
        long id = 0;
        Employee empl;
        while (it.hasNext()) {
            if (((empl = it.next()).getName().equals(name))) {
                id = empl.getId();
                empl.setSalary(empl.getSalary().multiply(BigDecimal.valueOf(1.25)));
                //System.out.println("--- Update : " + empl);
                break;
            }
        }

        employeeDao.saveAll(employees);
        return id;
    }

    @Override
    public Long hireEmployee(Employee employee) {
        //TODO Implement method using Collection and DAO
        // ---write your code here

        employeeDao.save(employee);

        return employee.getId();
    }
}
