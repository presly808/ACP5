package ua.artcode.week7.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by serhii on 10.03.15.
 */
public class SetupEntityMangerFactory {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_unit");

    }
}
