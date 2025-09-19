package task2.nothing;

import task2.Charger;
import task2.Device;
import task2.DeviceFactory;

public class NothingFactory implements DeviceFactory {
    @Override
    public Device createDevice() {
        return new NothingPhone();
    }

    @Override
    public Charger createCharger() {
        return new NothingCharger();
    }
}