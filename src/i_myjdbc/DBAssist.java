package i_myjdbc;

import javax.sql.DataSource;
import java.sql.*;

//jdbc小框架
public class DBAssist {

    //注入数据源---框架做到无关性 通用性 注入datasource的子类 任意数据源的实现类
    private static DataSource dataSource;

    public DBAssist(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //传入参数  sql 占位符
    public static void executeUpdate(String sql, Object... param) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ParameterMetaData metaData = preparedStatement.getParameterMetaData();
            int count = metaData.getParameterCount();
            if (param.length != count) {
                throw new Exception("参数个数不一致");
            } else {
                for (int i = 1; i <= count; i++) {
                    preparedStatement.setObject(i, param[i-1]);
                }
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            release(connection,preparedStatement,resultSet);
        }

    }

    //查询方法
    public static Object executeQuery(String sql, ResultSetHandler handler,Object... param) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ParameterMetaData metaData = preparedStatement.getParameterMetaData();
            int count = metaData.getParameterCount();
            if (param.length != count) {
                throw new Exception("参数个数不一致");
            } else {
                for (int i = 1; i <= count; i++) {
                    preparedStatement.setObject(i, param[i-1]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            //结果集转换为实体类
            return handler.handle(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            release(connection,preparedStatement,resultSet);
        }
        return null;
    }

    public static void release(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
