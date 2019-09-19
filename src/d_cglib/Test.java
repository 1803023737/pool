package d_cglib;

import c_proxy.Human;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) {

        /*cglib 是第三方jar 可以完成无实现接口的bean的代理   基于子类的动态代理*/
        /*实现原理：代理类是被代理类的子类*/
        /*要求：
        * 1.被代理类 是public
        * 2.被代理类不能final修饰
        * 3.引入cglib 的jar包
        * */
        final SpringBrother springBrother = new SpringBrother();
        SpringBrother proxyman = (SpringBrother) Enhancer.create(SpringBrother.class, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理了！！！！");
                if ("sing".equals(method.getName())) {
                    System.out.println("经纪人代理sing");
                    float money = (float) args[0];
                    return method.invoke(springBrother, money / 2);
                } else if ("dance".equals(method.getName())) {
                    System.out.println("经纪人代理dance");
                    float money = (float) args[0];
                    return method.invoke(springBrother, money / 2);
                } else {
                    System.out.println("其他不赚钱，不管！");
                    return method.invoke(springBrother, args);
                }
            }
        });

        proxyman.sing(1000);
        proxyman.dance(1000);
        proxyman.eat();
    }
}
