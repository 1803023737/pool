package c_proxy;

//代理人  例如经纪人   静态代理
public class ProxyManager implements Human {
    //包装者模式类似
    private Human oldhuman;

    public ProxyManager(Human oldhuman) {
        this.oldhuman = oldhuman;
    }

    @Override
    public void sing(float money) {
        oldhuman.sing(money / 2);
    }

    @Override
    public void dance(float money) {
        oldhuman.dance(money / 2);
    }

    @Override
    public void eat() {
        oldhuman.eat();
    }
}
