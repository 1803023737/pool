package f_dbcp;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPUtil {

    private static DataSource dataSource;
    private static Properties properties=new Properties();

    static {

        try {
            InputStream in = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcp.properties");
            properties.load(in);
            dataSource= BasicDataSourceFactory.createDataSource(properties);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        try {
            Connection connection = dataSource.getConnection();

            return  connection;
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("获得数据库连接失败！");
        }
    }

}
