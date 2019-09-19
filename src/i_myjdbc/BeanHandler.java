package i_myjdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//转换bean实现类  一条记录
public class BeanHandler implements ResultSetHandler {

    private Class clazz;

    //封装的目标类
    public BeanHandler(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object handle(ResultSet resultSet) {

        try {
            Object o = clazz.newInstance();
            //封装属性 javabean的书写前提  这边事先约定  类的属性与数据库字段一致，表名与类名一致
            //反射获得类属性
            //Field[] fields = clazz.getDeclaredFields();
            //数据库元对象获得表的列名
            ResultSetMetaData metaData = resultSet.getMetaData();
            //表的列数
            if (resultSet.next()) {
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnClassName = metaData.getColumnName(i + 1);

                    Object value = resultSet.getObject(columnClassName);
                    Field field = clazz.getDeclaredField(columnClassName);
                    field.setAccessible(true);
                    field.set(o, value);
                }

            }

            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //获得set   name
    private static String setMethodName(String fildeName) throws Exception {
        String s = "set" + fildeName.substring(0, 1).toUpperCase() + fildeName.substring(1).toString();
        return s;
    }
}
