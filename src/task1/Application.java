package task1;

public class Application {
    public static void main(String[] args) {
        Director director = new Director();

        CarBuilder carBuilder = new CarBuilder();

        director.create_new_car(carBuilder);

        Car res_car = carBuilder.getResult();
        System.out.println("Seats: " + res_car.getSeats());
        System.out.println("Engine: " + res_car.getEngine());
        System.out.println("Color: " + res_car.getColot());

    }
}


class Director {
    public void create_new_car(Builder builder){
        builder.reset();
        builder.setSeats(5);
        builder.setEngine("Very power dvizhok");
        builder.setColor("Grey");

    }
}

interface Builder {
    void reset();
    void setSeats(int seats);
    void setEngine(String engine);
    void setColor(String color);
}

class CarBuilder implements Builder {
    private Car car;

    public void reset() {
        car = new Car();
    }

    public void setSeats(int seats) {
        car.setSeats(seats);
    }

    public void setEngine(String engine) {
        car.setEngine(engine);
    }

    public void setColor(String color) {
        car.setColor(color);
    }

    public Car getResult() {return car;}


}

class Car {
    private int seats;
    private String engine;
    private String color;

    public void setSeats(int seats) {
        this.seats = seats;
    }
    public void setEngine (String engine) {

        this.engine = engine;
    }
    public void setColor (String color){
        this.color = color;
    }


    public int getSeats() {
        return seats;
    }

    public String getEngine() {
        return engine;
    }

    public String getColot(){
        return color;
    }


}