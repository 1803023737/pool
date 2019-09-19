package c_proxy;

public class SpringBrother implements  Human {
    @Override
    public void sing(float money) {
        System.out.println("春哥唱歌要钱"+money);
    }

    @Override
    public void dance(float money) {
        System.out.println("春哥跳舞要钱"+money);

    }

    @Override
    public void eat() {
        System.out.println("春哥吃饭啊！");

    }
}
