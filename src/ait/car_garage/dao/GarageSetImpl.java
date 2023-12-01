package ait.car_garage.dao;

import ait.car_garage.model.Car;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GarageSetImpl implements Garage {

    // поля класса
    private Set<Car> cars; // для объектов типа Car
    private int size; // текущее кол-во автомобилей в массиве
    private int capacity; // максимальное кол-во автомобилей

    // конструктор
    public GarageSetImpl(int capacity) {
        cars = new HashSet<>();
        this.size = 0;
        this.capacity = capacity;
    }

    @Override
    public boolean addCar(Car car) {
        if (car == null) {
            throw new RuntimeException("null");
        }
        if (capacity == cars.size()) {
            return false;
        }
        return cars.add(car);
    }

    @Override
    public Car removeCar(String regNumber) {
        Car car = findCarByRegNumber(regNumber);
        cars.remove(car);
        return car;
    }

    @Override
    public Car findCarByRegNumber(String regNumber) {
        return cars.stream()
                .filter(car -> car.getRegNumber().equals(regNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Car[] findCarsByModel(String model) {
        return findCarsByPredicate(car -> car.getModel().equals(model));
    }

    @Override
    public Car[] findCarsByCompany(String company) {
        return findCarsByPredicate(car -> car.getCompany().equals(company));
    }

    @Override
    public Car[] findCarsByColor(String color) {
        return findCarsByPredicate(car -> car.getColor().equals(color));
    }

    @Override
    public Car[] findCarsByEngine(double min, double max) {
        return findCarsByPredicate(car -> car.getEngine() >= min && car.getEngine() < max);
    }

    @Override
    public int size() {
        return cars.size();
    }

    private Car[] findCarsByPredicate(Predicate<Car> predicate) {
        return cars.stream()
                .filter(predicate)
                .toArray(Car[]::new);
    }
}
