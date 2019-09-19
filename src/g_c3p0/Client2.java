package g_c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Client2 {

    private static ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource("mysql");



    public static Connection getConnection(){
        try {
            Connection connection = comboPooledDataSource.getConnection();

            return  connection;
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("获得数据库连接失败！");
        }
    }

    public static void main(String[] args) {

        Connection connection = Client2.getConnection();
        System.out.println(connection);
    }

}
