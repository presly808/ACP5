package ua.artcode.week6.db;

import java.sql.*;
import java.util.Enumeration;

/**
 * Created by serhii on 24.02.15.
 */
public class JDBCUtils {

    public static void transactionOperations()  {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try{

            connection = ConnectionFactory.getConnection();
            ps =
                    connection.prepareStatement("INSERT INTO users (name, age,email) VALUES (?,?,?)");
            ps.setString(1, "Vania");
            ps.setInt(2, 34);
            ps.setString(3, "+38 093 456 78 98");

            connection.setAutoCommit(false);
            ps.execute();
            ps.execute("INSERT INTO addresses (city, street) VALUES ('Odessa','Main')");
            connection.commit();



        } catch (SQLException e){
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(ps != null){
                try {
                    ps.close();
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
