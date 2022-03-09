package ru.gb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App
{
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel((int)(CARS_COUNT/2)), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT); //*****
        executorService.execute(() -> {
            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
            }
        });
        while (true) {
            try {
                if ((cars[0].call()[0] == CARS_COUNT))
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        while (true) {
            try {
                if ((cars[0].call()[1] == CARS_COUNT))
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        executorService.shutdown();
    }
}
