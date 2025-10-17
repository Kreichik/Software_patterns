package task5.devices;

public class Light implements Device {
    private String name;
    private boolean isOn = false;
    private final double powerInWatts = 60.0;


    public Light(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " is ON");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " is OFF");
    }

    @Override
    public String getStatus() {
        return name + " is " + (isOn ? "ON" : "OFF");
    }

    @Override
    public double getPowerValue() {
        return isOn ? powerInWatts : 0;
    }

    public void setDimEffect(int level) {
        if (isOn) {
            System.out.println(name + " is dimmed to " + level + "%");
        } else {
            System.out.println(name + " Turn ON light before set dim effect");
        }
    }
}