package task2;

import task2.nothing.NothingFactory;
import task2.oppo.OppoFactory;

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

    public static void tochnoNeMain(){
//        String company = type;
        String company = "Nothing";

        initialize(company);

        makeOrder();



        company = "Oppo";

        initialize(company);

        makeOrder();
    }

    public static void main(String[] args) {
        tochnoNeMain();
    }
}