package ua.artcode.week2.refl_serial;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by serhii on 27.01.15.
 */
public class AnnotationPostProcess <T> {
    //TODO replace path location
    public void save(T obj) throws IllegalAccessException, FileNotFoundException {
        Class cl = obj.getClass();

        if(!cl.isAnnotationPresent(MySerial.class)){
            throw new UnsupportedOperationException(
                    "Doesn't annotate class " + cl.getName() + " by MySerial annotation");
        }

        MySerial annotation = (MySerial) cl.getAnnotation(MySerial.class);

        String path = annotation.desc();
        PrintWriter pw = new PrintWriter(path);
        pw.println(cl.getName());

        Field[] fields = cl.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            Save saveAnnotation = field.getAnnotation(Save.class);
            if(saveAnnotation != null){
                //save logic
                String value = saveAnnotation.value();
                String saveString = String.format("%s=%s",
                                value.isEmpty() ? field.getName() : value,
                                field.get(obj));
                pw.println(saveString);
            }
        }

        pw.flush();
        pw.close();
    }

    public T load() throws ClassNotFoundException {
        Class cl = Class.forName("ua.artcode.week2.refl_serial.Robot");
        return null;
    }

}
