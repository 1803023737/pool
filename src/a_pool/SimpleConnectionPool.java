package a_pool;

import b_datasouce.MyConnnect;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//模拟连接池的实现原理----缓存思想
public class SimpleConnectionPool {

    private static List<Connection> pool = new ArrayList<>();

    //初始化10个连接
    static {
        for (int i = 0; i < 10; i++) {
            try {
                Connection connection = JdbcUtil.getConnection();
                pool.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //获得connection(从连接池中获得  list为公共，存在多线程并发的问题，所以枷锁)
    public synchronized static Connection getConnectionByPool() {
        if (pool != null && pool.size() > 0) {
            Connection conn = pool.remove(0);//这边的conn是未包装的，调用的方法是销毁的方法
            //包装者模式  获得封装的包装者
            MyConnnect new_conn=new MyConnnect(conn,pool);
            return new_conn;
        } else {
            throw new RuntimeException("连接池中的连接已经用完！");
        }
    }

    //归还连接
    public static void closeConnection(Connection connection) {
        pool.add(connection);
    }

    //获得连接池
    public static List<Connection> getPool(){
        return pool;
    }

}
