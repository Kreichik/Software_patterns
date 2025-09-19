package task2.nothing;

import task2.Charger;

public class NothingCharger implements Charger {
    @Override
    public void power() {
        System.out.println("45W");
    }

    @Override
    public void protocols() {
        System.out.println("Quick Charge, Power Delivery");
    }
}