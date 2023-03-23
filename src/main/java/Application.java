import DAO.CityDAO;
import DAO.EmployeeDAO;
import DAO.Impl.CityDAOImpl;
import DAO.Impl.EmployeeDAOImpl;
import model.City;
import model.Employee;


import java.sql.*;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CityDAO cityDAO = new CityDAOImpl();

        City city1 = new City("Саратов");

        Employee employee1 = new Employee("Стасов", "Николай", "М", 46, city1);


        // Создаем объект
        cityDAO.creat(city1);
        employeeDAO.creat(employee1);

        // Получаем объект по id
        System.out.println(cityDAO.cityById(3));
        System.out.println(employeeDAO.employeeById(2));

        // Получаем полный список объектов
        List<City> listCity = cityDAO.readAll();
        for (City city: listCity) {
            System.out.println(city);
        }
        List<Employee> listEmployee = employeeDAO.readAll();

        for (Employee employee : listEmployee) {
            System.out.println(employee);
        }

        // Изменяем объект
        cityDAO.updateById(city1);
        employeeDAO.updateById(employee1);

        // Удаляем объект
        cityDAO.deleteById(city1);
        employeeDAO.deleteById(employee1);

    }
}