package ua.artcode.week7.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by serhii on 10.03.15.
 */
public class SimpleOperationEntityManger {

    public static void main(String[] args) {
        EntityManager entityManager = Persistence.
                createEntityManagerFactory("my_unit").
                createEntityManager();

        Author author = new Author(2000, new Date(),
                                        "Kolia", "asd@asda.com", AuthorType.UKR);

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(author);
        tx.commit();

        Author loaded = entityManager.find(Author.class, 1);
        System.out.println(loaded);

    }
}