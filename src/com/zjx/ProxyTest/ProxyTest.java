package com.zjx.ProxyTest;

/**
 * 静态代理测试
 */
public class ProxyTest {
    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHosue();
        BuyHouseProxy buyHouseProxy = new BuyHouseProxy(buyHouse);
        buyHouseProxy.buyHosue();
        LambdaTest();
    }

    public static void LambdaTest(){
        new Thread(() -> {
            System.out.println("LambdaTest");
        }).start();
    }
}
