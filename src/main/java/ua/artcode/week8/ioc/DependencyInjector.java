package ua.artcode.week8.ioc;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by serhii on 16.03.15.
 */
public class DependencyInjector {

    private Map<String,Object> context;


    public DependencyInjector(){
        context = new HashMap<>();
        context.put("ua.artcode.week8.ioc.IService", new ServiceB());
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
