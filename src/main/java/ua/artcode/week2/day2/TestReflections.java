package ua.artcode.week2.day2;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 */
public class TestReflections {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Man man = new Man(1,70,"Oleg", 3, "Peremogy");

        Class cl = Man.class;// man.getClass();
        getRuntimeInfo(cl);

        System.out.println("***************");
        invokeMeth(man, "getWeight");

        Man man1 = createInit(Man.class, 1, 80, "Ivan");
        System.out.println(man1);

    }

    public static<T> T createInit(Class<T> cl, Object...args) throws IllegalAccessException,
                                                                        InstantiationException {
        T obj = cl.newInstance();

        Field[] fields = cl.getDeclaredFields();

        for(int i = 0; i < fields.length; i++){
            fields[i].setAccessible(true);
            fields[i].set(obj, args[i]);
        }

        return obj;
    }



    public static void invokeMeth(Object instance, String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class cl = instance.getClass();
        Method method = cl.getDeclaredMethod(name);
        Object res = method.invoke(instance);
        System.out.println(res);
    }


    public static void getRuntimeInfo(Class cl){
        System.out.println(cl.getName());
        System.out.println(cl.getCanonicalName());

        Field[] fields = cl.getDeclaredFields();
        for(Field field : fields){
            System.out.printf("name=%s, type=%s\n",
                    field.getName(), field.getType().getName());
        }

        Method[] methods = cl.getDeclaredMethods();
        for(Method meth : methods){
            System.out.printf("%s : %s\n", meth.getName(), meth.getReturnType().getName());
        }


    }


}

class Man {

    private int id;
    private double weight;
    private String name;


    public Man() {
    }

    public Man(int id, double weight,
               String name, int num, String street) {
        this.id = id;
        this.weight = weight;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Man{" +
                "id=" + id +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }

    private class Address {

        int num;
        String street;

        public Address(int num, String street) {
            this.num = num;
            this.street = street;
        }
    }


}


