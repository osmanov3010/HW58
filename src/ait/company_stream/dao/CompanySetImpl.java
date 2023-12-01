package ait.company_stream.dao;

import ait.company_stream.model.Employee;
import ait.company_stream.model.SalesManager;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class CompanySetImpl implements Company {
    private Set<Employee> employees;
    private int capacity;

    public CompanySetImpl(int capacity) {
        this.capacity = capacity;
        employees = new HashSet<>();
    }

    // O(1)
    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("null");
        }
        if (capacity == employees.size()) {
            return false;
        }
        return employees.add(employee);
    }


    @Override
    public Employee removeEmployee(int id) {
        Employee employee = findEmployee(id);
        if (employee != null) {
            employees.remove(employee);
            return employee;
        } else {
            return null;
        }
    }

    // O(n)
    @Override
    public Employee findEmployee(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // O(n)
    @Override
    public double totalSalary() {
        return employees.stream()
                .mapToDouble(e -> e.calcSalary())
                .sum();
    }

    @Override
    public double avgSalary() {
        return totalSalary() / employees.size();
    }

    // O(1)
    @Override
    public int quantity() {
        return employees.size();
    }

    // O(n)
    @Override
    public double totalSales() {
        return employees.stream()
                .filter(employee -> employee instanceof SalesManager)
                .mapToDouble(employee -> ((SalesManager) employee).getSalesValue())
                .sum();
    }

    // O(n)
    @Override
    public void printEmployees() {
        employees.forEach(e -> System.out.println(e));
    }

    // O(n)
    private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
        return employees.stream()
                .filter(predicate)
                .toArray(Employee[]::new);
    }
}
