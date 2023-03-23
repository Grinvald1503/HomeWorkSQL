package DAO;

import model.City;
import model.Employee;

import java.util.List;

public interface CityDAO {
    void creat(City city);
    City cityById(int id);

    // Получение списка всех объектов Employee из базы
    List<City> readAll();
// Изменение конкретного объекта Employee в базе по id

    void updateById(City city);

    // Удаление конкретного объекта Employee из базы по id
    void deleteById(City city);
}
