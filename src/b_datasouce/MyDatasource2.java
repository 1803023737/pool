package b_datasouce;

import util.JdbcUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

//java 标准数据库连接池规范====可以弄成单例模式
public class MyDatasource2 implements DataSource {

    private  List<Connection> pool = Collections.synchronizedList(new ArrayList());

    public MyDatasource2() {
        for (int i = 0; i < 10; i++) {
            try {
                Connection connection = JdbcUtil.getConnection();
                pool.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Connection> getPool() {
        return pool;
    }

    public void setPool(List<Connection> pool) {
        this.pool = pool;
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (pool != null && pool.size() > 0) {
            final Connection conn = pool.remove(0);
            //包装者模式  获得封装的包装者
            //MyConnnect new_conn=new MyConnnect(conn,pool);
            //适配者模式
            //Myconnection2 new_conn=new Myconnection2(conn,pool);
            Connection new_conn = (Connection) Proxy.newProxyInstance(this.getClass().getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if ("close".equals(method.getName())) {
                        //方法是close时，修改其方法！
                        System.out.println("调用方法时close，修改方法，将connect放入连接池中！");
                        return pool.add(conn);
                    }else{
                        //其他方法还是调用原来的方法
                        return method.invoke(conn,args);
                    }

                }
            });
            return new_conn;
        } else {
            throw new RuntimeException("连接池中的连接已经用完！");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
