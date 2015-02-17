package ua.artcode.week1.day2;

/**
 * Created by serhii on 20.01.15.
 */
public class Employee {

    private int id;
    private int age;
    private double salary;
    private long count;

    public Employee() {
    }

    public Employee(int id, int age, double salary, long count) {
        this.id = id;
        this.age = age;
        this.salary = salary;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (age != employee.age) return false;
        if (count != employee.count) return false;
        if (id != employee.id) return false;
        if (Double.compare(employee.salary, salary) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + age;
        long temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (count ^ (count >>> 32));
        return result;
    }

}
