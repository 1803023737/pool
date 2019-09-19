package b_datasouce;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {

    public static void main(String[] args) {
        //MyDatasource myDatasource=new MyDatasource();
        MyDatasource2 myDatasource=new MyDatasource2();
        Connection connection=null;
        Statement statement=null;
        try {
            connection= myDatasource.getConnection();
            statement=connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    //这边调用的封装后的关闭
                    System.out.println(myDatasource.getPool().size());
                    connection.close();
                    System.out.println(myDatasource.getPool().size());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
