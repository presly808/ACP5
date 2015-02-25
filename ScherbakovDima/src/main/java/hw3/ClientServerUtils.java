package hw3;

import hw3.anotations.SerializableClass;
import hw3.anotations.SerializableField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientServerUtils<T> {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public void save(T object, String path){
        Class cl = object.getClass();

        if(!cl.isAnnotationPresent(SerializableClass.class)){
            throw new UnsupportedOperationException("Haven't got annotation SerializableClass!");
        }

        PrintWriter pw = null;

        try {
            pw = new PrintWriter(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        pw.println(cl.getName());

        Field[] fields = cl.getDeclaredFields();

        for(Field field : fields) {
            SerializableField sf = field.getAnnotation(SerializableField.class);
            if (sf != null){
                field.setAccessible(true);
                try {
                    if(field.get(object) instanceof Date){
                        String date = dateFormatter.format(field.get(object));
                        pw.printf("%s=%s", sf.name(), date);
                        pw.println();
                    } else {
                        pw.printf("%s=%s", sf.name(), field.get(object));
                        pw.println();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else continue;
            pw.flush();
        }
        pw.close();
    }

    public T load(String path) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Class cl = null;

        try {
            String className = scanner.nextLine();
            cl = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        T object = null;

        if (cl != null){
            try {
                object = (T) cl.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Field[] fields = cl.getDeclaredFields();
        String line = null;

        for(Field field : fields){
            try {
                line = scanner.nextLine();
            } catch (NoSuchElementException exception){
                break;
            }

            String[] parameters = line.split("=");
            field.setAccessible(true);

            try {
                field.set(object, new Integer(parameters[1]));
                continue;
            } catch (IllegalAccessException e) {}
            catch (NumberFormatException ex){}
            catch (IllegalArgumentException exe){}

            try {
                field.set(object, parameters[1]);
                continue;
            } catch (IllegalAccessException e) {}
            catch (IllegalArgumentException ex){}


            try {
                String p = parameters[1];
                Date date = dateFormatter.parse(p);
                field.set(object, date);
                continue;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {}
            catch (IllegalArgumentException exe){}
        }
        return object;
    }
}