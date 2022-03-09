package ru.gb;

import java.util.concurrent.Callable;

public class Car extends Cars implements Runnable, Callable<int[]> {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            addPreparation();
            while (getPreparation() < 4) {
                ;  // ждем когда все подготовятся
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if(getRaceEnd() == 0) {
            System.out.println(this.name + " WIN");
        }
        addRaceEnd();
    }

    @Override
    public int[] call() throws Exception {
        int[] arr = new int[2];
        arr[0]=getPreparation();
        arr[1]=getRaceEnd();
        return arr;
    }
}
