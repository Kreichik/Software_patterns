package task2.oppo;

import task2.Charger;
import task2.Device;
import task2.DeviceFactory;

public class OppoFactory implements DeviceFactory {
    @Override
    public Device createDevice() {
        return new OppoHeadphone();
    }

    @Override
    public Charger createCharger() {
        return new OppoCharger();
    }
}