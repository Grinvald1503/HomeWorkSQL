package DAO.Impl;

import DAO.EmployeeDAO;
import hibernate.HibernateSessionFactoryUtil;
import model.City;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void creat(Employee employee) {
        // Формируем запрос к базе с помощью PreparedStatement
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            // Создаем транзакцию и начинаем ее
            Transaction transaction = session.beginTransaction();
            // вызываем на объекте сессии метод save
            // данный метод внутри себя содержит необходимый запрос к базе
            // для создания новой строки
            session.save(employee);
            // Выполняем коммит, то есть сохраняем изменения,
            // которые совершили в рамках транзакции
            transaction.commit();
        }
    }

    @Override
    public Employee employeeById(int id) {

        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
    }


    @Override
    public List<Employee> readAll() {
        List<Employee> users = (List<Employee>)  HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From Employee").list();
        return users;
    }

    @Override
    public void updateById(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            // Для обновления данных нужно передать в конструктор
            // объект с актуальными данными
            session.update(employee);
            transaction.commit();
        }

    }

    @Override
    public void deleteById(Employee employee) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Для удаления объекта из таблицы нужно передать его в метод delete
            session.delete(employee);
            transaction.commit();
        }

    }
}
