package ua.artcode.manager;

import ua.artcode.dao.ClientDao;
import ua.artcode.dao.ClientDaoImpl;
import ua.artcode.model.Client;

/**
 * Created by serhii on 24.02.15.
 */
public class ClientManagerImpl implements ClientManager {

    private ClientDao dao = new ClientDaoImpl();

    @Override
    public void register(String login, String pass, String phone, String email) {
        //validation
        Client client = new Client(login, pass, email, phone);
        dao.create(client);
    }

    @Override
    public String signIn(String login, String pass) {

        Client client = dao.find(login);

        return client.toString();
    }

    @Override
    public void logout(String accessKey) {

    }
}
