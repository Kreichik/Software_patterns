package task2.oppo;

import task2.Charger;

public class OppoCharger implements Charger {
    @Override
    public void power() {
        System.out.println("5W");
    }

    @Override
    public void protocols() {
        System.out.println("Quick Charge");
    }
}