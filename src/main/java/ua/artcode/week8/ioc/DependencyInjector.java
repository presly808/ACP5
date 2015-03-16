package ua.artcode.week8.ioc;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class DependencyInjector {

    private Map<String,Object> context;


    public DependencyInjector(){
        initContext();
    }

    private void initContext() {
        Properties properties = new Properties();
        context = new HashMap<>();
        try {
            /*InputStream resourceAsStream = DependencyInjector.class
                    .getResourceAsStream("week8/context.properties");*/


            properties.load(new FileReader(
                    "/home/serhii/IdeaProjects/ACP5/src/main/resources/week8/context.properties"));
            for(String key : properties.stringPropertyNames()){
                String className = properties.getProperty(key);
                Class cl = Class.forName(className);
                Object instance = cl.newInstance();
                context.put(key, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void doInjection(Object dest){
        Class cl = dest.getClass();

        for(Field field : cl.getDeclaredFields()){
            field.setAccessible(true);

            String className = field.getType().getName();
            Object forInject = context.get(className);

            try {
                field.set(dest, forInject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

}
