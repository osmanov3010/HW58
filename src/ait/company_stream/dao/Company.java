package ait.company_stream.dao;

import ait.company_stream.model.Employee;

public interface Company {
    // объявляем методы, только сигнатуры, без тела метода
    boolean addEmployee(Employee employee);
    Employee removeEmployee(int id);
    Employee findEmployee(int id);
    int quantity();
    double totalSalary(); // - ФОТ, зарплата всех
    double avgSalary(); // средняя зарплата по компании, average - средний
    double totalSales(); // - объем продаж
    void printEmployees();

}
