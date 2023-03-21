package Model;

import java.util.Objects;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private Model.City city;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String gender, int age, Model.City city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public Employee(int id, String firstName, String lastName, String gender, int age, Model.City city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model.City getCity() {
        return city;
    }

    public void setCity(Model.City city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", Фамилия ='" + firstName + '\'' +
                ", Имя ='" + lastName + '\'' +
                ", Пол ='" + gender + '\'' +
                ", Возраст =" + age +
                ", Город =" + city +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && gender.equals(employee.gender) && city.equals(employee.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, age, city);
    }
}
