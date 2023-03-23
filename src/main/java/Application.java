import DAO.EmployeeDAO;
import DAO.Impl.EmployeeDAOImpl;
import model.City;
import model.Employee;


import java.sql.*;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        Employee employee1 = new Employee("Стасов", "Николай", "М", 46, 2);
        // Создаем объект
        employeeDAO.creat(employee1);

        // Получаем объект по id
        System.out.println(employeeDAO.employeeById(2));

        // Получаем полный список объектов
        List<Employee> list = employeeDAO.readAll();

        for (Employee employee : list) {
            System.out.println(employee);
        }

        // Изменяем объект
        employeeDAO.updateById(employee1);

        // Удаляем объект
        employeeDAO.deleteById(employee1);

    }
}