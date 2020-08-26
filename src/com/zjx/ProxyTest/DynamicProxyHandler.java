package com.zjx.ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理：真正的代理对象由JDK在程序运行时通过反射机制动态创建的，我们只需要编写一个动态处理器就可以了。
 * 注意Proxy.newProxyInstance()方法接受三个参数：
 *
 * @ClassLoader loader:指定当前目标对象使用的类加载器,获取加载器的方法是固定的
 * @Class<?>[] interfaces:指定目标对象实现的接口的类型,使用泛型方式确认类型
 * @InvocationHandler:指定动态处理器，执行目标对象的方法时,会触发事件处理器的方法我们下面实现的就是 对于静态代理，动态代理大大减少了我们的开发任务，同时减少了对业务接口的依赖，降低了耦合度。
 * 但是还是有一点点小小的遗憾之处，那就是它始终无法摆脱仅支持interface代理的桎梏
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object object;

    public DynamicProxyHandler(final Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房前准备");
        Object result = method.invoke(object, args);
        System.out.println("买房后装修");
        return result;
    }
}
