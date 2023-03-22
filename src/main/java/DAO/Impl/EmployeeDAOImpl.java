package DAO.Impl;

import DAO.EmployeeDAO;
import Model.City;
import Model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    final String user = "postgres";
    final String password = "Depech123";
    final String url = "jdbc:postgresql://localhost:5432/skypro";
    @Override
    public void creat(Employee employee) {
        // Формируем запрос к базе с помощью PreparedStatement
        try(final Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))")) {

            // Подставляем значение вместо wildcard
            // первым параметром указываем порядковый номер wildcard
            // вторым параметром передаем значение
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCityId());

            // С помощью метода executeQuery отправляем запрос к базе
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee employeeById(int id) {
        Employee employee = new Employee();
        // Формируем запрос к базе с помощью PreparedStatement
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id AND id=(?)")) {

            // Подставляем значение вместо wildcard
            statement.setInt(1, id);

            // Делаем запрос к базе и результат кладем в ResultSet
            ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while(resultSet.next()) {

                // С помощью методов getInt и getString получаем данные из resultSet
                // и присваиваем их полим объекта
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setCity(new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }


    @Override
    public List<Employee> readAll() {
        // Создаем список, в который будем укладывать объекты
        List<Employee> employeeArrayList = new ArrayList<>();

        try(final Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = Integer.parseInt(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                City city = new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name"));


                // Создаем объекты на основе полученных данных
                // и укладываем их в итоговый список
                employeeArrayList.add(new Employee(id, firstName, lastName, gender, age, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeArrayList;
    }

    @Override
    public void updateById(int id, Employee employee) {
        try(final Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE employee SET first_name=(?), last_name=(?), gender=(?), age=(?), city_id=(?) WHERE id=(?)")) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCityId());
            statement.setInt(6, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(int id) {
        try(final Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM employee WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
