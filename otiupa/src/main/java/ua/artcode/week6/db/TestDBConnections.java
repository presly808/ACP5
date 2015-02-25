package ua.artcode.week6.db;

import java.sql.*;
import java.util.Enumeration;

/**
 * Created by otiupa on 23.02.2015.
 */
public class TestDBConnections {

    public static final String PASSWORD = "berlinnn";
    public static final String USER = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/ACP5";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name, age, email FROM users;");

        while (resultSet.next()){
//            resultSet.getString("name");
            System.out.printf("name : %s, age : %s, email : %s\n",
                    resultSet.getString("name"),
                    resultSet.getString("age"),
                    resultSet.getString("email"));
        }


//        showAllDrivers();

    }

    public static void showAllDrivers(){
        Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
        while (driverEnumeration.hasMoreElements()){
            Driver driver = driverEnumeration.nextElement();
            System.out.println(driver);
        }
    }
}
