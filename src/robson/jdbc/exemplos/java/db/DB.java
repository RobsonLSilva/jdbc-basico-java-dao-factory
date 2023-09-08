package robson.jdbc.exemplos.java.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn == null) {
            try {
                Properties prop = loadProperties();
                String url = prop.getProperty("dburl");
                conn = DriverManager.getConnection(url, prop);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return conn;
    }

    public static void closeConnection(){
        if (conn != null) try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Properties loadProperties(){
        Properties prop = new Properties();
        try (FileInputStream fs = new FileInputStream("src\\db.properties")){
            prop.load(fs);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return prop;
    }

    public static void closeStatement(Statement st){
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
