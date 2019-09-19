package e_pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//管理对象的工厂
public class BeanFactory {

    private static Properties properties=new Properties();
    static{
        InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("文件加载失败！");
        }
    }

    //从配置文件中获得bean
    public static Object getDaoImpl(String key){
        String classAllName = properties.getProperty(key);
        try {
            Class<?> clazz = Class.forName(classAllName);
            Object instance = clazz.newInstance();
            return  instance;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
