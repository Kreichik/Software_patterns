package task5.devices;

public class SecurityCamera implements Device {
    private String name;
    private boolean isRecording = false;
    private final double powerInWatts = 25.0;


    public SecurityCamera(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        isRecording = true;
        System.out.println(name + " Camera is enabled and recording");
    }

    @Override
    public void turnOff() {
        isRecording = false;
        System.out.println(name + " Camera is disabled");
    }

    @Override
    public String getStatus() {
        return name + " Camera is " + (isRecording ? "RECORDING" : "OFF");
    }

    @Override
    public double getPowerValue() {
        return isRecording ? powerInWatts : 0;
    }
}