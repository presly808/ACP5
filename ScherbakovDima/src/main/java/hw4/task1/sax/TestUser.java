package hw4.task1.sax;

public class TestUser {

    private int id;
    private String name;
    private int age;
    private int salary;
    private Address address;

    public TestUser() {
        address = new Address();
    }

    public TestUser(int id, String name, int age, int salary, String city, String building, String room) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        address = new Address(city, building,room);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getCity() {
        return address.city;
    }

    public String getBuilding() {
        return address.building;
    }

    public String getRoom() {
        return address.room;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setCity(String city) {
        address.city = city;
    }

    public void setBuilding(String building) {
        address.building = building;
    }

    public void setRoom(String room) {
        address.room = room;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", city=" + address.city +
                ", building=" + address.building +
                ", room=" + address.room +
                '}';
    }

    private class Address{

        private String city;
        private String building;
        private String room;

        private Address() {
        }

        private Address(String city, String building, String room) {
            this.city = city;
            this.building = building;
            this.room = room;
        }
    }
}
