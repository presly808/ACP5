package ua.artcode.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by serhii on 11.03.15.
 */
public class EntityManagerFactoryHolder {
    private static EntityManagerFactory factory = null;



    public static synchronized EntityManagerFactory getFactory(){
        if(factory == null) {
            factory = Persistence.createEntityManagerFactory("my_unit");
        }

        return factory;
    }




}
