import DAO.Impl.EmployeeDAOImpl;
import Model.City;
import Model.Employee;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {
        task2();
    }
    public static void task1 () throws SQLException {
        // Создаем переменные с данными для подключения к базе
        final String user = "postgres";
        final String password = "";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        // Создаем соединение с базой с помощью Connection
        // Формируем запрос к базе с помощью PreparedStatement
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {

            // Подставляем значение вместо wildcard
            statement.setInt(1, 2);

            // Делаем запрос к базе и результат кладем в ResultSet
            final ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while (resultSet.next()) {

                // С помощью методов getInt и getString получаем данные из resultSet
                String firstName = "Фамилия : " + resultSet.getString("first_name");
                String lastName = "Имя : " + resultSet.getString("last_name");
                String gender = "Пол : " + resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String city = "city_id: " + resultSet.getString("city_id");

                // Выводим данные в консоль
                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println("Возраст : " + age);
                System.out.println(city);

            }
        }
    }
    public static void task2() {
        City city = new City(1,"Chabarovsk");
        Employee employee = new Employee("Стасов", "Николай", "М", 46, city);

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        employeeDAO.creat(employee);
        System.out.println(employeeDAO.employeeById(2));
        System.out.println(employeeDAO.readAll());
        employeeDAO.updateById(2, employee);
        employeeDAO.deleteById(10);
        System.out.println(employeeDAO.employeeById(2));

    }
}