package com.smarthome.devices.concrete;

import com.smarthome.devices.base.AbstractDevice;
import javassist.compiler.ast.StringL;

public class GenericDevice extends AbstractDevice {

    public GenericDevice(String name, double power, String location, String paramKey, String paramValue) {
        super(name, power, location);
        this.parameters.put(paramKey, paramValue);
    }

    @Override
    public void operate() {
        if (isOn) {
            System.out.println("Generic Device '" + name + "' is operating with parameters: " + getAllParameters());
        } else {
            System.out.println("Generic Device '" + name + "' is off");
        }
    }

    @Override
    public String getDeviceType() {
        return "GenericDevice";
    }

    @Override
    public String getLocation(){
        return location;
    }
}