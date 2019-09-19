package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

    //数据库连接四大参数
    private static String url;
    private static String username;
    private static String password;
    private static String driverClass;

    private static Connection connection;

    //初始化四大参数
    static {
        ClassLoader classLoader = JdbcUtil.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            driverClass = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
            try {
                in.close();
                 //加载数据库驱动
                 Class.forName(driverClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url,username,password);
        return connection;
    }

}
