package b_datasouce;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Myconnection2 extends ConnectionAdapter {

    //实例
    private Connection oldconn;
    private List<Connection> pool;

    public Myconnection2(Connection oldconn, List<Connection> pool) {
        super(oldconn);
        this.oldconn = oldconn;
        this.pool = pool;
    }

    //复写父类方法
    @Override
    public void close() throws SQLException {
        pool.add(oldconn);
    }

    @Override
    public Statement createStatement() throws SQLException {
        return super.createStatement();
    }
}
