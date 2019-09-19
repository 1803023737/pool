package i_myjdbc;

import f_dbcp.DBCPUtil;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class Client {

    private static DataSource dataSource;
    private static Properties properties=new Properties();

    public static void main(String[] args) throws Exception {
        InputStream in = DBCPUtil.class.getClassLoader().getResourceAsStream("dbcp.properties");
        properties.load(in);
        dataSource= BasicDataSourceFactory.createDataSource(properties);
        DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        DBAssist dbAssist=new DBAssist(dataSource);
        try {
           // DBAssist.executeUpdate("update user set username=?  where id=?","abc",1);
            //Object o = DBAssist.executeQuery("select * from user  where id=?", new BeanHandler(User.class), 1);
            Object o = DBAssist.executeQuery("select * from user  ", new BeanListHandler(User.class));
            System.out.println(o);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
