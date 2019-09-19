package f_dbcp;

import java.sql.Connection;
import java.sql.SQLException;

public class Client {

    public static void main(String[] args) {
        Connection connection=DBCPUtil.getConnection();
        try {
            System.out.println("connection："+connection);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
