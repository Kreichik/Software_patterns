package task2.nothing;

import task2.Device;

public class NothingPhone implements Device {
    @Override
    public void type() {
        System.out.println("Phone");
    }

    @Override
    public void brand() {
        System.out.println("Nothing");
    }
}