package g_c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class Client {

    private static ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();

    static {
        try {
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/pool?characterEncoding=utf-8");
            comboPooledDataSource.setPassword("123456");
            comboPooledDataSource.setUser("root");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            Connection connection = comboPooledDataSource.getConnection();

            return  connection;
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("获得数据库连接失败！");
        }
    }

    public static void main(String[] args) {

        Connection connection = Client.getConnection();
        System.out.println(connection);
    }

}
