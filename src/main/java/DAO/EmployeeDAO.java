package DAO;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {
    //Создание(добавление) сущности Employee в таблицу
    void creat(Employee employee);

    //Получение конкретного объекта Employee по id
    Employee employeeById(int id);

    // Получение списка всех объектов Employee из базы
    List<Employee> readAll();
// Изменение конкретного объекта Employee в базе по id

    void updateById(Employee employee);

    // Удаление конкретного объекта Employee из базы по id
    void deleteById(Employee employee);
}