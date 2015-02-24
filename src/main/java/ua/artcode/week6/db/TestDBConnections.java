package ua.artcode.week6.db;

import java.sql.*;
import java.util.Enumeration;

/**
 * Created by serhii on 23.02.15.
 */
public class TestDBConnections {

    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/ACP5";
    public static final String USER = "root";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT name,age,email FROM users;");

            while (resultSet.next()){
                System.out.printf("name : %s, age : %d, email : %s\n",
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }




    }

    public static void showAllDrivers(){
        Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
        while(driverEnumeration.hasMoreElements()){
            Driver driver = driverEnumeration.nextElement();
            System.out.println(driver);
        }
    }

}
