package i_myjdbc;

import java.sql.ResultSet;

//结果集转换处理器
public interface ResultSetHandler {

    Object handle(ResultSet resultSet);



}
