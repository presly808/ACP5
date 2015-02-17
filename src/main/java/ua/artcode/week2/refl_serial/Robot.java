package ua.artcode.week2.refl_serial;

/**
 * Created by serhii on 27.01.15.
 */
@MySerial(limit = 10, desc = "/home/serhii/robot.txt")
public class Robot {


    @Save("robot.id")
    private int id;
    @Save("robot.power")
    private int power;
    @Save("robot.model")
    private String model;
    @Save
    private double price;

    private long accessKey;

    public Robot() {
    }

    public Robot(int id, int power, String mode,
                 long accessKey, double price) {
        this.id = id;
        this.power = power;
        this.model = mode;
        this.accessKey = accessKey;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(long accessKey) {
        this.accessKey = accessKey;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "id=" + id +
                ", power=" + power +
                ", model='" + model + '\'' +
                ", accessKey=" + accessKey +
                ", price=" + price +
                '}';
    }
}
