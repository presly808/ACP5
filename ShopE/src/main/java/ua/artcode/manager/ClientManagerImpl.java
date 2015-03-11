package ua.artcode.manager;

import org.hibernate.Hibernate;
import ua.artcode.dao.ClientDao;
import ua.artcode.dao.ClientDaoImpl;
import ua.artcode.dao.ClientDaoJpaHibernate;
import ua.artcode.exception.NoUserFoundException;
import ua.artcode.model.Client;
import ua.artcode.utils.EntityManagerFactoryHolder;

/**
 * Created by serhii on 24.02.15.
 */
public class ClientManagerImpl implements ClientManager {

    private ClientDao dao = new ClientDaoJpaHibernate(EntityManagerFactoryHolder.getFactory());

    @Override
    public void register(String login, String pass, String phone, String email) {
        //validation
        Client client = new Client(login, pass, email, phone);
        dao.create(client);
    }

    @Override
    public String signIn(String login, String pass) throws NoUserFoundException {

        Client client = dao.find(login);

        return client.toString();
    }

    @Override
    public void logout(String accessKey) {

    }
}
