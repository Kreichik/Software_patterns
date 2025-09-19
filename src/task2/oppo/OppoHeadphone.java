package task2.oppo;

import task2.Device;

public class OppoHeadphone implements Device {
    @Override
    public void type() {
        System.out.println("Headphone");
    }

    @Override
    public void brand() {
        System.out.println("Oppo");
    }
}