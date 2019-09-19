package a_pool;

import java.sql.Connection;
import java.util.List;

public class Client {

    public static void main(String[] args) {

        List<Connection> pool= SimpleConnectionPool.getPool();
        for (Connection connection : pool) {
            System.out.println(connection);
        }
        System.out.println("总连接数："+pool.size());
        System.out.println("==============取出连接==============");
        Connection c1 = SimpleConnectionPool.getConnectionByPool();
        System.out.println("总连接数："+pool.size());
        System.out.println(c1);
        Connection c2 = SimpleConnectionPool.getConnectionByPool();
        System.out.println("总连接数："+pool.size());
        System.out.println(c2);
        System.out.println("==============归还连接==============");
        SimpleConnectionPool.closeConnection(c1);
        System.out.println("总连接数："+pool.size());
        SimpleConnectionPool.closeConnection(c2);
        System.out.println("总连接数："+pool.size());

    }


}
