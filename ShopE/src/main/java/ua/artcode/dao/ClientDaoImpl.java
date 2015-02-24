package ua.artcode.dao;

import ua.artcode.model.Client;
import ua.artcode.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by serhii on 24.02.15.
 */
public class ClientDaoImpl implements ClientDao {

    @Override
    public void create(Client client) {
        try (Connection connection = ConnectionFactory.getConnection();
                Statement statement = connection.createStatement()){

            String prepareSql =
                    String.format("INSERT INTO clients (login,pass,phone,email) VALUES ('%s','%s','%s','%s');",
                            client.getLogin(),client.getPass(), client.getPhone(), client.getEmail());
            statement.execute(prepareSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Client find(String login) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String prepareSql =
                    String.format("SELECT id,login,phone,email FROM clients WHERE login='%s' LIMIT 1;",
                            login);

            ResultSet resultSet = statement.executeQuery(prepareSql);
            Client client = new Client();

            if(resultSet.next()){
                client.setId(resultSet.getInt("id"));
                client.setLogin(resultSet.getString("login"));
                client.setPhone(resultSet.getString("phone"));
                client.setEmail(resultSet.getString("email"));
            } else {
                return null;
                // create own exception and throw if necessary
            }

            return client;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public List<Client> findAll() {
        return null;
    }
}
