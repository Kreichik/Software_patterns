package task2;

interface Device {
    void type();
    void brand();
}

interface Charger {
    void power();
    void protocols();
}

interface DeviceFactory {
    Device createDevice();
    Charger createCharger();
}

class OppoFactory implements DeviceFactory {
    @Override
    public Device createDevice() {
        return new OppoHeadphone();
    }

    @Override
    public Charger createCharger() {
        return new OppoCharger();
    }
}

class NothingFactory implements DeviceFactory {
    @Override
    public Device createDevice() {
        return new NothingPhone();
    }

    public Charger createCharger() {
        return new NothingCharger();
    }
}

class OppoHeadphone implements Device {
    @Override
    public void type() {
        System.out.println("Headphone");
    }

    @Override
    public void brand() {
        System.out.println("Oppo");
    }
}

class OppoCharger implements Charger {
    @Override
    public void power() {
        System.out.println("5W");
    }

    @Override
    public void protocols() {
        System.out.println("Quick Charge");
    }
}

class NothingPhone implements Device {
    @Override
    public void type() {
        System.out.println("Phone");
    }

    @Override
    public void brand() {
        System.out.println("Nothing");
    }
}

class NothingCharger implements Charger {
    @Override
    public void power() {
        System.out.println("45W");
    }

    @Override
    public void protocols() {
        System.out.println("Quick Charge, Power Delivery");
    }
}



public class Application {
    private static DeviceFactory factory;

    public static void initialize(String type){
        if(type.equals("Nothing")){
            factory = new NothingFactory();
        }
        else if(type.equals("Oppo")){
            factory = new OppoFactory();
        }
    }

    public static void makeOrder(){
        Device device = factory.createDevice();
        Charger charger = factory.createCharger();

        System.out.println("Making Order...");
        System.out.print("Type: ");
        device.type();

        System.out.print("Brand: ");
        device.brand();

        System.out.print("Power: ");
        charger.power();

        System.out.print("Protocols: ");
        charger.protocols();


        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        String company = "Nothing";

        initialize(company);

        makeOrder();



        company = "Oppo";

        initialize(company);

        makeOrder();
    }

}
