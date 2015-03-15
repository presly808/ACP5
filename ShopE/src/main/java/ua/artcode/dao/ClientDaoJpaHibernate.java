package ua.artcode.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ua.artcode.exception.NoUserFoundException;
import ua.artcode.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class ClientDaoJpaHibernate implements ClientDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ClientDaoJpaHibernate() {
    }

    public ClientDaoJpaHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void create(Client client) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Client find(String login) throws NoUserFoundException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Client> clients = entityManager.createQuery("from Client c where c.login = :login")
                .setParameter("login", login)
                .setMaxResults(1)
                .getResultList();

        if(clients.isEmpty()){
            throw new NoUserFoundException();
        }

        return clients.get(0);
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
