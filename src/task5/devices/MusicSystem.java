package task5.devices;

public class MusicSystem implements Device {
    private String name;
    private boolean isPlaying = false;
    private final double powerInWatts = 120.0;


    public MusicSystem(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        isPlaying = true;
        System.out.println(name + " is playing music");
    }

    @Override
    public void turnOff() {
        isPlaying = false;
        System.out.println(name + " has stopped");
    }

    @Override
    public String getStatus() {
        return name + " is " + (isPlaying ? "PLAYING" : "OFF");
    }

    @Override
    public double getPowerValue() {
        return isPlaying ? powerInWatts : 0;
    }

    public void setVolume(int volume) {
        if (isPlaying) {
            System.out.println(name + " volume: " + volume);
        } else {
            System.out.println(name + " must be playing to set volume");
        }
    }
}