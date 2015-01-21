package ua.artcode.week1.day2;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by serhii on 20.01.15.
 */
public class TestHash{

    public static void main(String[] args) {
        Employee e1 = new Employee(12, 25, 2000, 1000);
        Employee e2 = new Employee(12, 25, 2000, 1000);

        /*Test contract equals hashCode*/
        System.out.println(e1.equals(e2) == (e1.hashCode() == e2.hashCode()));



        Employee emp1 = new Employee(1, 25, 2000, 1000);
        Employee emp2 = new Employee(2, 22, 400, 1000);
        Employee emp3 = new Employee(3, 18, 200, 1000);
        Employee emp4 = new Employee(4, 30, 2000, 1000);
        Employee emp5 = new Employee(5, 50, 20000, 1000);

        Set<Employee> set = new LinkedHashSet<>(8, 50f);

        set.add(emp1);
        set.add(emp2);
        set.add(emp3);
        set.add(emp4);
        set.add(emp5);

        for(Employee em : set){
            System.out.println(em.getId());
        }




    }


}
