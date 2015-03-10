package ua.artcode.week7.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Created by serhii on 10.03.15.
 */
public class TestRelations {


    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("my_unit");
        EntityManager entityManager = factory.createEntityManager();


        Author author = new Author(2000, new Date(),
                "Fedor Dostoyevskiy", "asd@asda.com", AuthorType.UKR);

        Book book1 = new Book("Idiot", author);
        Book book2 = new Book("Zlochin i Kara", author);

        author.getBooks().add(book1);
        author.getBooks().add(book2);

        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.getTransaction().commit();

        Author loaded = entityManager.find(Author.class, author.getId());
        System.out.println(loaded);
        deleteAuthor(entityManager, loaded);


        Book book3 = new Book("NewBook", new Author(1222, new Date(), "newAuthor", "", AuthorType.UKR));
        saveBookWithAuthor(entityManager, book3);

    }


    public static void deleteAuthor(EntityManager entityManager, Author author) {
        entityManager.getTransaction().begin();
        entityManager.remove(author);
        entityManager.getTransaction().commit();

    }

    public static void saveBookWithAuthor(EntityManager entityManager, Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

}
