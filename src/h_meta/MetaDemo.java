package h_meta;

import f_dbcp.DBCPUtil;
import org.junit.Test;

import java.sql.*;

//数据元获取
public class MetaDemo {

    @Test
    public void test() {

        Connection connection = DBCPUtil.getConnection();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            //获得数据库名称
            String databaseProductName = metaData.getDatabaseProductName();
            System.out.println("数据库名称：" + databaseProductName);
            //数据库版本
            String version = metaData.getDatabaseProductVersion();
            System.out.println("数据库版本：" + version);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {

        Connection connection = DBCPUtil.getConnection();
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement("select * from USER where id=?");
            PreparedStatement preparedStatement = connection.prepareStatement("????????????");
            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
            //统计参数个数  就是问号的个数S
            int parameterCount = parameterMetaData.getParameterCount();
            System.out.println("参数的个数：" + parameterCount);
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        Connection connection = DBCPUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id=?");
            preparedStatement.setString(1,"1");
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            //结果集中列的个数
            System.out.println(metaData.getColumnCount());
            for (int i = 1; i <=metaData.getColumnCount(); i++) {
                System.out.println(metaData.getColumnClassName(i)+"===="+metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
