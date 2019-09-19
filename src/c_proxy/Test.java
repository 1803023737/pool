package c_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {

        /*Human human = new SpringBrother();
        human.sing(100);
        human.dance(100);
        human.eat();*/

        /*静态代理---代理对象咱们生成*/
       /* Human springBrother = new SpringBrother();
        Human human=new ProxyManager(springBrother);
        human.sing(100);
        human.dance(100);
        human.eat();*/


        /*jdk动态代理· 是基于接口的动态代理  代理对象自动生成到内存中*/
        final Human springBrother = new SpringBrother();
        Human proxy = (Human) Proxy.newProxyInstance(springBrother.getClass().getClassLoader(), springBrother.getClass().getInterfaces(), new InvocationHandler() {
            //代理的具体策略 调用代理对象的任何方法都会经过该方法！
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理了！！！！");
                if ("sing".equals(method.getName())){
                    System.out.println("经纪人代理sing");
                    float money= (float) args[0];
                    return method.invoke(springBrother,money/2);
                }else if("dance".equals(method.getName())){
                    System.out.println("经纪人代理dance");
                    float money= (float) args[0];
                    return method.invoke(springBrother,money/2);
                }else{
                    System.out.println("其他不赚钱，不管！");
                    return method.invoke(springBrother,args);
                }
            }
        });
        proxy.dance(1000);
        proxy.sing(1000);
        proxy.eat();
    }
}
