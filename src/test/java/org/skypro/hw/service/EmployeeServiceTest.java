package org.skypro.hw.service;

import org.junit.jupiter.api.Test;
import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.EmployeeStorageIsFullException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.skypro.hw.service.utils.EmployeeGenerator.*;

class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void add_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = employeeService.add(firstName, lastName, salary, departmentId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void add_withEmployeeStorageIsFullException() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;

        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        double salary3 = SALARY_3;

        //Подготовка ожидаемого результата
        String expectedMessage = "Массив сотрудников переполнен";

        //Начало теста
        employeeService.add(firstName2, lastName2, salary2, departmentId);
        employeeService.add(firstName3, lastName3, salary3, departmentId);
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.add(firstName, lastName, salary, departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void find() {
        //Подготовка входных данных

        //Подготовка ожидаемого результата

        //Начало теста

    }

    @Test
    void remove() {
    }

    @Test
    void getAll() {
    }
}