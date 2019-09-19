package e_pool;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

//管理对象的工厂
public class BeanFactory2 {

    private static Properties properties=new Properties();
    static{
        InputStream in = BeanFactory2.class.getClassLoader().getResourceAsStream("bean.properties");
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
            final Object instance = clazz.newInstance();
            //通过代理方式实现aop编程-----再不更改原先代码的基础上  完成功能，开闭原则！！！
            //动态代理是实现aop 面向切面的核心技术
            Object o = Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("实现的类："+instance);
                    System.out.println("实现的方法："+method.getName());
                    long begin=System.currentTimeMillis();
                    System.out.println("开始时间："+begin);
                    Object invoke = method.invoke(instance, args);
                    long end=System.currentTimeMillis();
                    System.out.println("结束时间："+end);
                    System.out.println("总共耗时："+(end-begin));
                    return invoke;
                }
            });
            return  o;
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
