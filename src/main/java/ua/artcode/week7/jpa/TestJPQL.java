package ua.artcode.week7.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * Created by serhii on 11.03.15.
 */
public class TestJPQL {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_unit");
        EntityManager entityManager = factory.createEntityManager();
        List<Author> authors = findByName("Kolia",entityManager);

    }

    public static List<Author> findByName(String name, EntityManager entityManager){
        TypedQuery query = entityManager.createQuery("select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        List<Author> authorList = query.getResultList();
        return authorList;

    }


}
