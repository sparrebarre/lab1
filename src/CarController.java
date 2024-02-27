package src;

import java.awt.*;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    private final static int RANGE = 5;

    // The frame that represents this instance View of the MVC pattern
    // A list of cars, modify if needed
    private final static ArrayList<Car> cars = new ArrayList<>();
    private final static WorkShop<Volvo240> shop = new WorkShop<>(2, new Point(300, 300));

    //methods:
    public boolean addCar(Car car) {
        return cars.add(car);
    }

    public void update() {
        ArrayList<Car> removables = new ArrayList<>();
        for (Car car : cars) {
            car.move();
            Point pos = car.getPosition();
            if (inRange(pos, shop.getPosition()) && car instanceof Volvo240) {
                shop.addCar((Volvo240) car);
                removables.add(car);
            }
        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void rebound(Car car, Point newPos) {
        double speed = car.getSpeed();
        car.stopEngine();
        car.turnAround();
        car.setPosition(newPos);
        car.setSpeed(speed);
    }

    private boolean inRange(Point carPos, Point shopPos) {
        return (carPos.getX() - shopPos.getX() >= -RANGE && carPos.getX() - shopPos.getX() <= RANGE) &&
                (carPos.getY() - shopPos.getY() >= -RANGE && carPos.getY() - shopPos.getY() <= RANGE);
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
            for (Car car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount / 100);
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    public void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) ((Saab95) car).setTurboOn();
        }
    }

    public void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) ((Saab95)car).setTurboOff();
        }
    }

    public void lowerRamp() {
        for (Car car : cars) {
            if (car instanceof TransportVehicles) ((TransportVehicles) car).lowerRamp();
        }
    }

    public void liftRamp() {
        for (Car car : cars) {
            if (car instanceof TransportVehicles) ((TransportVehicles) car).liftRamp();
        }
    }

    public void startEngine() {
        for (Car car : cars) car.startEngine();
    }

    public void stopEngine() {
        for (Car car : cars) car.stopEngine();
    }
}