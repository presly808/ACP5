package ua.artcode.dao;

import ua.artcode.exception.NoUserFoundException;
import ua.artcode.model.Client;

import java.util.List;

/**
 * CRUD
 */
public interface ClientDao {

    public void create(Client client);

    public void delete(int id);

    public Client find(String login) throws NoUserFoundException;

    public void update(Client client);

    public List<Client> findAll();

}
