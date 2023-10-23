package org.skypro.hw.service;

import org.junit.jupiter.api.Test;
import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.EmployeeNotFoundException;
import org.skypro.hw.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
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
        String expectedMessage = "400 Массив сотрудников переполнен";

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
    void find_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee();

        //Начало теста
        employeeService.add(firstName, lastName, salary, departmentId);
        Employee actualEmployee = employeeService.find(firstName, lastName, salary, departmentId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void find_withEmployeeNotFoundException() {
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
        String expectedMessage = "404 Такого сотрудника нет";

        //Начало теста
        employeeService.add(firstName, lastName, salary, departmentId);
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.find(firstName, lastName2, salary, departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void remove_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee();

        //Начало теста
        employeeService.add(firstName, lastName, salary, departmentId);
        Employee actualEmployee = employeeService.remove(firstName, lastName, salary, departmentId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void remove_withEmployeeNotFoundException() {
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
        String expectedMessage = "404 Сотрудник не удален - не был найден в базе";

        //Начало теста
        employeeService.add(firstName, lastName, salary, departmentId);
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.remove(firstName, lastName2, salary, departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getAll_Success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee();
        List<Employee> expectedEmployee1 = new ArrayList<>();
        expectedEmployee1.add(expectedEmployee);

        //Начало теста
        employeeService.add(firstName, lastName, salary, departmentId);
        List<Employee> actualEmployee = employeeService.getAll();
        assertEquals(expectedEmployee1, actualEmployee);
    }
}