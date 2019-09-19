package e_pool;

import e_pool.Service.DemoService;
import e_pool.Service.impl.DemoServiceImpl;

public class client {

    public static void main(String[] args) {

        DemoService demoService = new DemoServiceImpl();
        demoService.serviceMethod();
    }

}
