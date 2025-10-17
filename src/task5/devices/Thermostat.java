package task5.devices;

public class Thermostat implements Device {
    private String name;
    private boolean isOn = false;
    private int temperature;
    private static final int ECO_TEMPERATURE = 18;
    private static final int DEFAULT_TEMPERATURE = 21;
    private final double powerInWatts = 150.0;


    public Thermostat(String name) {
        this.name = name;
        this.temperature = DEFAULT_TEMPERATURE;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " is ON. Default temperature is " + this.temperature + "°C");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " is OFF");
    }

    @Override
    public String getStatus() {
        if (isOn) {
            return name + " is ON (Set to " + this.temperature + "°C)";
        } else {
            return name + " is OFF";
        }
    }

    @Override
    public double getPowerValue() {
        return isOn ? powerInWatts : 0;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        if (isOn) {
            System.out.println(name + " temperature set to " + this.temperature + "°C.");
        }
    }

    public void setEcoMode() {
        if (isOn) {
            System.out.println(name + " is set to Eco Mode.");
            setTemperature(ECO_TEMPERATURE);
        } else {
            System.out.println(name + " must be on to set Eco Mode.");
        }
    }
}