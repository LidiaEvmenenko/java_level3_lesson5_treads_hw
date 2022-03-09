package ru.gb;

import java.util.concurrent.atomic.AtomicInteger;

public class Cars {
    private static AtomicInteger preparation = new AtomicInteger(0);
    private static AtomicInteger raceEnd = new AtomicInteger(0);

    public static void addPreparation() {
        preparation.incrementAndGet();
    }

    public static void addRaceEnd() {
        raceEnd.incrementAndGet();
    }

    public static int getRaceEnd() {
        return raceEnd.intValue();
    }

    public static int getPreparation() {
        return preparation.intValue();
    }
}
