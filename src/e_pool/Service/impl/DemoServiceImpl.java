package e_pool.Service.impl;

import e_pool.BeanFactory2;
import e_pool.Service.DemoService;
import e_pool.dao.DaoDemo;

public class DemoServiceImpl implements DemoService {


    @Override
    public void serviceMethod() {
        //普通形式
       // DaoDemo daoDemo = (DaoDemo) BeanFactory.getDaoImpl("DaoDemo");
        //代理形式   ----做切面的
        DaoDemo daoDemo =(DaoDemo) BeanFactory2.getDaoImpl("DaoDemo");
        daoDemo.find();
    }
}
